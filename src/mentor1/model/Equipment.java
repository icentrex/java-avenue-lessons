package mentor1.model;

import mentor1.Cursoring;
import mentor1.menu.MainMenu;
import mentor1.menu.ConsoleScanner;

import java.util.Objects;

public abstract class Equipment implements Cursoring {

    private final int id;
    private static int nextId = 0;
    private String brandName;
    private int serialNumber;
    private String userId;

    //private User currentUser;
    public Equipment(String brandName, int serialNumber) {
        this.id = nextId++;
        this.brandName = brandName;
        this.serialNumber = serialNumber;
        this.userId = "Не закреплена";
        //currentUser = null;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
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
        return this + "\n=== Меню \"Выбранное оборудование\" ===";
    }

    @Override
    public String getCommands() {
        System.out.println("Информация о технике:");
        // EquipmentMenu.getInstance().getEquipmentService().showEquipmentInfo(this.id);
        // Вывод Ситтем аут тут!
        System.out.println("Закреплена за пользователем:");
        MainMenu.getInstance().getEquipmentService().showAssignedUserByEquipmentId(this.id);
        // Систем аут брать из currentUser данного класса
        System.out.println();
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
        switch (commandNumber) {
            //Закрепить технику
            case "1" -> {
                MainMenu.getInstance().getEquipmentService().showUsersList();
                System.out.println("Введите id пользователя:");
                String userId = ConsoleScanner.IN.nextLine();
                MainMenu.getInstance().getEquipmentService().assignEquipment(userId, this.id);
            }
            //Открепить технику
            case "2" -> {
                System.out.println("Открепляю технику от пользователя...");
                MainMenu.getInstance().getEquipmentService().detachEquipment(this.id);
                System.out.println("Техника откреплена");
            }
            //Изменить технику
            case "3" -> {
                MainMenu.getInstance().getEquipmentService().showAllEquipments();
                System.out.println("Что хотите скорректировать?%n1 - Производителя%n2 - Серийный номер%n3 - Тип устройства(в разработке)");
                String choice = ConsoleScanner.IN.nextLine();
                switch (choice) {
                    case "1" -> {
                        System.out.println("Введите корректное наименование производителя:");
                        String brandName = ConsoleScanner.IN.nextLine();
                        if (brandName.isEmpty()) {
                            System.out.println("Недопустимо пустое имя!");
                            return "";
                        }
                        MainMenu.getInstance().getEquipmentService().updateBrandName(this.id, brandName);
                        System.out.println("Наименование производителя обновлено!");
                    }
                    case "2" -> {
                        System.out.println("Введите корректный серийный номер:");
                        String serialNumber = ConsoleScanner.IN.nextLine();
                        if (serialNumber.isEmpty()) {
                            System.out.println("Недопустим пустой серийный номер!");
                            return "";
                        }
                        MainMenu.getInstance().getEquipmentService().updateSerialNumber(this.id, Integer.parseInt(serialNumber));
                        System.out.println("Серийный номер обновлен!");
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
                    default -> System.out.println("Команды не существует. Попробуйте еще раз!");
                }
            }
            //Удалить технику
            case "4" -> {
                MainMenu.getInstance().getEquipmentService().showAllEquipments();
                System.out.println("Удаляю текущую технику...");
                System.out.println("Проверяю закреплена ли она за пользователем...");
                //TODO проверку на закрепленность
                MainMenu.getInstance().getEquipmentService().deleteById(this.id);
                System.out.println("Техника удалена");
                MainMenu.getInstance().setCursorObject(null);
                System.out.println("Перехожу в главное меню");


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
