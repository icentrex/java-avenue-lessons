package lesson34;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class FileOperationsDemo {
    private static final Path WORK_DIRECTORY = Path.of("test_directory");
    private static final Path FILE = WORK_DIRECTORY.resolve("testFile5.txt");

    public static void main(String[] args) {

        try {
            String content = "Hello world";
            //Path path = Paths.get("testFile.txt");
            //Files.createFile(path);

            //createDirectory(WORK_DIRECTORY);
            //createFile(FILE);
            //writeToFile(Path.of("newFile.txt"), content);
            //printFileInfo(Path.of("newFile.txt"));
            //renameFile(Path.of("newFile.txt"), Path.of("superNewFile.txt"));
            //checkExists(Path.of("superNewFile.txt"), Path.of("testFile1.txt"));
            // System.out.println("----");
            //checkExists(Path.of("superNewFile.txt"));
            //listDirectory(Path.of("test_directory"));
            deleteDirectory(Path.of("test_directory"));

            deleteFile(Path.of("testFile5.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createDirectory(Path dir) throws IOException {
        System.out.println("Создание директории");
        if (!Files.exists(dir)) {
            Files.createDirectory(dir);
            System.out.println("Директория создана");
        } else {
            System.out.println("Директория уже существует");
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

    private static void writeToFile(Path file, String content) throws IOException {
        System.out.println("Запись данных в файл");
        Files.writeString(file, content);
        System.out.println("Данные успешно записаны");
    }

    private static void printFileInfo(Path file) throws IOException {
        System.out.println("Чтение инфомрации");
        System.out.println(file.getFileName());
        System.out.println(Files.size(file));
        System.out.println(Files.isReadable(file));
        System.out.println(Files.isWritable(file));
        System.out.println(Files.isExecutable(file));
        System.out.println(Files.isHidden(file));
        System.out.println(file.toAbsolutePath());
    }

    private static void renameFile(Path from, Path to) throws IOException {
        System.out.println("Переименование файла");
        System.out.println(from.getFileName());
        Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
        System.out.println(to.getFileName());
        System.out.println("Переименование завершили");
    }

    private static void checkExists(Path... paths) {
        System.out.println("Првоерка существования файлов");
        for (Path p : paths) {
            System.out.println(p.getFileName() + " " + Files.exists(p));
        }
    }

    private static void listDirectory(Path directory) {
        System.out.println("Отобразить содержимое директории");
        try (Stream<Path> stream = Files.list(directory)) {
            stream.forEach(path -> {
                try {
                    System.out.println(path.getFileName() + " " + Files.size(path));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteFile(Path file) throws IOException {
        System.out.println("Удаление файла");
        Files.deleteIfExists(file);
        System.out.println("Удалили файл");
    }

    private static void deleteDirectory(Path directory) throws IOException {
        System.out.println("Удаление директории");
        Files.deleteIfExists(directory);
        System.out.println("Удалили директорию");
    }
}
