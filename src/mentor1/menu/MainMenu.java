package mentor1.menu;

import mentor1.repository.*;
import mentor1.service.EquipmentService;
import mentor1.service.UserService;
import mentor1.utils.Menu;
import mentor1.utils.DisplayReadWriter;
import mentor1.utils.DisplayReadWriterImpl;
import mentor1.utils.MenuNavigator;

public class MainMenu implements Menu {
    private final MenuNavigator navigation;
    private static MainMenu instance;
    private final UserMenu userMenu;
    private final EquipmentMenu equipmentMenu;
    private final UserService userService;
    private final EquipmentService equipmentService;
    private final DisplayReadWriterImpl displayReadWriter;

    private MainMenu() {
        displayReadWriter = new DisplayReadWriterImpl();
        this.navigation = new MenuNavigator(displayReadWriter);

        UserRepository userRepository = new UserRepositoryInMemory();
        EquipmentTypeRepository equipmentTypeRepository = new EquipmentTypeRepositoryInMemory();
        EquipmentRepository equipmentRepository = new EquipmentRepositoryInMemory();

        this.equipmentService = new EquipmentService(equipmentRepository, equipmentTypeRepository);
        this.userService = new UserService(userRepository, equipmentService);

        this.userMenu = new UserMenu(userService);
        this.equipmentMenu = new EquipmentMenu(equipmentService);
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    public void run() {
        navigation.run(this);
    }

    public DisplayReadWriter getDisplayReadWriter() {
        return displayReadWriter;
    }

    public MenuNavigator getNavigation() {
        return navigation;
    }

    public UserService getUserService() {
        return userService;
    }

    public EquipmentService getEquipmentService() {
        return equipmentService;
    }

    @Override
    public String getInfo() {
        return "\n=== Программа \"Учет техники v1.0\" ===\n";
    }

    @Override
    public String getCommands() {
        return """
                Доступные команды:
                1 - Меню "Пользователи"
                2 - Меню "Техника"
                0 - Выход из программы
                """;
    }

    @Override
    public String execute(String commandNumber) {
        switch (commandNumber) {
            case "1" -> navigation.open(userMenu);
            case "2" -> navigation.open(equipmentMenu);
            case "0" -> {
                return "EXIT";
            }
            default -> System.out.println("Команды не существует. Попробуйте еще раз!");
        }
        return "";
    }
}
