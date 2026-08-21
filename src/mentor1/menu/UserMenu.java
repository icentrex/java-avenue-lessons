package mentor1.menu;

import mentor1.TechnicalException;
import mentor1.model.User;
import mentor1.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class UserMenu implements Cursoring {
    private final UserService userService;

    public UserMenu(UserService userService) {
        this.userService = userService;
    }

    private void showUsersList() {
        List<User> usersList = userService.getUsersList();
        if (usersList.isEmpty()) {
            MainMenu.getInstance().getDisplayReadWriter().write(List.of("Список пользователей пуст"));
            return;
        }

        List<String> formatted = usersList.stream()
                .map(user -> String.format("userId = %s, name = %s, phone = %s%n",
                        user.getId(),
                        user.getName(),
                        user.getPhone()))
                .toList();
        MainMenu.getInstance().getDisplayReadWriter().write(formatted);
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
            //Создать пользователя
            case "1" -> {
                String name = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Введите имя пользователя:"));
                String phone = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Введите телефон пользователя:"));

                try {
                    Optional<User> createUserResult = userService.createUser(name, phone);
                    if (createUserResult.isEmpty()) {
                        MainMenu.getInstance().getDisplayReadWriter()
                                .write(List.of("Пользователь с таким телефоном уже существует!"));
                        return "";
                    }

                    MainMenu.getInstance().setCursorObject(createUserResult.get());
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Пользователь создан и выбран."));

                } catch (TechnicalException e) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of(e.getMessage()));
                }
            }
            //Выбрать пользователя
            case "2" -> {
                showUsersList();
                String userId = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Введите id пользователя:"));
                Optional<User> findUserResult = userService.findUserById(Integer.parseInt(userId));

                if (findUserResult.isEmpty()) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Пользователя с таким ID не существует"));
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
            default -> MainMenu.getInstance().getDisplayReadWriter()
                    .write(List.of("Команды не существует. Попробуйте еще раз!"));
        }
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserMenu) obj;
        return Objects.equals(this.userService, that.userService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userService);
    }

    @Override
    public String toString() {
        return "UserMenu[" +
                "userService=" + userService + ']';
    }
}
