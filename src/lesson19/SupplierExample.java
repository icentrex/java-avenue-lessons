package lesson19;

import java.util.Random;

public class SupplierExample {
    public static void main(String[] args) {
        System.out.println("Сгенерируем 10 случайных чисел от 1 до 999, только четные");
        for (int index = 1; index < 11; index++) {
            int number = new Random().nextInt(1, 1000);
            if (number % 2 == 0) {
                System.out.println(number);
            } else {
                index--;
            }
        }

        System.out.println("Сделаем то же самое с помощью supplier");

        RandomEvenNumberSupplier supplier = new RandomEvenNumberSupplier();
        for (int index = 0; index < 10; index++) {
            System.out.println(supplier.get());
        }
    }
}
