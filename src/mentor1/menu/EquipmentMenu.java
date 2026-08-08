package mentor1.menu;

import mentor1.model.Equipment;
import mentor1.service.EquipmentService;

import java.util.List;
import java.util.Objects;

public final class EquipmentMenu implements Cursoring, DisplayReadWriter {
    private final EquipmentService equipmentService;

    public EquipmentMenu(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
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
                String type = DisplayReadWriter.writeAndRead(
                        List.of("Введите тип оборудования:\n1 - Монитор\n2 - Мышка\n3 - Системный блок"));
                String brandName = DisplayReadWriter.writeAndRead(List.of("Введите производителя:"));
                String serialNumber = DisplayReadWriter.writeAndRead(List.of("Введите серийный номер:"));

                Equipment equipment = equipmentService.create(Integer.parseInt(type), brandName, Integer.parseInt(serialNumber));

                if (equipment == null) {
                    return "";
                }

                MainMenu.getInstance().setCursorObject(equipment);
                DisplayReadWriter.write(List.of("Техника добавлена!", "Техника выбрана!"));
            }
            //Выбрать технику
            case "2" -> {
                equipmentService.showAllEquipments();
                String equipmentId = DisplayReadWriter.writeAndRead(List.of("Введите id оборудования:"));
                MainMenu.getInstance().setCursorObject(equipmentService.findById(Integer.parseInt(equipmentId)));
            }
            case "9" -> {
                return "BACK";
            }
            case "0" -> {
                return "EXIT";
            }
            default -> DisplayReadWriter.write(List.of("Команды не существует. Попробуйте еще раз!"));
        }
        return "";
    }

    public EquipmentService equipmentService() {
        return equipmentService;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (EquipmentMenu) obj;
        return Objects.equals(this.equipmentService, that.equipmentService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentService);
    }

    @Override
    public String toString() {
        return "EquipmentMenu[" +
                "equipmentService=" + equipmentService + ']';
    }

}
