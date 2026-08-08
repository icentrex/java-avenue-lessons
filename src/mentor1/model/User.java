package mentor1.model;

import mentor1.menu.Cursoring;
import mentor1.menu.DisplayReadWriter;
import mentor1.menu.MainMenu;
import mentor1.service.UserService;

import java.util.List;
import java.util.Objects;

public class User implements Cursoring, DisplayReadWriter {
    private int id;
    private String name;
    private String phoneNumber;
    //так норм делать? или оставить метод инит?
    //так не получиться. Мы пытаемся получиться инстанс которого еще нет.
//    private final UserService userService = MainMenu.getInstance().getUserService();
    private UserService userService;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void init() {
        MainMenu menu = MainMenu.getInstance();
        userService = menu.getUserService();
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber);
    }

    @Override
    public String getInfo() {
        return "\n=== Меню \"Выбранный пользователь\" ===\nИнформация о пользователе:"
                + String.format("userId = %s, name = %s, phone = %s%n", this.id, this.name, this.phoneNumber);
    }

    @Override
    public String getCommands() {
        init();
        DisplayReadWriter.write(List.of("Закрепленная техника:"));
        DisplayReadWriter.write(userService.getUserEquipments(this.id));
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
                DisplayReadWriter.write(userService.getFreeEquipments());
                String equipmentId = DisplayReadWriter.writeAndRead(List.of("Введите id техники:"));

                if (userService.assignEquipment(this.id, Integer.parseInt(equipmentId)).equalsIgnoreCase("200 OK")) {
                    DisplayReadWriter.write(List.of("Техника закреплена!"));
                } else {
                    DisplayReadWriter.write(List.of("Ошибка при закреплении техники!"));
                }
            }
            //Открепить технику
            case "2" -> {
                DisplayReadWriter.write(userService.getUserEquipments(this.id));
                String equipmentId = DisplayReadWriter.writeAndRead(List.of("Введите id техники:"));

                if (userService.detachEquipment(Integer.parseInt(equipmentId)).equalsIgnoreCase("200 OK")) {
                    DisplayReadWriter.write(List.of("Техника откреплена!"));
                } else {
                    DisplayReadWriter.write(List.of("Ошибка при откреплении техники!"));
                }
            }
            //Изменить пользователя
            case "3" -> {
                String choice = DisplayReadWriter.writeAndRead(List.of("Что хотите скорректировать?\n1 - Имя\n2 - Телефон"));
                switch (choice) {
                    case "1" -> {
                        String name = DisplayReadWriter.writeAndRead(List.of("Введите корректное имя пользователя:"));
                        if (name.isEmpty()) {
                            DisplayReadWriter.write(List.of("Недопустимо пустое имя!"));
                            return "";
                        }

                        if (userService.updateName(this.id, name).equalsIgnoreCase("200 OK")) {
                            DisplayReadWriter.write(List.of("Имя обновлено!"));
                        } else {
                            DisplayReadWriter.write(List.of("Ошибка при обновлении имени!"));
                        }
                    }
                    case "2" -> {
                        String phone = DisplayReadWriter.writeAndRead(List.of("Введите корректный телефон пользователя:"));
                        if (phone.isEmpty()) {
                            DisplayReadWriter.write(List.of("Недопустим пустой телефон!"));
                            return "";
                        }

                        if (userService.updatePhone(this.id, phone).equalsIgnoreCase("200 OK")) {
                            DisplayReadWriter.write(List.of("Телефон обновлен!"));
                        } else {
                            DisplayReadWriter.write(List.of("Ошибка при обновлении телефонв!"));
                        }
                    }
                    default -> DisplayReadWriter.write(List.of("Команды не существует. Попробуйте еще раз!"));
                }
            }
            //Удалить пользователя
            case "4" -> {
                DisplayReadWriter.write(List.of("Удаляю текущего пользователя...", "Проверяю есть ли закрепленная техника..."));

                if (userService.deleteByUserId(this.id).equalsIgnoreCase("200 OK")) {
                    DisplayReadWriter.write(List.of("Закрепленная техника отсутствует!", "Пользователь удален", "Перехожу в главное меню"));
                } else {
                    DisplayReadWriter.write(List.of("Удалить нельзя. За пользователем закреплена техника!"));
                }
            }
            //Выход в главное меню
            case "9" -> {
                return "BACK";
            }
            //Выход из программы
            case "0" -> {
                return "EXIT";
            }
            default -> DisplayReadWriter.write(List.of("Команды не существует. Попробуйте еще раз!"));
        }
        return "";
    }
}
