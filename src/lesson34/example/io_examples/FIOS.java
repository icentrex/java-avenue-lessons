package lesson34.example.io_examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FIOS {
    public static void main(String[] args) {
        //Избавиться от трейса и обработать грамотно ошибку

        //String str = "Hello world";
        //try {
        //    FileOutputStream fileOutputStream = new FileOutputStream("testFile.txt");
        //    byte[] strByte = str.getBytes();
        //    fileOutputStream.write(strByte);
        //    System.out.println("Файл записан");
        //} catch (FileNotFoundException e) {
        //    throw new RuntimeException(e);
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}

        //try(FileInputStream fileInputStream = new FileInputStream("testFile.txt")) {
        //
        //    int i;
        //    while ((i = fileInputStream.read()) != -1) {
        //        System.out.println(i);
        //    }
        //} catch (FileNotFoundException e) {
        //    throw new RuntimeException(e);
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}

        try (FileInputStream fileInputStream = new FileInputStream("testFile.txt");
             FileOutputStream fileOutputStream = new FileOutputStream("testFile.txt")) {


            while ((fileInputStream.read() != -1)) {
                System.out.println(fileInputStream.read());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
