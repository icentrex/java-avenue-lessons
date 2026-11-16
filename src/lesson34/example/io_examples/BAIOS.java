package lesson34.example.io_examples;

import java.io.ByteArrayInputStream;

public class BAIOS {
    public static void main(String[] args) {
        byte[] mas = new byte[]{1,2,3,4,5,6};
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mas);
        int i;
        while ((i = byteArrayInputStream.read()) != -1){
            System.out.println(i);
        }
    }
}
