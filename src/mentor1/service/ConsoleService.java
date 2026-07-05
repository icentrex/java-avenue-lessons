package mentor1.service;

import mentor1.Cursoring;

public class ConsoleService {
    //Вывод доступных команд
    //Считывание выбора пользователя
    //Вывод информации

public Cursoring cursor;

    public void commandList(){
        System.out.println("""
                Доступные команды:
                create - создать нового пользователя
                delete - удалить пользователя
                info - информация о пользователе
                all - показать всех пользователей
                exit - выход""");
    }

    public writeInfo() {
        System.out.println(cursor.wtiteInfo());
    }


}
