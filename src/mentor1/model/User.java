package mentor1.model;

import mentor1.Cursoring;
import mentor1.menu.ConsoleScanner;

import java.util.Objects;

public class User implements Cursoring {
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
        return "\n=== Меню \"Пользователь\" ===";
    }

    @Override
    public String getCommands() {
        return """
                Доступные команды:
                1 - Закрепить технику
                2 - Открепить технику
                9 - Назад в главное меню
                0 - Выход из программы
                """;
    }

    @Override
    public String execute(String commandNumber) {
        switch (commandNumber) {
            case "1" -> {
                System.out.println("Введите id пользователя:");
                String userId = ConsoleScanner.IN.nextLine();
                //userService.assignEquipment();
            }
            case "2" -> {
                System.out.println("Введите id пользователя:");
                String userId = ConsoleScanner.IN.nextLine();
                //userService.detachEquipment();
            }
            case "9" -> {
                return "BACK";
            }
            case "0" -> {
                return "EXIT";
            }
            default -> System.out.println("Команды не существует. Попробуйте еще раз!");
        }
        return "";
    }
}
