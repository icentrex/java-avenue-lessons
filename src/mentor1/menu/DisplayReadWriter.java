package mentor1.menu;

import java.util.List;
import java.util.Scanner;

public interface DisplayReadWriter {
    Scanner scanner = new Scanner(System.in);

    static void write(List<String> text) {
        for (String string : text) {
            System.out.println(string);
        }
    }

    static String writeAndRead(List<String> text) {
        write(text);
        return read();
    }

    static String read() {
        return scanner.nextLine();
    }
}
