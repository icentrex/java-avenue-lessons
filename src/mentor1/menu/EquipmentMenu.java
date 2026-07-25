package mentor1.menu;

import mentor1.Cursoring;
import mentor1.repository.EquipmentRepository;
import mentor1.service.EquipmentService;

public class EquipmentMenu implements Cursoring {

    private static EquipmentMenu instance;
    private final EquipmentService equipmentService;

    private EquipmentMenu() {
        EquipmentRepository equipmentRepository = new EquipmentRepository();
        this.equipmentService = new EquipmentService(equipmentRepository);
        instance = this;
    }

    public static EquipmentMenu getInstance() {
        return instance;
    }

    public EquipmentService getEquipmentService() {
        return equipmentService;
    }

    @Override
    public String getInfo() {
        return "\n=== Меню \"Техника\" ===";
    }

    @Override
    public String getCommands() {
        equipmentService.showAll();
        return """
                Доступные команды:
                1 - Добавить технику
                2 - Выбрать технику
                9 - Назад в главное меню
                0 - Выход из программы
                """;
    }

    @Override
    public String execute(String commandNumber) {
        switch (commandNumber) {
            //Добавить технику
            case "1" -> {
                equipmentService.showAll();
                System.out.println("Введите тип оборудования:\n1 - Монитор\n2 - Мышка\n3 - Системный блок");
                String type = ConsoleScanner.IN.nextLine();
                System.out.println("Введите производителя:");
                String name = ConsoleScanner.IN.nextLine();
                System.out.println("Введите серийный номер:");
                String serialNumber = ConsoleScanner.IN.nextLine();
                equipmentService.add(Integer.parseInt(type), name, Integer.parseInt(serialNumber));
            }
            //Выбрать технику
            case "2" -> {
                equipmentService.showAll();
                System.out.println("Введите id оборудования:");
                String equipmentId = ConsoleScanner.IN.nextLine();
                ConsoleMainMenu.getInstance().setCursorObject(equipmentService.findById(Integer.parseInt(equipmentId)));
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
