package mentor1.model;

import mentor1.menu.Cursoring;
import mentor1.menu.DisplayReadWriter;
import mentor1.menu.MainMenu;
import mentor1.service.EquipmentService;

import java.util.List;
import java.util.Objects;

public abstract class Equipment implements Cursoring, DisplayReadWriter {

    private int id;
    private String brandName;
    private int serialNumber;
    private int userId;
    //так норм делать или лучше метод инит оставить?
    private EquipmentService equipmentService = MainMenu.getInstance().getEquipmentService();

    //private User currentUser;
    public Equipment(String brandName, int serialNumber) {
        this.brandName = brandName;
        this.serialNumber = serialNumber;
        this.userId = 0;
        //currentUser = null;
    }

//    public void init() {
//        MainMenu menu = MainMenu.getInstance();
//        equipmentService = menu.getEquipmentService();
//    }

    public String getBrandName() {
        return this.brandName;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + brandName + '\'' +
                ", serialNumber=" + serialNumber +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return id == equipment.id && serialNumber == equipment.serialNumber && Objects.equals(brandName, equipment.brandName)
                && Objects.equals(userId, equipment.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandName, serialNumber, userId);
    }

    @Override
    public String getInfo() {
        return "\n=== Меню \"Выбранное оборудование\" ===\nИнформация о технике:" +
                String.format("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                        this.id,
                        this.brandName,
                        this.serialNumber,
                        this.userId);
    }

    @Override
    public String getCommands() {
        //        init();
        DisplayReadWriter.write(List.of("Закреплена за пользователем:"));
        equipmentService.showAssignedUserByEquipmentId(this.id);
        // Систем аут брать из currentUser данного класса
        return """
                Доступные команды:
                1 - Закрепить технику
                2 - Открепить технику
                3 - Изменить технику
                4 - Удалить технику
                9 - Назад в главное меню
                0 - Выход из программы
                """;
    }

    @Override
    public String execute(String commandNumber) {
        //        init();
        switch (commandNumber) {
            //Закрепить технику
            case "1" -> {
                equipmentService.showUsersList();
                String userId = DisplayReadWriter.writeAndRead(List.of("Введите id пользователя:"));
                equipmentService.assignEquipment(Integer.parseInt(userId), this.id);
            }
            //Открепить технику
            case "2" -> {
                DisplayReadWriter.write(List.of("Открепляю технику от пользователя..."));
                equipmentService.detachEquipment(this.id);
                DisplayReadWriter.write(List.of("Техника откреплена"));
            }
            //Изменить технику
            case "3" -> {
                equipmentService.showAllEquipments();
                String choice = DisplayReadWriter.writeAndRead(
                        List.of("Что хотите скорректировать?%n" +
                                "1 - Производителя%n" +
                                "2 - Серийный номер%n" +
                                "3 - Тип устройства(в разработке)"));
                switch (choice) {
                    case "1" -> {
                        String brandName = DisplayReadWriter.writeAndRead(List.of("Введите корректное наименование производителя:"));
                        if (brandName.isEmpty()) {
                            DisplayReadWriter.write(List.of("Недопустимо пустое имя!"));
                            return "";
                        }
                        equipmentService.updateBrandName(this.id, brandName);
                        DisplayReadWriter.write(List.of("Наименование производителя обновлено!"));
                    }
                    case "2" -> {
                        String serialNumber = DisplayReadWriter.writeAndRead(List.of("Введите корректный серийный номер:"));
                        if (serialNumber.isEmpty()) {
                            DisplayReadWriter.write(List.of("Недопустим пустой серийный номер!"));
                            return "";
                        }
                        equipmentService.updateSerialNumber(this.id, Integer.parseInt(serialNumber));
                        DisplayReadWriter.write(List.of("Серийный номер обновлен!"));
                    }
//                    case "3" -> {
//                        System.out.println("Введите корректный тип устройства%n1 - Монитор%n2 - Мышка%n3 - Системный блок");
//                        String deviceType = ConsoleScanner.IN.nextLine();
//                        if (deviceType.isEmpty()) {
//                            System.out.println("Недопустим пустой тип устройства!");
//                        }
//                        EquipmentMenu.getInstance().getEquipmentService().updateDeviceType(this.id, deviceType);
//                        System.out.println("Тип устройства обновлен!");
//                    }
                    default -> DisplayReadWriter.write(List.of("Команды не существует. Попробуйте еще раз!"));
                }
            }
            //Удалить технику
            case "4" -> {
                equipmentService.showAllEquipments();
                DisplayReadWriter.write(List.of("Удаляю текущую технику...", "Проверяю закреплена ли она за пользователем..."));
                //TODO проверку на закрепленность
                equipmentService.deleteById(this.id);
                MainMenu.getInstance().setCursorObject(null);
                DisplayReadWriter.write(List.of("Техника удалена", "Перехожу в главное меню"));
            }
            //Выход в главное меню
            case "9" -> {
                return "BACK";
            }
            //Выход из программы
            case "0" -> {
                return "EXIT";
            }
            default -> System.out.println("Команды не существует. Попробуйте еще раз!");
        }
        return "";
    }
}
