package mentor1;
import mentor1.repository.EquipmentRepository;
import mentor1.repository.UserRepository;
import mentor1.console.ConsoleService;
import mentor1.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        EquipmentRepository equipmentRepository = new EquipmentRepository();

        // Консольный сервис запускается в конце инициализации
        ConsoleService consoleService = new ConsoleService(userService, equipmentRepository);
        consoleService.run();
    }
}
