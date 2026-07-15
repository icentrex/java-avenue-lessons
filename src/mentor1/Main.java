package mentor1;

import mentor1.menu.EquipmentMenu;
import mentor1.menu.UserMenu;
import mentor1.repository.EquipmentRepository;
import mentor1.repository.UserRepository;
import mentor1.menu.ConsoleMainMenu;
import mentor1.service.EquipmentService;
import mentor1.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        EquipmentRepository equipmentRepository = new EquipmentRepository();
        EquipmentService equipmentService = new EquipmentService();

        // Консольный сервис запускается в конце инициализации
        UserMenu userMenu = new UserMenu(userService);
        EquipmentMenu equipmentMenu = new EquipmentMenu(equipmentService);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(userMenu, equipmentMenu);
        consoleMainMenu.run();
    }
}
