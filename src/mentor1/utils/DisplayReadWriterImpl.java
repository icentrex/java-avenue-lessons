package mentor1.utils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DisplayReadWriterImpl implements DisplayReadWriter {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void write(List<String> text) {
        for (String string : text) {
            System.out.println(string);
        }
    }

    @Override
    public String writeAndRead(List<String> text) {
        write(text);
        return read();
    }

    @Override
    public Optional<Integer> writeAndReadInt(List<String> text) {
        String input = writeAndRead(text).trim();
        if (input.isEmpty()) {
            write(List.of("Поле не может быть пустым"));
            return Optional.empty();
        }

        try {
            return Optional.of(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            write(List.of("Введите целое число"));
            return Optional.empty();
        }
    }

    public String read() {
        return scanner.nextLine();
    }
}
