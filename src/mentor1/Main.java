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
        ConsoleMainMenu.getInstance().run();
    }
}
