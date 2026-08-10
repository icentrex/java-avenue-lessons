package mentor1.menu;

import java.util.List;
import java.util.Scanner;

public class ConsoleDisplay implements DisplayReadWriter {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void write(List<String> text) {
        for (String string : text) {
            System.out.println(string);
        }
    }

    @Override
    public String writeAndRead(List<String> text) {
        write(text);
        return scanner.nextLine();
    }
}
