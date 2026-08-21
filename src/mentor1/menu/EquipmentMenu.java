package mentor1.menu;

import mentor1.TechnicalException;
import mentor1.model.Equipment;
import mentor1.service.EquipmentService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class EquipmentMenu implements Cursoring {
    private final EquipmentService equipmentService;

    public EquipmentMenu(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    public void showEquipmentsList() {
        List<Equipment> catalog = MainMenu.getInstance().getEquipmentService().getEquipmentsList();

        if (catalog.isEmpty()) {
            MainMenu.getInstance().getDisplayReadWriter().write(List.of("Список техники пустой"));
            return;
        }

        List<String> formatted = catalog.stream()
                .map(equipment -> String.format("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                        equipment.getId(),
                        equipment.getBrandName(),
                        equipment.getSerialNumber(),
                        equipment.getUserId()))
                .toList();
        MainMenu.getInstance().getDisplayReadWriter().write(formatted);
    }

    @Override
    public String getInfo() {
        return "\n=== Меню \"Техника\" ===";
    }

    @Override
    public String getCommands() {
        showEquipmentsList();
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
                String type = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Введите тип оборудования:\n1 - Монитор\n2 - Мышка\n3 - Системный блок"));
                String brandName = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Введите производителя:"));
                String serialNumber = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Введите серийный номер:"));

                try {
                    Optional<Equipment> createEquipmentResult = equipmentService.createEquipment(
                            Integer.parseInt(type),
                            brandName,
                            Integer.parseInt(serialNumber));

                    if (createEquipmentResult.isEmpty()) {
                        MainMenu.getInstance().getDisplayReadWriter()
                                .write(List.of("Техника с таким серийным номером уже существует!"));
                        return "";
                    }

                    MainMenu.getInstance().setCursorObject(createEquipmentResult.get());
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Техника добавлена и выбрана!"));

                } catch (TechnicalException e) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of(e.getMessage()));
                }
            }
            //Выбрать технику
            case "2" -> {
                showEquipmentsList();
                String equipmentId = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Введите id оборудования:"));

                Optional<Equipment> findEquipmentResult = equipmentService.findEquipmentById(Integer.parseInt(equipmentId));

                if (findEquipmentResult.isEmpty()) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Техника с таким ID не существует"));
                    return "";
                }
                MainMenu.getInstance().setCursorObject(findEquipmentResult.get());
            }
            case "9" -> {
                return "BACK";
            }
            case "0" -> {
                return "EXIT";
            }
            default -> MainMenu.getInstance().getDisplayReadWriter()
                    .write(List.of("Команды не существует. Попробуйте еще раз!"));
        }
        return "";
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
