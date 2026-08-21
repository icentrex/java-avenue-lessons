package mentor1.model;

import mentor1.menu.Cursoring;
import mentor1.menu.MainMenu;

import java.util.List;
import java.util.Objects;

public abstract class Equipment implements Cursoring {
    private int id;
    private String brandName;
    private int serialNumber;
    private int userId;
    //private User currentUser;

    public Equipment(String brandName, int serialNumber) {
        this.brandName = brandName;
        this.serialNumber = serialNumber;
        this.userId = 0;
        //currentUser = null;
    }

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
        MainMenu.getInstance().getDisplayReadWriter().write(List.of("Закреплена за пользователем:"));
        MainMenu.getInstance().getEquipmentService().showAssignedUserByEquipmentId(this.id);
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
        switch (commandNumber) {
            //Закрепить технику
            case "1" -> {
                List<User> usersList = MainMenu.getInstance().getUserService().getUsersList();
                if (usersList.isEmpty()) {
                    MainMenu.getInstance().getDisplayReadWriter().write(List.of("Список пользователей пуст"));
                    return "";
                }

                List<String> formatted = usersList.stream()
                        .map(user -> String.format("userId = %s, name = %s, phone = %s%n",
                                user.getId(),
                                user.getName(),
                                user.getPhone()))
                        .toList();
                MainMenu.getInstance().getDisplayReadWriter().write(formatted);

                String userId = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of("Введите id пользователя:"));
                MainMenu.getInstance().getEquipmentService().assignEquipment(Integer.parseInt(userId), this.id);
            }
            //Открепить технику
            case "2" -> {
                if (!MainMenu.getInstance().getEquipmentService().detachEquipment(this.id)) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Не удалось открепить технику. Техническая ошибка"));
                }

                MainMenu.getInstance().getDisplayReadWriter()
                        .write(List.of("Техника откреплена"));
            }
            //Изменить технику
            case "3" -> {
                String choice = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndRead(List.of(
                                "Что хотите скорректировать?%n" +
                                        "1 - Производителя%n" +
                                        "2 - Серийный номер%n" +
                                        "3 - Тип устройства(в разработке)"));
                switch (choice) {
                    case "1" -> {
                        String brandName = MainMenu.getInstance().getDisplayReadWriter()
                                .writeAndRead(List.of("Введите корректное наименование производителя:"));
                        if (brandName.isEmpty()) {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Недопустимо пустое имя!"));
                            return "";
                        }
                        MainMenu.getInstance().getEquipmentService().updateBrandName(this.id, brandName);
                        MainMenu.getInstance().getDisplayReadWriter()
                                .write(List.of("Наименование производителя обновлено"));
                    }
                    case "2" -> {
                        String serialNumber = MainMenu.getInstance().getDisplayReadWriter()
                                .writeAndRead(List.of("Введите корректный серийный номер:"));
                        if (serialNumber.isEmpty()) {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Недопустим пустой серийный номер"));
                            return "";
                        }
                        MainMenu.getInstance().getEquipmentService().updateSerialNumber(this.id, Integer.parseInt(serialNumber));
                        MainMenu.getInstance().getDisplayReadWriter()
                                .write(List.of("Серийный номер обновлен"));
                    }
                    default -> MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Команды не существует. Попробуйте еще раз!"));
                }
            }
            //Удалить технику
            case "4" -> {
                MainMenu.getInstance().getDisplayReadWriter()
                        .write(List.of("Удаляю текущую технику...Проверяю закреплена ли она за пользователем..."));
                //TODO проверку на закрепленность
                MainMenu.getInstance().getEquipmentService().deleteEquipmentById(this.id);
                MainMenu.getInstance().setCursorObject(null);
                MainMenu.getInstance().getDisplayReadWriter()
                        .write(List.of("Техника удалена", "Перехожу в главное меню"));
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