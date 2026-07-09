package mentor1.console;

import mentor1.Cursoring;
import mentor1.repository.EquipmentRepository;
import mentor1.service.UserService;

import java.util.Scanner;

public class ConsoleService implements Cursoring {
    //Вывод доступных команд
    //Считывание выбора пользователя
    //Вывод информации

    public UserService userService; // переделать Сервисы под Синглтоны?
    public EquipmentRepository equipmentRepository; // Нужно заменить на EquipmentService
    public Cursoring cursorObject;

    public ConsoleService(UserService userService, EquipmentRepository equipmentRepository) {
        this.userService = userService;
        this.equipmentRepository = equipmentRepository;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(this.getInfo());
            System.out.println(this.getCommands());
            String input = scanner.nextLine();
            System.out.println(this.execute(input));
        }
    }


    @Override
    public String getInfo() {
        return this.cursorObject != null
                ? this.cursorObject.getInfo()
                : "\n---===Программа \"Учет техники v1.0\"===---\nВыберите пункт:";
    }

    @Override
    public String getCommands() {
        return this.cursorObject != null
                ? this.cursorObject.getCommands()
                : """
                Доступные команды:
                1 - Получить список Пользователей
                2 - Получить список Техники
                3 - выход
                """;
    }

    @Override
    public String execute(String commandNumber) {
        switch (commandNumber) {
            case "1" -> {
                this.cursorObject = this.userService;
                break;
            }
            case "2" -> {
                //this.cursorObject = this.equipmentRepository; // Нужно заменить на EquipmentService
                break;
            }
        }
        if (cursorObject != null) {
            return this.cursorObject.execute(commandNumber);
        }
        return "";
    }
}
