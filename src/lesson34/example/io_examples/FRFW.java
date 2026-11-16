package lesson34.example.io_examples;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FRFW {
    public static void main(String[] args) {
        //try (FileWriter fileWriter = new FileWriter("testFile3.txt")) {
        //    String text = "Hello world";
        //    fileWriter.write(text);
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}

        try (FileReader fileReader = new FileReader("testFile3.txt")) {
            int temp;
            while ((temp = fileReader.read()) != -1) {
                System.out.println((char)temp);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
