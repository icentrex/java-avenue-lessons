package mentor1;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    //нужно делать final?
    private final List<User> users = new ArrayList<>();

    public void create(String name, int id) {
        User user = new User(name, id);
        if (users.contains(user)) {
            System.out.println("\nПользователь уже существует!");
        } else {
            users.add(user);
            System.out.println("Пользователь " + user + " создан");
        }
    }

    //как верно реализовать?
    public void deleteById(int userId) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            users.remove(index);
            System.out.printf("\nПользователь с id = %d удален", userId);
        } else {
            System.out.printf("\nПользователь c id = %d не найден", userId);
        }
    }

    public void infoById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                System.out.println("\nИнформация о пользователе:");
                System.out.println(user);
                return;
            }
        }
        System.out.println("\nПользователь не найден");
    }

    public void showAllUsers() {
        System.out.println("\nСписок всех пользователей:");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
