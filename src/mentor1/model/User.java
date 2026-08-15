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
    private String phone;
    private UserService userService;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void init() {
        userService = MainMenu.getInstance().getUserService();
    }

    public void showUserEquipments() {
        List<Equipment> userEquipments = userService.getUserEquipments(this.id);
        if (userEquipments.isEmpty()) {
            DisplayReadWriter.write(List.of("У пользователя нет техники"));
            return;
        }

        List<String> formatted = userEquipments.stream()
                .map(equipment -> String.format("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                        equipment.getId(),
                        equipment.getBrandName(),
                        equipment.getSerialNumber(),
                        equipment.getUserId()))
                .toList();
        DisplayReadWriter.write(formatted);
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone);
    }

    @Override
    public String getInfo() {
        return "\n=== Меню \"Выбранный пользователь\" ===\nИнформация о пользователе:"
                + String.format("userId = %s, name = %s, phone = %s%n", this.id, this.name, this.phone);
    }

    @Override
    public String getCommands() {
        init();
        DisplayReadWriter.write(List.of("Закрепленная техника:"));
        showUserEquipments();
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
                List<Equipment> freeEquipments = MainMenu
                        .getInstance()
                        .getEquipmentService().getFreeEquipments();
                if (freeEquipments.isEmpty()) {
                    DisplayReadWriter.write(List.of("Нет свободной техники"));
                    return "";
                }

                List<String> formatted = freeEquipments.stream()
                        .map(equipment -> String.format("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                                equipment.getId(),
                                equipment.getBrandName(),
                                equipment.getSerialNumber(),
                                equipment.getUserId()))
                        .toList();
                DisplayReadWriter.write(formatted);

                String equipmentId = DisplayReadWriter.writeAndRead(List.of("Введите id техники:"));
                if (userService.assignEquipment(this.id, Integer.parseInt(equipmentId))) {
                    DisplayReadWriter.write(List.of("Техника закреплена"));
                } else {
                    DisplayReadWriter.write(List.of("Ошибка. Техника не найдена"));
                }
            }
            //Открепить технику
            case "2" -> {
                showUserEquipments();
                String equipmentId = DisplayReadWriter.writeAndRead(List.of("Введите id техники:"));

                if (userService.detachEquipment(Integer.parseInt(equipmentId))) {
                    DisplayReadWriter.write(List.of("Техника откреплена"));
                } else {
                    DisplayReadWriter.write(List.of("Ошибка. Техника не найдена"));
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

                        if (userService.updateUserName(this.id, name)) {
                            DisplayReadWriter.write(List.of("Имя обновлено"));
                        } else {
                            DisplayReadWriter.write(List.of("Ошибка. Пользователь не найден"));
                        }
                    }
                    case "2" -> {
                        String phone = DisplayReadWriter.writeAndRead(List.of("Введите корректный телефон пользователя:"));
                        if (phone.isEmpty()) {
                            DisplayReadWriter.write(List.of("Недопустим пустой телефон"));
                            return "";
                        }

                        if (userService.updateUserPhone(this.id, phone)) {
                            DisplayReadWriter.write(List.of("Телефон обновлен"));
                        } else {
                            DisplayReadWriter.write(List.of("Пользователь с таким номером телефона уже существует!"));
                        }
                    }
                    default -> DisplayReadWriter.write(List.of("Команды не существует. Попробуйте еще раз"));
                }
            }
            //Удалить пользователя
            case "4" -> {
                DisplayReadWriter.write(List.of("Удаляю текущего пользователя...", "Проверяю есть ли закрепленная техника..."));
                if (userService.deleteUserById(this.id)) {
                    DisplayReadWriter.write(List.of("Пользователь удален, перехожу в главное меню"));
                    MainMenu.getInstance().setCursorObject(null);
                } else {
                    DisplayReadWriter.write(List.of("Удалить нельзя. За пользователем закреплена техника"));
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
