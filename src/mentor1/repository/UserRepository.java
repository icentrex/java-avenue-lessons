package mentor1.repository;

import mentor1.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepository {
    private final Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;

    public UserRepository() {
        addUser(new User("Дмитрий", "123-45-66"));
        addUser(new User("Иван", "999-45-66"));
        addUser(new User("Ирина", "888-45-66"));
    }

    public User addUser(User user) {
        user.setId(nextId);
        users.put(nextId, user);
        nextId++;
        return user;
    }

    public boolean deleteUserById(int userId) {
        return users.remove(userId) != null;
    }

    public boolean updateUserName(int userId, String name) {
        Optional<User> findUserResult = findUserById(userId);

        if (findUserResult.isEmpty()) {
            return false;
        }

        findUserResult.get().setName(name);
        return true;
    }

    public boolean updateUserPhone(int userId, String phone) {
        Optional<User> findUserResult = findUserById(userId);

        if (findUserResult.isEmpty()) {
            return false;
        }

        findUserResult.get().setPhone(phone);
        return true;
    }

    public Optional<User> findUserById(int userId) {
        return Optional.ofNullable(users.get(userId));
    }

    public boolean isPhoneExist(int excludedUserId, String phone) {
        return users.values().stream()
                .anyMatch(user -> user.getId() != excludedUserId && user.getPhone().equalsIgnoreCase(phone));
    }

    public boolean isPhoneExist(String phone) {
        return users.values().stream()
                .anyMatch(user -> user.getPhone().equalsIgnoreCase(phone));
    }

    public List<User> getUsersList() {
        return users.values().stream()
                .sorted(Comparator.comparing(User::getId))
                .collect(Collectors.toList());
    }
}


