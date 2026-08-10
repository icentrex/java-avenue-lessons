package mentor1.model;

import mentor1.Cursoring;
import mentor1.menu.DisplayReadWriter;
import mentor1.menu.MainMenu;
import mentor1.service.UserService;

import java.util.List;
import java.util.Objects;

public class User implements Cursoring {
    private final String id;
    private static int nextId = 0;
    private String name;
    private String phoneNumber;
    private MainMenu menu;
    private DisplayReadWriter display;
    private UserService userService;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = nextId + name.substring(0, 1).toLowerCase() + phoneNumber.charAt(0);
        nextId++;
    }

    public void init() {
        if (menu == null) {
            menu = MainMenu.getInstance();
            display = menu.getDisplay();
            userService = menu.getUserService();
        }
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
        init();
        System.out.println("Информация о пользователе:");
        display.write(MainMenu.getInstance().getUserService().getInfo(this.id));
        System.out.println("Закрепленная техника:");
        display.write(MainMenu.getInstance().getUserService().getUserEquipments(this.id));
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
        init();
        switch (commandNumber) {
            //Закрепить технику
            case "1" -> {
                display.write(userService.getFreeEquipments());
                String equipmentId = display.writeAndRead(List.of("Введите id техники:"));
                userService.assignEquipment(this.id, Integer.parseInt(equipmentId));
                display.write(List.of("Техника закреплена!"));
            }
            //Открепить технику
            case "2" -> {
                display.write(userService.getUserEquipments(this.id));
                String equipmentId = display.writeAndRead(List.of("Введите id техники:"));
                userService.detachEquipment(Integer.parseInt(equipmentId));
                display.write(List.of("Техника откреплена!"));
            }
            //Изменить пользователя
            case "3" -> {
                String choice = display.writeAndRead(List.of("Что хотите скорректировать?\n1 - Имя\n2 - Телефон"));
                switch (choice) {
                    case "1" -> {
                        String name = display.writeAndRead(List.of("Введите корректное имя пользователя:"));
                        if (name.isEmpty()) {
                            display.write(List.of("Недопустимо пустое имя!"));
                            return "";
                        }
                        userService.updateName(this.id, name);
                        display.write(List.of("Имя обновлено!"));
                    }
                    case "2" -> {
                        String phone = display.writeAndRead(List.of("Введите корректный телефон пользователя:"));
                        if (phone.isEmpty()) {
                            display.write(List.of("Недопустим пустой телефон!"));
                            return "";
                        }
                        userService.updatePhone(this.id, phone);
                        display.write(List.of("Телефон обновлен!"));
                    }
                    default -> display.write(List.of("Команды не существует. Попробуйте еще раз!"));
                }
            }
            //Удалить пользователя
            case "4" -> {
                display.write(List.of("Удаляю текущего пользователя...", "Проверяю есть ли закрепленная техника..."));
                display.write(userService.deleteByUserId(this.id));
            }
            //Выход в главное меню
            case "9" -> {
                return "BACK";
            }
            //Выход из программы
            case "0" -> {
                return "EXIT";
            }
            default -> display.write(List.of("Команды не существует. Попробуйте еще раз!"));
        }
        return "";
    }
}
