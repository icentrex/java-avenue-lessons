package mentor1.repository;

import mentor1.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<User> users = new ArrayList<>(100);

    public UserRepository() {
        users.add(new User("Сергей", "123-45-66"));
        users.add(new User("Иван", "999-45-66"));
        users.add(new User("Ирина", "888-45-66"));
    }

    public void create(User user) {
        if (users.contains(user)) {
            System.out.println("Пользователь существует!");
        }
        users.add(user);
    }

    public void deleteById(int id) {
        users.remove(id);
    }

    public void updateName(String name) {
        User user = findByName(name);
        if (user != null) {
            user.setName(name);
        }
    }

    public void updatePhone(String phone) {
        User user = findByPhone(phone);
        if (user != null) {
            user.setPhoneNumber(phone);
        }
    }

    public User findById(int id) {
        return users.get(id);
    }

    public User findByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public User findByPhone(String phone) {
        for (User user : users) {
            if (user.getPhoneNumber().equalsIgnoreCase(phone)) {
                return user;
            }
        }
        return null;
    }

    public int findUserIdByPhone(String phone) {
        for (int index = 0; index < users.size(); index++) {
            if (users.get(index).getPhoneNumber().equalsIgnoreCase(phone)) {
                return index;
            }
        }
        return 0;
    }

    public void showAllUsers() {
        // Отсортировать через stream по ID
        // List<User> filterList = users.stream().sorted().toList();
        for (int index = 0; index < users.size(); index++) {
            System.out.printf("id = %d, name = %s, phone = %s%n", index, users.get(index).getName(), users.get(index).getPhoneNumber());
        }
    }

    public List<User> getUsers() {
        return users;
    }
}
