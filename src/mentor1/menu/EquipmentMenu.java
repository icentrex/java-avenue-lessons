package mentor1.menu;

import mentor1.Cursoring;
import mentor1.model.Equipment;
import mentor1.service.EquipmentService;

public class EquipmentMenu implements Cursoring {
    private final EquipmentService equipmentService;

    public EquipmentMenu(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
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
        equipmentService.showAllEquipments();
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
                System.out.println("Введите тип оборудования:\n1 - Монитор\n2 - Мышка\n3 - Системный блок");
                String type = ConsoleScanner.IN.nextLine();
                System.out.println("Введите производителя:");
                String brandName = ConsoleScanner.IN.nextLine();
                System.out.println("Введите серийный номер:");
                String serialNumber = ConsoleScanner.IN.nextLine();
                Equipment equipment = equipmentService.add(Integer.parseInt(type), brandName, Integer.parseInt(serialNumber));
                if (equipment == null) {
                    return "";
                }
                System.out.println("Техника добавлена!");
                MainMenu.getInstance().setCursorObject(equipment);
                System.out.println("Техника выбрана!");
            }
            //Выбрать технику
            case "2" -> {
                equipmentService.showAllEquipments();
                System.out.println("Введите id оборудования:");
                String equipmentId = ConsoleScanner.IN.nextLine();
                MainMenu.getInstance().setCursorObject(equipmentService.findById(Integer.parseInt(equipmentId)));
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
