package mentor1.model;

import mentor1.Cursoring;
import mentor1.menu.ConsoleScanner;
import mentor1.menu.EquipmentMenu;

import java.util.Objects;

public abstract class Equipment implements Cursoring{
    private String name;
    private int serialNumber;
    private int userId;

    public Equipment(String name, int serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.userId = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "name='" + name + '\'' +
                ", serialNumber=" + serialNumber +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return serialNumber == equipment.serialNumber && userId == equipment.userId && Objects.equals(name, equipment.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, serialNumber, userId);
    }

    @Override
    public String getInfo() {
        return this + "\n=== Меню \"Выбранное оборудование\" ===";
    }

    @Override
    public String getCommands() {
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
                System.out.println("Введите id пользователя:");
                String userId = ConsoleScanner.IN.nextLine();
                //UserMenu.getInstance().getUserService().assignEquipment(userId);
            }
            //Открепить технику
            case "2" -> {
                System.out.println("Введите id пользователя:");
                String userId = ConsoleScanner.IN.nextLine();
                //UserMenu.getInstance().getUserService().detachEquipment(userId);
            }
            //Изменить технику
            case "3" -> {
                EquipmentMenu.getInstance().getEquipmentService().showAll();
                System.out.println("Введите корректное наименование производителя:");
                String name = ConsoleScanner.IN.nextLine();
                System.out.println("Введите корректный серийный номер:");
                String number = ConsoleScanner.IN.nextLine();
                EquipmentMenu.getInstance().getEquipmentService().update(name, Integer.parseInt(number));
            }
            //Удалить технику
            case "4" -> {
                EquipmentMenu.getInstance().getEquipmentService().showAll();
                System.out.println("Введите id оборудования:");
                String equipmentId = ConsoleScanner.IN.nextLine();
                EquipmentMenu.getInstance().getEquipmentService().deleteById(Integer.parseInt(equipmentId));

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
