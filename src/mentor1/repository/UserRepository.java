package mentor1.repository;

import mentor1.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public UserRepository() {
        User user = new User("Дмитрий", "123-45-66");
        users.put(user.getId(), user);
        User user1 = new User("Иван", "999-45-66");
        users.put(user1.getId(), user1);
        User user2 = new User("Ирина", "888-45-66");
        users.put(user2.getId(), user2);
    }

    public User create(String name, String phone) {

        if (isNameExist(name)) {
            System.out.println("Пользователь с таким именем уже существует!");
            return null;
        }

        if (isPhoneExist(phone)) {
            System.out.println("Пользователь с таким телефоном уже существует!");
            return null;
        }

        User user = new User(name, phone);
        users.put(user.getId(), user);
        return user;
    }

    public void deleteById(String id) {
        users.remove(id);
    }

    public void updateName(String userId, String name) {
        User user = findById(userId);

        if (user != null) {
            user.setName(name);
        }
        //TODO проверять на дубликаты
    }

    public void updatePhone(String userId, String phone) {
        User user = findById(userId);

        if (user != null) {
            user.setPhoneNumber(phone);
        }
        //TODO проверять на дубликаты
    }

    public User findById(String id) {
        return users.get(id);
    }

    public boolean isPhoneExist(String phone) {
        return users.values().stream()
                .anyMatch(user -> user.getPhoneNumber().equalsIgnoreCase(phone));
    }

    public boolean isNameExist(String name) {
        return users.values().stream()
                .anyMatch(user -> user.getName().equalsIgnoreCase(name));
    }

    public User getInfo(String userId) {
        return users.get(userId);
    }

    public List<User> getUsersList() {
        return users.values().stream()
                .sorted(Comparator.comparing(user -> user.getId()))
                .collect(Collectors.toList());
    }

//    public Map<String, User> getUsers() {
//        return users;
//    }
}


