package mentor1;

import mentor1.model.Computer;
import mentor1.model.Monitor;
import mentor1.model.Mouse;
import mentor1.repository.EquipmentRepository;
import mentor1.repository.UserRepository;
import mentor1.service.ConsoleService;
import mentor1.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);

        EquipmentRepository equipmentRepository = new EquipmentRepository();
        equipmentRepository.add(new Monitor("Samsung", 1));
        equipmentRepository.add(new Mouse("Logitech", 2));
        equipmentRepository.add(new Computer("Dell", 3));

        ConsoleService consoleService = new ConsoleService();

        Scanner scanner = new Scanner(System.in);

//        while (true) {
//            System.out.println("\n---===Программа \"Учет техники v1.0\"===---");
//            consoleService.commandList();
//            System.out.println("Выберите пункт:");
//            String input = scanner.nextLine();
//            switch (input) {
//                case "create" -> {
//                    System.out.println("Введите имя пользователя");
//                    String name = scanner.nextLine();
//                    System.out.println("Введите id пользователя");
//                    int userId = scanner.nextInt();
//                    userRepository.create(name, userId);
//                    scanner.nextLine();
//                }
//                case "info" -> {
//                    System.out.println("Введите id пользователя");
//                    userRepository.infoById(scanner.nextInt());
//                    scanner.nextLine();
//                }
//                case "delete" -> {
//                    System.out.println("Введите id пользователя");
//                    userRepository.deleteById(scanner.nextInt());
//                    scanner.nextLine();
//                }
//                case "all" -> userRepository.showAllUsers();
//                case "exit" -> {
//                    System.out.println("Работа программы завершена");
//                    scanner.close();
//                    return;
//                }
//                default -> System.out.println("Неизвестная команда. Попробуйте еще раз");
//            }
//
//        }
    }
}
