package mentor1.menu;

import mentor1.model.User;
import mentor1.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserMenu implements Cursoring, DisplayReadWriter {
    private final UserService userService;

    public UserMenu(UserService userService) {
        this.userService = userService;
    }

    private void showUsersList() {
        List<User> usersList = userService.getUsersList();
        if (usersList.isEmpty()) {
            DisplayReadWriter.write(List.of("Список пользователей пуст"));
            return;
        }

        List<String> formatted = usersList.stream()
                .map(user -> String.format("userId = %s, name = %s, phone = %s%n",
                        user.getId(),
                        user.getName(),
                        user.getPhone()))
                .toList();
        DisplayReadWriter.write(formatted);
    }

    public UserService getUserService() {
        return userService;
    }

    @Override
    public String getInfo() {
        return "\n=== Меню \"Пользователи\" ===";
    }

    @Override
    public String getCommands() {
        showUsersList();
        return """
                Доступные команды:
                1 - Создать пользователя
                2 - Выбрать пользователя
                9 - Назад в главное меню
                0 - Выход из программы
                """;
    }

    @Override
    public String execute(String commandNumber) {
        switch (commandNumber) {
            //Создать
            case "1" -> {
                String name = DisplayReadWriter.writeAndRead(List.of("Введите имя пользователя:"));
                String phone = DisplayReadWriter.writeAndRead(List.of("Введите телефон пользователя:"));
                Optional<User> createUserResult = userService.createUser(name, phone);
                if (createUserResult.isEmpty()) {
                    DisplayReadWriter.write(List.of("Пользователь с таким телефоном уже существует!"));
                    return "";
                }
                MainMenu.getInstance().setCursorObject(createUserResult.get());
                DisplayReadWriter.write(List.of("Пользователь создан и выбран!"));
            }
            //Выбрать
            case "2" -> {
                showUsersList();
                String userId = DisplayReadWriter.writeAndRead(List.of("Введите id пользователя:"));
                Optional<User> findUserResult = userService.findUserById(Integer.parseInt(userId));

                if (findUserResult.isEmpty()) {
                    DisplayReadWriter.write(List.of("Пользователя с таким ID не существует"));
                    return "";
                }
                MainMenu.getInstance().setCursorObject(findUserResult.get());
            }
            //Выход в главное меню
            case "9" -> {
                return "BACK";
            }
            //Выход из программы
            case "0" -> {
                return "EXIT";
            }
            default -> DisplayReadWriter.write(List.of("Команды не существует. Попробуйте еще раз!"));
        }
        return "";
    }
}
