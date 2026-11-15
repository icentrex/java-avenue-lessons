package mentor1.model;

import mentor1.Main;
import mentor1.service.UpdateResult;
import mentor1.utils.Menu;
import mentor1.menu.MainMenu;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class User implements Menu {
    private static final String USER_NOT_FOUND_MESSAGE = "Ошибка. Пользователь не найден";
    private static final String PHONE_DUPLICATE_MESSAGE = "Такой телефон уже занят другим пользователем";
    private static final String INVALID_DATA_MESSAGE = "Недопустимые данные";
    private int id;
    private String name;
    private String phone;

    public User(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public User(User other) {
        this.id = other.id;
        this.name = other.name;
        this.phone = other.phone;
    }

    private void writeUpdateResult(UpdateResult result, String successMessage) {
        String message = switch (result) {
            case SUCCESS -> successMessage;
            case DUPLICATE -> PHONE_DUPLICATE_MESSAGE;
            case NOT_FOUND -> USER_NOT_FOUND_MESSAGE;
            case INVALID_DATA -> INVALID_DATA_MESSAGE;
        };
        MainMenu.getInstance().getDisplayReadWriter().write(List.of(message));
    }

    public void showUserEquipments() {
        List<Equipment> userEquipments = MainMenu.getInstance().getEquipmentService().getUserEquipments(this.id);
        if (userEquipments.isEmpty()) {
            MainMenu.getInstance().getDisplayReadWriter().write(List.of("У пользователя нет техники"));
            return;
        }

        List<String> formatted = userEquipments.stream()
                .map(equipment -> String.format("id = %d, name = %s, serialNumber = %d, user = %s%n",
                        equipment.getId(),
                        equipment.getBrandName(),
                        equipment.getSerialNumber(),
                        equipment.getUser()))
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
        return "\n=== Меню \"Выбранный пользователь\" ===\nИнформация о пользователе: "
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
                9 - Назад
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
                        .map(equipment -> String.format("id = %d, type = %s, name = %s, serialNumber = %d, userId = %s%n",
                                equipment.getId(),
                                equipment.getEquipmentType().getName(),
                                equipment.getBrandName(),
                                equipment.getSerialNumber(),
                                equipment.getUser()))
                        .toList();
                MainMenu.getInstance().getDisplayReadWriter().write(formatted);

                Optional<Integer> equipmentId = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndReadInt(List.of("Введите id техники:"));

                if (equipmentId.isEmpty()) {
                    return "";
                }

                if (MainMenu.getInstance().getEquipmentService().assignEquipment(this, equipmentId.get())) {
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
                Optional<Integer> equipmentId = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndReadInt(List.of("Введите id техники:"));

                if (equipmentId.isEmpty()) {
                    return "";
                }

                if (MainMenu.getInstance().getEquipmentService().detachEquipment(equipmentId.get())) {
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
                        .writeAndRead(List.of(
                                """ 
                                        Что хотите скорректировать?
                                        1 - Имя
                                        2 - Телефон
                                        """));
                switch (choice) {
                    case "1" -> {
                        String correctName = MainMenu.getInstance().getDisplayReadWriter()
                                .writeAndRead(List.of("Введите корректное имя пользователя:"));
                        if (correctName.isEmpty()) {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Недопустимо пустое имя!"));
                            return "";
                        }

                        User candidate = new User(this);
                        candidate.setName(correctName);

                        UpdateResult result = MainMenu.getInstance().getUserService().updateUser(candidate);
                        if (result == UpdateResult.SUCCESS) {
                            this.setName(correctName);
                        }
                        writeUpdateResult(result, "Имя обновлено");
                    }
                    case "2" -> {
                        String correctPhone = MainMenu.getInstance().getDisplayReadWriter()
                                .writeAndRead(List.of("Введите корректный телефон пользователя:"));
                        if (correctPhone.isEmpty()) {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Недопустим пустой телефон"));
                            return "";
                        }

                        User candidate = new User(this);
                        candidate.setPhone(correctPhone);

                        UpdateResult result = MainMenu.getInstance().getUserService().updateUser(candidate);
                        if (result == UpdateResult.SUCCESS) {
                            this.setPhone(correctPhone);
                        }
                        writeUpdateResult(result, "Телефон обновлен");
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
                            .write(List.of("Пользователь удален. Перехожу в меню пользователей"));
                    return "BACK";
                } else {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Удалить нельзя. За пользователем закреплена техника"));
                }
            }
            //Выход в меню пользователей
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
