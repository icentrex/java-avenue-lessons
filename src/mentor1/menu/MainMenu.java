package mentor1.menu;

import mentor1.Cursoring;
import mentor1.repository.EquipmentRepository;
import mentor1.repository.UserRepository;
import mentor1.service.EquipmentService;
import mentor1.service.UserService;

public class MainMenu implements Cursoring {
    private static MainMenu instance;
    private final UserMenu userMenu;
    private final EquipmentMenu equipmentMenu;
    private final UserService userService;
    private final EquipmentService equipmentService;
    private final ConsoleDisplay display = new ConsoleDisplay();
    private Cursoring cursorObject;
    private boolean isNeedContinue = true;

    private MainMenu() {
        UserRepository userRepository = new UserRepository();
        EquipmentRepository equipmentRepository = new EquipmentRepository();

        this.userService = new UserService(userRepository);
        this.equipmentService = new EquipmentService(equipmentRepository);

        userService.setEquipmentService(equipmentService);
        equipmentService.setUserService(userService);

        this.userMenu = new UserMenu(userService);
        this.equipmentMenu = new EquipmentMenu(equipmentService);
    }

    public UserService getUserService() {
        return userService;
    }

    public EquipmentService getEquipmentService() {
        return equipmentService;
    }

    public ConsoleDisplay getDisplay() {
        return display;
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    public void run() {
        while (isNeedContinue) {
            System.out.println(this.getInfo());
            System.out.println(this.getCommands());
            String input = ConsoleScanner.IN.nextLine();
            System.out.println(this.execute(input));
        }
    }

    public void setCursorObject(Cursoring cursorObject) {
        this.cursorObject = cursorObject;
    }

    @Override
    public String getInfo() {
        return this.cursorObject != null
                ? this.cursorObject.getInfo()
                : "\n=== Программа \"Учет техники v1.0\" ===\n";
    }

    @Override
    public String getCommands() {
        return this.cursorObject != null
                ? this.cursorObject.getCommands()
                : """
                Доступные команды:
                1 - Меню "Пользователи"
                2 - Меню "Техника"
                0 - Выход из программы
                """;
    }

    @Override
    public String execute(String commandNumber) {
        if (cursorObject != null) {
            String result = this.cursorObject.execute(commandNumber);

            if (result.isEmpty()) {
                return "";
            }

            if (result.equals("BACK")) {
                this.cursorObject = null;
                return "";
            } else if (result.equals("EXIT")) {
                this.isNeedContinue = false;
                return "Программа завершена!";
            }
            return result;
        }

        switch (commandNumber) {
            case "1" -> this.cursorObject = this.userMenu;
            case "2" -> this.cursorObject = this.equipmentMenu;
            case "0" -> {
                this.isNeedContinue = false;
                return "Программа завершена!";
            }
            default -> System.out.println("Команды не существует. Попробуйте еще раз!");
        }
        return "";
    }
}
