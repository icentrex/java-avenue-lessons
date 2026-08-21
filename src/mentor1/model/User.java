package mentor1.model;

import mentor1.menu.Cursoring;
import mentor1.menu.DisplayReadWriter;
import mentor1.menu.MainMenu;
import mentor1.service.UserService;

import java.util.List;
import java.util.Objects;

public class User implements Cursoring {
    private int id;
    private String name;
    private String phone;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void showUserEquipments() {
        List<Equipment> userEquipments = MainMenu.getInstance().getEquipmentService().getUserEquipments(this.id);
        if (userEquipments.isEmpty()) {
            MainMenu.getInstance().getDisplayReadWriter().write(List.of("У пользователя нет техники"));
            return;
        }

        List<String> formatted = userEquipments.stream()
                .map(equipment -> String.format("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                        equipment.getId(),
                        equipment.getBrandName(),
                        equipment.getSerialNumber(),
                        equipment.getUserId()))
                .toList();
        MainMenu.getInstance().getDisplayReadWriter().write(formatted);
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
        MainMenu.getInstance().getDisplayReadWriter().write(List.of("Закрепленная техника:"));
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
        switch (commandNumber) {
            //Закрепить технику
            case "1" -> {
                List<Equipment> freeEquipments = MainMenu.getInstance().getEquipmentService().getFreeEquipments();
                if (freeEquipments.isEmpty()) {
                    MainMenu.getInstance().getDisplayReadWriter().write(List.of("Нет свободной техники"));
                    return "";
                }

                List<String> formatted = freeEquipments.stream()
                        .map(equipment -> String.format("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                                equipment.getId(),
                                equipment.getBrandName(),
                                equipment.getSerialNumber(),
                                equipment.getUserId()))
                        .toList();
                MainMenu.getInstance().getDisplayReadWriter().write(formatted);

                String equipmentId = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Введите id техники:"));
                if (MainMenu.getInstance().getEquipmentService().assignEquipment(this.id, Integer.parseInt(equipmentId))) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Техника закреплена"));
                } else {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Ошибка. Техника не найдена"));
                }
            }
            //Открепить технику
            case "2" -> {
                showUserEquipments();
                String equipmentId = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Введите id техники:"));

                if (MainMenu.getInstance().getEquipmentService().detachEquipment(Integer.parseInt(equipmentId))) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Техника откреплена"));
                } else {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Ошибка. Техника не найдена"));
                }
            }
            //Изменить пользователя
            case "3" -> {
                String choice = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Что хотите скорректировать?\n1 - Имя\n2 - Телефон"));
                switch (choice) {
                    case "1" -> {
                        String name = MainMenu.getInstance().getDisplayReadWriter()
                                .writeAndRead(List.of("Введите корректное имя пользователя:"));
                        if (name.isEmpty()) {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Недопустимо пустое имя!"));
                            return "";
                        }

                        if (MainMenu.getInstance().getUserService().updateUserName(this.id, name)) {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Имя обновлено"));
                        } else {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Ошибка. Пользователь не найден"));
                        }
                    }
                    case "2" -> {
                        String phone = MainMenu.getInstance().getDisplayReadWriter()
                                .writeAndRead(List.of("Введите корректный телефон пользователя:"));
                        if (phone.isEmpty()) {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Недопустим пустой телефон"));
                            return "";
                        }

                        if (MainMenu.getInstance().getUserService().updateUserPhone(this.id, phone)) {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Телефон обновлен"));
                        } else {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Пользователь с таким номером телефона уже существует!"));
                        }
                    }
                    default -> MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Команды не существует. Попробуйте еще раз"));
                }
            }
            //Удалить пользователя
            case "4" -> {
                MainMenu.getInstance().getDisplayReadWriter()
                        .write(List.of("Удаляю текущего пользователя...Проверяю есть ли закрепленная техника..."));
                if (MainMenu.getInstance().getUserService().deleteUserById(this.id)) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Пользователь удален. Перехожу в главное меню"));
                    MainMenu.getInstance().setCursorObject(null);
                } else {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Удалить нельзя. За пользователем закреплена техника"));
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
            default -> MainMenu.getInstance().getDisplayReadWriter()
                    .write(List.of("Команды не существует. Попробуйте еще раз!"));
        }
        return "";
    }
}
