package mentor1.menu;

import mentor1.Cursoring;
import mentor1.repository.UserRepository;
import mentor1.service.UserService;

public class UserMenu implements Cursoring {

    private static UserMenu instance;
    private final UserService userService;

    private UserMenu() {
        UserRepository userRepository = new UserRepository();
        this.userService = new UserService(userRepository);
    }

    public static UserMenu getInstance() {
        if (instance == null) {
            instance = new UserMenu();
        }
        return instance;
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
        userService.showAll();
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
                System.out.println("Введите имя пользователя:");
                String name = ConsoleScanner.IN.nextLine();
                System.out.println("Введите телефон пользователя:");
                String phone = ConsoleScanner.IN.nextLine();
                userService.create(name, phone);
            }
            //Выбрать
            case "2" -> {
                userService.showAll();
                System.out.println("Введите id пользователя:");
                String userId = ConsoleScanner.IN.nextLine();
                ConsoleMainMenu.getInstance().setCursorObject(userService.findById(Integer.parseInt(userId)));
            }
            //Выход в главное меню
            case "9" -> {
                return "BACK";
            }
            //Выход из программы
            case "0" -> {
                return "EXIT";
            }
            default -> System.out.println("Команды не существует. Попробуйте еще раз!");
        }
        return "";
    }
}
