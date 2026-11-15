package mentor1.menu;

import mentor1.model.Equipment;
import mentor1.model.EquipmentType;
import mentor1.service.EquipmentService;
import mentor1.utils.Menu;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class EquipmentMenu implements Menu {
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
                .map(equipment -> String.format("id = %d, type= %s, name = %s, serialNumber = %d, user = %s%n",
                        equipment.getId(),
                        equipment.getEquipmentType(),
                        equipment.getBrandName(),
                        equipment.getSerialNumber(),
                        equipment.getUser()))
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
                9 - Назад
                0 - Выход из программы
                """;
    }

    @Override
    public String execute(String commandNumber) {
        switch (commandNumber) {
            //Добавить технику
            case "1" -> {
                MainMenu.getInstance().getDisplayReadWriter().write(List.of("Существующие типы оборудования:"));
                List<EquipmentType> equipmentTypesList = equipmentService.getEquipmentTypesList();
                MainMenu.getInstance().getDisplayReadWriter().write(equipmentTypesList
                        .stream()
                        .map(equipmentType -> String.format("id = %d, name = %s", equipmentType.getId(), equipmentType.getName()))
                        .toList());

                Optional<Integer> typeId = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndReadInt(List.of("Выберите тип оборудования (введите ID):"));

                if (typeId.isEmpty()) {
                    return "";
                }

                int chosenTypeId = typeId.get();
                Optional<EquipmentType> chosenTypeResult = equipmentTypesList.stream()
                        .filter(equipmentType -> equipmentType.getId() == chosenTypeId)
                        .findFirst();

                if (chosenTypeResult.isEmpty()) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Тип оборудования с таким ID не найден"));
                    return "";
                }

                String brandName = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Введите производителя:"));

                Optional<Integer> serialNumber = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndReadInt(List.of("Введите серийный номер:"));

                if (serialNumber.isEmpty()) {
                    return "";
                }

                Optional<Equipment> createEquipmentResult = equipmentService.createEquipment(
                        chosenTypeResult.get(),
                        brandName,
                        serialNumber.get());

                if (createEquipmentResult.isEmpty()) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Техника с таким серийным номером уже существует!"));
                    return "";
                }

                MainMenu.getInstance().getNavigation().open(createEquipmentResult.get());
                MainMenu.getInstance().getDisplayReadWriter()
                        .write(List.of("Техника добавлена и выбрана!"));
            }
            //Выбрать технику
            case "2" -> {
                showEquipmentsList();
                Optional<Integer> equipmentId = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndReadInt(List.of("Введите id оборудования:"));
                if (equipmentId.isEmpty()) {
                    return "";
                }

                Optional<Equipment> findEquipmentResult = equipmentService.findEquipmentById(equipmentId.get());

                if (findEquipmentResult.isEmpty()) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Техника с таким ID не существует"));
                    return "";
                }
                MainMenu.getInstance().getNavigation().open(findEquipmentResult.get());
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
