package lesson34.hw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Path file = Path.of("/Users/aleafoninser/IdeaProjects/group55/src/lesson34/hw/testFile.txt");

        List<String> lines = List.of(
                "Первая строка текста",
                "Вторая строка текста",
                "Третья строка текста"
        );

        try {
            createFile(file);
            writeToFile(file, lines);
            readAndCount(file);

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    private static void createFile(Path file) throws IOException {
        System.out.println("Создание файла");
        if (!Files.exists(file)) {
            Files.createFile(file);
            System.out.println("Файл создан");
        } else {
            System.out.println("Файл уже существует");
        }
    }

    private static void writeToFile(Path file, List<String> content) throws IOException {
        System.out.println("Запись данных в файл");
        Files.write(file, content);
        System.out.println("Данные успешно записаны");
    }

    public static void readAndCount(Path file) throws IOException {
        System.out.println("Читаю файл в одну строку, считаю слова");
        String[] content = Files.readString(file).trim().split("\\s+");
        System.out.println("Количество слов: " + content.length);
    }
}
