package lesson35.server;

import lesson35.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        int userCount = 1;

        try {
            ServerSocket serverSocket = new ServerSocket(9123);
            System.out.println("Сервер запущен на порту 9123");
            System.out.println("Ожидаем пользователей");

            while (true) {
                Socket newUserSocket = serverSocket.accept();
                DataInputStream in = new DataInputStream(newUserSocket.getInputStream());

                User newUser = new User("user" + userCount, newUserSocket);
                userCount++;
                System.out.println("Пользователь " + newUser.getName() + " подключился");
                users.add(newUser);

                Thread thread = new Thread(() -> {
                    try {
                        while (true) {
                            String message = in.readUTF(); // Ждём сообщение от пользователя-хозяина треда (если он отключится то, будет Exception)
                            System.out.println(newUser.getName() + ": " + message);

                            for (User user : users) {
                                if (user != newUser) {
                                    DataOutputStream out = new DataOutputStream(user.getSocket().getOutputStream());
                                    out.writeUTF(newUser.getName() + ": " + message);
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Пользователь " + newUser.getName() + " отключился");
                        users.remove(newUser);
                    }
                });
                thread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}