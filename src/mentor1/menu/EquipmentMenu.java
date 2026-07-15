package mentor1.menu;

import mentor1.Cursoring;
import mentor1.service.EquipmentService;

public class EquipmentMenu implements Cursoring {
    private EquipmentService equipmentService;

    public EquipmentMenu(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @Override
    public String getInfo() {
        return "\n=== Меню \"Техника\" ===";
    }

    @Override
    public String getCommands() {
        return """
                Доступные команды:
                1 - Добавить технику
                2 - Удалить технику
                3 - Информация о технике
                9 - Назад в главное меню
                0 - Выход из программы
                """;
    }

    @Override
    public String execute(String commandNumber) {
//        switch (commandNumber) {
//            case "1" -> {
//                System.out.println("Введите имя пользователя:");
//                String name = ConsoleScanner.IN.nextLine();
//                System.out.println("Введите телефон пользователя:");
//                String phone = ConsoleScanner.IN.nextLine();
//                userService.create(name, phone);
//            }
//            case "2" -> {
//                System.out.println("Введите телефон пользователя:");
//                String phone = ConsoleScanner.IN.nextLine();
//                userService.deleteByPhone(phone);
//            }
//            case "3" -> {
//                System.out.println("Введите телефон пользователя:");
//                String phone = ConsoleScanner.IN.nextLine();
//                System.out.println(userService.infoByPhone(phone));
//                ;
//            }
//            case "4" -> userService.assignEquipment();
//            case "5" -> userService.detachEquipment();
//            case "6" -> userService.showAll();
//            case "9" -> {
//                return "BACK";
//            }
//            case "0" -> {
//                return "EXIT";
//            }
//            default -> System.out.println("Команды не существует. Попробуйте еще раз!");
//        }
        return "";
    }
}
