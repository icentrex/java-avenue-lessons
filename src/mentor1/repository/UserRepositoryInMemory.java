package mentor1.repository;

import mentor1.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepositoryInMemory implements UserRepository {
    private final Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;

    public UserRepositoryInMemory() {
        add(new User("Дмитрий", "123-45-66"));
        add(new User("Иван", "999-45-66"));
        add(new User("Ирина", "888-45-66"));
    }

    @Override
    public User add(User user) {
        user.setId(nextId);
        users.put(nextId, new User(user));
        nextId++;
        return user;
    }

    @Override
    public boolean delete(int userId) {
        return users.remove(userId) != null;
    }

    @Override
    public Optional<User> find(int userId) {
        return Optional.ofNullable(users.get(userId)).map(user -> new User(user));
    }

    @Override
    public boolean update(User user) {
        if (!users.containsKey(user.getId())) {
            return false;
        }

        //сохраняем копию объекта
        users.put(user.getId(), new User(user));
        return true;
    }

    @Override
    public boolean isPhoneExist(String phone) {
        return users.values().stream()
                .anyMatch(user -> user.getPhone().equalsIgnoreCase(phone));
    }

    @Override
    public boolean isPhoneExist(int excludedUserId, String phone) {
        return users.values().stream()
                .anyMatch(user -> user.getId() != excludedUserId && user.getPhone().equalsIgnoreCase(phone));
    }


    @Override
    public List<User> getUsersList() {
        return users.values().stream()
                .sorted(Comparator.comparing(User::getId))
                .map(user -> new User(user))
                .collect(Collectors.toList());
    }
}


