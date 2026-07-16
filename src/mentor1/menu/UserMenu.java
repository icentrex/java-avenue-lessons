package mentor1.menu;

import mentor1.Cursoring;
import mentor1.model.User;
import mentor1.service.UserService;

public class UserMenu implements Cursoring {
    private final UserService userService;

    public UserMenu(UserService userService) {
        this.userService = userService;
    }

    public User findUserById(int id) {
        return userService.findById(id);
    }

    @Override
    public String getInfo() {
        return "\n=== Меню \"Пользователи\" ===";
    }

    @Override
    public String getCommands() {
        return """
                Доступные команды:
                1 - Создать пользователя
                2 - Удалить пользователя
                3 - Выбрать пользователя
                9 - Назад в главное меню
                0 - Выход из программы
                """;
    }

    @Override
    public String execute(String commandNumber) {
        switch (commandNumber) {
            case "1" -> {
                System.out.println("Введите имя пользователя:");
                String name = ConsoleScanner.IN.nextLine();
                System.out.println("Введите телефон пользователя:");
                String phone = ConsoleScanner.IN.nextLine();
                userService.create(name, phone);
            }
            case "2" -> {
                userService.showAll();
                System.out.println("Введите id пользователя:");
                String userId = ConsoleScanner.IN.nextLine();
                userService.deleteById(Integer.parseInt(userId));
            }
            case "3" -> {
                userService.showAll();
                System.out.println("Введите id пользователя:");
                String userId = ConsoleScanner.IN.nextLine();
                ConsoleScanner.IN.nextLine();
                return "USER" + userId;
            }
            case "9" -> {
                return "BACK";
            }
            case "0" -> {
                return "EXIT";
            }
            default -> System.out.println("Команды не существует. Попробуйте еще раз!");
        }
        return "";
    }
}
