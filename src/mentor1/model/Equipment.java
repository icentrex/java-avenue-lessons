package mentor1.model;

import mentor1.service.UpdateResult;
import mentor1.utils.Menu;
import mentor1.menu.MainMenu;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Equipment implements Menu {
    private static final String EQUIPMENT_NOT_FOUND_MESSAGE = "Ошибка. Техника не найдена";
    private static final String SERIAL_DUPLICATE_MESSAGE = "Техника с таким серийным номером уже существует";
    private static final String INVALID_DATA_MESSAGE = "Недопустимые данные";
    private int id;
    private String brandName;
    private int serialNumber;
    private EquipmentType equipmentType;
    private User user;

    public Equipment(int id, EquipmentType equipmentType, String brandName, int serialNumber, User user) {
        this.id = id;
        this.equipmentType = equipmentType;
        this.brandName = brandName;
        this.serialNumber = serialNumber;
        this.user = user;
    }

    public Equipment(EquipmentType equipmentType, String brandName, int serialNumber) {
        this.equipmentType = equipmentType;
        this.brandName = brandName;
        this.serialNumber = serialNumber;
        this.user = null;
    }

    public Equipment(Equipment other) {
        this.id = other.id;
        this.brandName = other.brandName;
        this.serialNumber = other.serialNumber;
        this.equipmentType = other.equipmentType;
        this.user = other.user;
    }

    private void writeUpdateResult(UpdateResult result, String successMessage) {
        String message = switch (result) {
            case SUCCESS -> successMessage;
            case NOT_FOUND -> EQUIPMENT_NOT_FOUND_MESSAGE;
            case DUPLICATE -> SERIAL_DUPLICATE_MESSAGE;
            case INVALID_DATA -> INVALID_DATA_MESSAGE;
        };
        MainMenu.getInstance().getDisplayReadWriter().write(List.of(message));
    }

    public String getBrandName() {
        return this.brandName;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public User getUser() {
        return user;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public int getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", serialNumber=" + serialNumber +
                ", equipmentType=" + equipmentType +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return id == equipment.id && serialNumber == equipment.serialNumber && Objects.equals(brandName, equipment.brandName) && Objects.equals(equipmentType, equipment.equipmentType) && Objects.equals(user, equipment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandName, serialNumber, equipmentType, user);
    }

    @Override
    public String getInfo() {
        return "\n=== Меню \"Выбранное оборудование\" ===\nИнформация о технике: " +
                String.format("id = %d, type = %s, name = %s, serialNumber = %d, userId = %s%n",
                        this.id,
                        this.equipmentType,
                        this.brandName,
                        this.serialNumber,
                        this.user);
    }

    @Override
    public String getCommands() {
        MainMenu.getInstance().getDisplayReadWriter().write(List.of("Закреплена за пользователем:"));
        if (this.user == null) {
            MainMenu.getInstance().getDisplayReadWriter()
                    .write(List.of("Техника не закреплена"));
        } else {
            MainMenu.getInstance().getDisplayReadWriter()
                    .write(List.of(String.format("userId = %s, name = %s, phone = %s%n", user.getId(), user.getName(), user.getPhone())));
        }
        return """
                Доступные команды:
                1 - Закрепить технику
                2 - Открепить технику
                3 - Изменить технику
                4 - Удалить технику
                9 - Назад
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
                        .map(user -> String.format("userId = %s, name = %s, phone = %s", user.getId(), user.getName(), user.getPhone()))
                        .toList();
                MainMenu.getInstance().getDisplayReadWriter().write(formatted);

                Optional<Integer> userId = MainMenu.getInstance().getDisplayReadWriter()
                        .writeAndReadInt(List.of("Введите id пользователя:"));
                if (userId.isEmpty()) {
                    return "";
                }

                Optional<User> findUserResult = MainMenu.getInstance().getUserService().findUserById(userId.get());

                if (findUserResult.isEmpty()) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Пользователя с таким ID не существует"));
                    return "";
                }

                if (!MainMenu.getInstance().getEquipmentService().assignEquipment(findUserResult.get(), this.id)) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Не удалось закрепить технику. Техническая ошибка"));
                    return "";
                }
                MainMenu.getInstance().getDisplayReadWriter()
                        .write(List.of("Техника закреплена"));

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
                                """
                                        Что хотите скорректировать?
                                        1 - Производителя
                                        2 - Серийный номер
                                        3 - Тип устройства
                                        """));
                switch (choice) {
                    //Изменить производителя
                    case "1" -> {
                        String correctBrandName = MainMenu.getInstance().getDisplayReadWriter()
                                .writeAndRead(List.of("Введите корректное наименование производителя:"));
                        if (correctBrandName.isEmpty()) {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Недопустимо пустое имя!"));
                            return "";
                        }

                        Equipment candidate = new Equipment(this);
                        candidate.setBrandName(correctBrandName);

                        UpdateResult result = MainMenu.getInstance().getEquipmentService().updateEquipment(candidate);
                        if (result == UpdateResult.SUCCESS) {
                            this.setBrandName(correctBrandName);
                        }
                        writeUpdateResult(result, "Наименование производителя обновлено");
                    }
                    //Изменить серийный номер
                    case "2" -> {
                        Optional<Integer> correctSerialNumber = MainMenu.getInstance().getDisplayReadWriter()
                                .writeAndReadInt(List.of("Введите корректный серийный номер:"));
                        if (correctSerialNumber.isEmpty()) {
                            return "";
                        }

                        Equipment candidate = new Equipment(this);
                        candidate.setSerialNumber(correctSerialNumber.get());

                        UpdateResult result = MainMenu.getInstance().getEquipmentService().updateEquipment(candidate);
                        if (result == UpdateResult.SUCCESS) {
                            this.setSerialNumber(correctSerialNumber.get());
                        }
                        writeUpdateResult(result, "Серийный номер обновлен");
                    }
                    //Изменить тип оборудования
                    case "3" -> {
                        MainMenu.getInstance().getDisplayReadWriter().write(List.of("Существующие типы оборудования:"));
                        List<EquipmentType> equipmentTypesList = MainMenu.getInstance().getEquipmentService().getEquipmentTypesList();
                        MainMenu.getInstance().getDisplayReadWriter().write(equipmentTypesList
                                .stream()
                                .map(equipmentType -> String.format("id = %d, name = %s", equipmentType.getId(), equipmentType.getName()))
                                .toList());

                        Optional<Integer> correctEquipmentTypeId = MainMenu.getInstance().getDisplayReadWriter()
                                .writeAndReadInt(List.of("Выберите корректный тип оборудования (введите ID):"));

                        if (correctEquipmentTypeId.isEmpty()) {
                            return "";
                        }

                        int chosenTypeId = correctEquipmentTypeId.get();
                        Optional<EquipmentType> chosenTypeResult = equipmentTypesList.stream()
                                .filter(equipmentType -> equipmentType.getId() == chosenTypeId)
                                .findFirst();

                        if (chosenTypeResult.isEmpty()) {
                            MainMenu.getInstance().getDisplayReadWriter()
                                    .write(List.of("Тип оборудования с таким ID не найден"));
                            return "";
                        }

                        Equipment candidate = new Equipment(this);
                        candidate.setEquipmentType(chosenTypeResult.get());

                        UpdateResult result = MainMenu.getInstance().getEquipmentService().updateEquipment(candidate);
                        if (result == UpdateResult.SUCCESS) {
                            this.setEquipmentType(chosenTypeResult.get());
                        }
                        writeUpdateResult(result, "Тип оборудования обновлен");
                    }
                    default -> System.out.println("Команды не существует. Попробуйте еще раз!");
                }
            }
            //Удалить технику
            case "4" -> {
                MainMenu.getInstance().getDisplayReadWriter()
                        .write(List.of("Удаляю текущую технику...Проверяю закреплена ли она за пользователем..."));
                if (MainMenu.getInstance().getEquipmentService().deleteEquipmentById(this.id)) {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Техника удалена. Перехожу в меню техники"));
                    return "BACK";
                } else {
                    MainMenu.getInstance().getDisplayReadWriter()
                            .write(List.of("Удалить нельзя. Техника закреплена за пользователем"));
                }
            }
            //Выход в меню техники
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