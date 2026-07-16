package mentor1.menu;

import mentor1.Cursoring;

public class ConsoleMainMenu implements Cursoring {
    private final UserMenu userMenu; // переделать Сервисы под Синглтоны?
    private final EquipmentMenu equipmentMenu;
    private Cursoring cursorObject;
    private boolean isNeedContinue = true;

    public ConsoleMainMenu(UserMenu userMenu, EquipmentMenu equipmentMenu) {
        this.userMenu = userMenu;
        this.equipmentMenu = equipmentMenu;
    }

    public void run() {
        while (isNeedContinue) {
            System.out.println(this.getInfo());
            System.out.println(this.getCommands());
            String input = ConsoleScanner.IN.nextLine();
            System.out.println(this.execute(input));
        }
    }

    @Override
    public String getInfo() {
        return this.cursorObject != null
                ? this.cursorObject.getInfo()
                : "\n=== Программа \"Учет техники v1.0\" ===\n";
    }

    @Override
    public String getCommands() {
        return this.cursorObject != null
                ? this.cursorObject.getCommands()
                : """
                Доступные команды:
                1 - Меню "Пользователи"
                2 - Меню "Техника"
                0 - Выход из программы
                """;
    }

    @Override
    public String execute(String commandNumber) {
        if (cursorObject != null) {
            String result = this.cursorObject.execute(commandNumber);

            if (result.isEmpty()) {
                return "";
            }

            if (result.equals("BACK")) {
                this.cursorObject = null;
                return "";
            } else if (result.equals("EXIT")) {
                this.isNeedContinue = false;
                return "Программа завершена!";
            } else if (result.startsWith("USER")) {
                this.cursorObject = this.userMenu.findUserById(Integer.parseInt(result.substring(4)));
                return "";
            }
            return result;
        }

        switch (commandNumber) {
            case "1" -> this.cursorObject = this.userMenu;
            case "2" -> this.cursorObject = this.equipmentMenu;
            case "0" -> {
                this.isNeedContinue = false;
                return "Программа завершена!";
            }
            default -> System.out.println("Команды не существует. Попробуйте еще раз!");
        }
        return "";
    }
}
