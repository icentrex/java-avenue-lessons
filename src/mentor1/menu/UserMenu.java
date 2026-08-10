package mentor1.menu;

import mentor1.Cursoring;
import mentor1.model.User;
import mentor1.service.UserService;

import java.util.List;

public class UserMenu implements Cursoring {
    private final UserService userService;
    private MainMenu menu;
    private DisplayReadWriter display;

    public UserMenu(UserService userService) {
        this.userService = userService;
    }

    public void init() {
        if (menu == null) {
            menu = MainMenu.getInstance();
            display = menu.getDisplay();
        }
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
        init();
        display.write(userService.getUsersList());
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
        init();
        switch (commandNumber) {
            //Создать
            case "1" -> {
                String name = display.writeAndRead(List.of("Введите имя пользователя:"));
                String phone = display.writeAndRead(List.of("Введите телефон пользователя:"));
                User user = userService.create(name, phone);
                if (user == null) {
                    display.write(List.of("Пользователь с таким именем и телефоном уже существует!"));
                    return "";
                }
                MainMenu.getInstance().setCursorObject(user);
                display.write(List.of("Пользователь создан!", "Пользователь выбран!"));
            }
            //Выбрать
            case "2" -> {
                display.write(userService.getUsersList());
                String userId = display.writeAndRead(List.of("Введите id пользователя:"));
                menu.setCursorObject(userService.findByUserId(userId));
            }
            //Выход в главное меню
            case "9" -> {
                return "BACK";
            }
            //Выход из программы
            case "0" -> {
                return "EXIT";
            }
            default -> display.write(List.of("Команды не существует. Попробуйте еще раз!"));
        }
        return "";
    }
}
