package lesson11.hw2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*
        Задание 2: Преобразования типов
        Напишите код, который выполняет следующие преобразования:
        Преобразуйте целое число int в строку String.
        Преобразуйте строку String, содержащую числовое значение, в целочисленный тип int.
        Преобразуйте вещественное число double в целое число int с потерей дробной части.
        *Преобразуйте массив целых чисел int[] в список ArrayList<Integer> с использованием автоупаковки.
        */
        int number = 4323;
        System.out.println("Число = " + number);

        String int2string = String.valueOf(number);
        System.out.println("Число в строку = \"" + int2string + "\"");

        int string2int = Integer.parseInt(int2string);
        System.out.println("Строку обратно в число = " + string2int);

        double dNumber = 123.4455;
        System.out.println("Вещественное число = " + dNumber);

        int double2int = (int) dNumber;
        System.out.println("Вещественное число в целое = " + double2int);

        int[] numbers = {1, 3, 4, 55, 33, 66, 77, 88};
        System.out.print("Массив целых чисел: ");
        for (int i : numbers) {
            System.out.print(i + " ");
        }
        System.out.println();

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int j : numbers) {
            integerArrayList.add(j);
        }

        System.out.print("Автоупакованный массив целых чисел в ArrayList<Integer>: ");
        for (Integer i : integerArrayList) {
            System.out.print(i + " ");
        }
    }
}
