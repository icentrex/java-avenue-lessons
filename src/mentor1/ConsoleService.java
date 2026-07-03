package mentor1;

public class ConsoleService {
    //Вывод доступных команд
    //Считывание выбора пользователя
    //Вывод информации

    public void commandList(){
        System.out.println("""
                Доступные команды:
                create - создать нового пользователя
                delete - удалить пользователя
                info - информация о пользователе
                all - показать всех пользователей
                exit - выход""");
    }
}
