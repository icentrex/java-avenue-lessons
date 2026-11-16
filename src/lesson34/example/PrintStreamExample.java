package lesson34.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamExample {
    public static void main(String[] args) {
        String str = "Hello world";
        try (FileOutputStream fileOutputStream = new FileOutputStream("testFile2.txt");
             PrintStream printStream = new PrintStream(fileOutputStream);
        ) {
            printStream.println(str);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
