package mentor1.repository;

import mentor1.exceptions.DuplicateRequestException;
import mentor1.model.User;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserFileRepository {
    private static final String FILE_PATH = "src/mentor1/repository/UserRepository.txt";
    private final Path path = Path.of(FILE_PATH);
    private int nextId = 1;

    public UserFileRepository() {
        nextId = maxIdFromFile() + 1;
    }

    private int maxIdFromFile() {
        return readFileRepositoryToList().stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0);
    }

    private String toLine(User user) {
        return user.getId() + ";" + user.getName() + ";" + user.getPhone();
    }

    private void writeToFile(List<User> users) {
        List<String> lines = users.stream()
                .map(this::toLine)
                .collect(Collectors.toList());
        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось записать в файл: " + path, e);
        }
    }

    public List<User> readFileRepositoryToList() {
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(line -> {
                        String[] tokens = line.split(";", 3);
                        if (tokens.length < 3) {
                            throw new IllegalArgumentException("Строка не соответствует формату: " + line);
                        }
                        return new User(Integer.parseInt(tokens[0]), tokens[1].trim(), tokens[2]);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось прочитать файл: " + path, e);
        }
    }

    public User add(User user) {
        List<User> all = readFileRepositoryToList();

        boolean alreadyExist = all.stream().anyMatch(u -> u.getName().equalsIgnoreCase(user.getName())
                && u.getPhone().equalsIgnoreCase(user.getPhone()));

        if (alreadyExist) {
            throw new DuplicateRequestException("Пользователь с таким именем и телефоном уже существует");
        }

        user.setId(nextId);
        nextId++;
        all.add(user);

        writeToFile(all);
        return user;
    }

    public boolean deleteUserById(int userId) {
        //todo проверка на наличие техники закрепленной за юзером
        List<User> all = readFileRepositoryToList();

        List<User> remaining = all.stream()
                .filter(user -> user.getId() != userId)
                .collect(Collectors.toList());

        if (remaining.size() == all.size()) {
            return false; //такого пользователя нет в файле
        }

        writeToFile(remaining);
        return true;
    }

    //а в сервисе через findUserById получаешь Юзера,
    // обновляшь поля какие нужны, проверяешь на ошибки,
    // и в конце кидаешь этого юзера в updateUser(User user)
    public boolean updateUser(User user) {
        List<User> all = readFileRepositoryToList();

        boolean isUserExist = all.stream().anyMatch(u -> u.getId() == user.getId());
        if (!isUserExist) {
            return false;
        }

        List<User> updated = all.stream()
                .map(u -> u.getId() == user.getId() ? user : u)
                .collect(Collectors.toList());

        writeToFile(updated);
        return true;
    }
//    public boolean updateUserName(int userId, String name) {
//        Optional<User> findUserResult = findUserById(userId);
//
//        if (findUserResult.isEmpty()) {
//            return false;
//        }
//
//        findUserResult.get().setName(name);
//        return true;
//    }
//
//    public boolean updateUserPhone(int userId, String phone) {
//        Optional<User> findUserResult = findUserById(userId);
//
//        if (findUserResult.isEmpty()) {
//            return false;
//        }
//
//        findUserResult.get().setPhone(phone);
//        return true;
//    }

    public Optional<User> findUserById(int userId) {
        return readFileRepositoryToList().stream()
                .filter(user -> user.getId() == userId)
                .findFirst();
    }

    public boolean isPhoneExist(int excludedUserId, String phone) {
        return readFileRepositoryToList().stream()
                .anyMatch(user -> user.getId() != excludedUserId && user.getPhone().equalsIgnoreCase(phone));
    }

    public boolean isPhoneExist(String phone) {
        return readFileRepositoryToList().stream()
                .anyMatch(user -> user.getPhone().equalsIgnoreCase(phone));
    }

    public List<User> getUsersList() {
        return readFileRepositoryToList().stream()
                .sorted(Comparator.comparing(User::getId))
                .collect(Collectors.toList());
    }
}


