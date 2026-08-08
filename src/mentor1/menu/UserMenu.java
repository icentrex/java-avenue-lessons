package mentor1.menu;

import mentor1.model.User;
import mentor1.service.UserService;

import java.util.List;

public class UserMenu implements Cursoring, DisplayReadWriter {
    private final UserService userService;


    public UserMenu(UserService userService) {
        this.userService = userService;
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
        DisplayReadWriter.write(userService.getUsersList());
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
                User user = userService.create(name, phone);
                if (user == null) {
                    DisplayReadWriter.write(List.of("Пользователь с таким именем и телефоном уже существует!"));
                    return "";
                }
                MainMenu.getInstance().setCursorObject(user);
                DisplayReadWriter.write(List.of("Пользователь создан!", "Пользователь выбран!"));
            }
            //Выбрать
            case "2" -> {
                DisplayReadWriter.write(userService.getUsersList());
                String userId = DisplayReadWriter.writeAndRead(List.of("Введите id пользователя:"));
                MainMenu.getInstance().setCursorObject(userService.findByUserId(Integer.parseInt(userId)));
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
