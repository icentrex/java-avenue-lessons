package mentor1.model;

import mentor1.Cursoring;
import mentor1.menu.ConsoleScanner;
import mentor1.menu.UserMenu;

import java.util.Objects;

public class User implements Cursoring {
    private final String id;
    private static int nextId = 0;
    private String name;
    private String phoneNumber;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = nextId + name.substring(0, 1).toLowerCase() + phoneNumber.charAt(0);
        nextId++;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getId() {
        return id;
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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber);
    }

    @Override
    public String getInfo() {
        return "\n=== Меню \"Выбранный пользователь\" ===";
    }

    @Override
    public String getCommands() {
        System.out.println("Информация о пользователе:");
        UserMenu.getInstance().getUserService().showInfo(this.id);
        System.out.println("Закрепленная техника:");
        UserMenu.getInstance().getUserService().showUserEquipments(this.id);
        System.out.println();
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
                UserMenu.getInstance().getUserService().showFreeEquipments();
                System.out.println("Введите id техники:");
                String equipmentId = ConsoleScanner.IN.nextLine();
                UserMenu.getInstance().getUserService().assignEquipment(this.id, Integer.parseInt(equipmentId));
                System.out.println("Техника закреплена!");
            }
            //Открепить технику
            case "2" -> {
                UserMenu.getInstance().getUserService().showUserEquipments(this.id);
                System.out.println("Введите id техники:");
                String equipmentId = ConsoleScanner.IN.nextLine();
                UserMenu.getInstance().getUserService().detachEquipment(Integer.parseInt(equipmentId));
                System.out.println("Техника откреплена!");
            }
            //Изменить пользователя
            case "3" -> {
                System.out.println("Что хотите скорректировать?\n1 - Имя\n2 - Телефон");
                String choice = ConsoleScanner.IN.nextLine();
                switch (choice) {
                    case "1" -> {
                        System.out.println("Введите корректное имя пользователя:");
                        String name = ConsoleScanner.IN.nextLine();
                        if (name.isEmpty()) {
                            System.out.println("Недопустимо пустое имя!");
                            return "";
                        }
                        UserMenu.getInstance().getUserService().updateName(this.id, name);
                        System.out.println("Имя обновлено!");
                    }
                    case "2" -> {
                        System.out.println("Введите корректный телефон пользователя:");
                        String phone = ConsoleScanner.IN.nextLine();
                        if (phone.isEmpty()) {
                            System.out.println("Недопустим пустой телефон!");
                            return "";
                        }
                        UserMenu.getInstance().getUserService().updatePhone(this.id, phone);
                        System.out.println("Телефон обновлен!");
                    }
                    default -> System.out.println("Команды не существует. Попробуйте еще раз!");
                }
            }
            //Удалить пользователя
            case "4" -> {
                System.out.println("Удаляю текущего пользователя...");
                System.out.println("Проверяю есть ли закрепленная техника...");
                UserMenu.getInstance().getUserService().deleteByUserId(this.id);
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
