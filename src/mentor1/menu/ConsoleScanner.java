package mentor1.menu;

import java.util.Scanner;

public class ConsoleScanner {
    //Проблема с использованием. В классах в которых используется не видно зависимости.
    // Лучше передавать в конструкторе
    public static final Scanner IN = new Scanner(System.in);

    //Чтобы не создавались инстансы, чтобы был один сканер на все меню
    private ConsoleScanner() {

    }
}
