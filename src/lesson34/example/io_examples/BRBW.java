package lesson34.example.io_examples;

import java.io.*;

public class BRBW {
    public static void main(String[] args) {
        //try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("testFile4.txt"))) {
        //    String str = "Hello world";
        //    bufferedWriter.write(str);
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("testFile4.txt"))) {
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                System.out.println(temp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
