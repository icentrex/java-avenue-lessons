package mentor1.model;

import mentor1.Cursoring;
import mentor1.menu.ConsoleScanner;
import mentor1.menu.EquipmentMenu;
import mentor1.menu.UserMenu;

import java.util.Objects;

public class User implements Cursoring {
    //переписать репозиторий и остальное
    private Integer id;
    private String name;
    private String phoneNumber;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }

    @Override
    public String getInfo() {
        return this + "\n=== Меню \"Выбранный пользователь\" ===";
    }

    @Override
    public String getCommands() {
        return """
                Доступные команды:
                1 - Закрепить технику
                2 - Открепить технику
                3 - Изменить пользователя
                4 - Удалить пользователя
                9 - Назад в главное меню
                0 - Выход из программы
                """;
    }

    @Override
    public String execute(String commandNumber) {
        switch (commandNumber) {
            //Закрепить технику
            case "1" -> {
                EquipmentMenu.getInstance().getEquipmentService().showAll();
                System.out.println("Введите id техники:");
                String equipmentId = ConsoleScanner.IN.nextLine();
                int currentUserId = UserMenu.getInstance().getUserService().findUserIdByPhone(this.phoneNumber);
                UserMenu.getInstance().getUserService().assignEquipment(currentUserId, Integer.parseInt(equipmentId));
            }
            //Открепить технику
            case "2" -> {
                EquipmentMenu.getInstance().getEquipmentService().showAll();
                System.out.println("Введите id техники:");
                String equipmentId = ConsoleScanner.IN.nextLine();
                UserMenu.getInstance().getUserService().detachEquipment(Integer.parseInt(equipmentId));
            }
            //Изменить пользователя
            case "3" -> {
                System.out.println("Введите корректное имя пользователя:");
                String name = ConsoleScanner.IN.nextLine();
                System.out.println("Введите корректный телефон пользователя:");
                String phone = ConsoleScanner.IN.nextLine();
                UserMenu.getInstance().getUserService().update(name, phone);
            }
            //Удалить пользователя
            case "4" -> {
                UserMenu.getInstance().getUserService().showAll();
                System.out.println("Введите id пользователя:");
                String userId = ConsoleScanner.IN.nextLine();
                UserMenu.getInstance().getUserService().deleteById(Integer.parseInt(userId));
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
