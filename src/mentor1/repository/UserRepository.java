package mentor1.repository;

import mentor1.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepository {
    private final Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;

    public UserRepository() {
        add(new User("Дмитрий", "123-45-66"));
//        add(new User("Иван", "999-45-66"));
//        add(new User("Ирина", "888-45-66"));
    }

    public void add(User user) {
        user.setId(nextId);
        users.put(nextId, user);
        nextId++;
    }

    public void deleteById(int userId) {
        users.remove(userId);
    }

    public void updateName(int userId, String name) {
        User user = findById(userId);

        if (user != null) {
            user.setName(name);
        }
    }

    public void updatePhone(int userId, String phone) {
        User user = findById(userId);

        if (user != null) {
            user.setPhoneNumber(phone);
        }
    }

    public User findById(int userId) {
        return users.get(userId);
    }

    public boolean isPhoneExist(String phone) {
        return users.values().stream()
                .anyMatch(user -> user.getPhoneNumber().equalsIgnoreCase(phone));
    }

    public List<User> getUsersList() {
        return users.values().stream()
                .sorted(Comparator.comparing(User::getId))
                .collect(Collectors.toList());
    }
}


