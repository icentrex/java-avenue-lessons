package lesson11.hw1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*
        Задание 1: Определение типов данных
        Для каждого из приведенных ниже выражений определите, к какому типу данных оно относится (примитивному или ссылочному):
         */
        int x = 10;
        System.out.println("Примитивный тип данных для работы с целыми числами в диапазоне от -2^31 до 2^31-1: " + x);
        String str = "Hello";
        System.out.println("Ссылочный тип данных для работы со строками: " + str);
        boolean isTrue = true;
        System.out.println("Примитивный тип данных для работы с логическими переменными (true/false): " + isTrue);
        double d = 3.14;
        System.out.println("Примитивный тип данных для работы с числами с плавающей точкой (двойная точность): " + d);
        //Integer i = new Integer(20); подчеркивает красным второй Integer
        //'Integer(int)' is deprecated since version 9 and marked for removal
        //Внес правки
        Integer i = Integer.valueOf(20);
        System.out.println("Ссылочный тип данных (обертка) для работы с целыми числами: " + i);
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("Ссылочный тип данных для работы с динамическими массивами: " + list);
        char c = 'a';
        System.out.printf("Примитивный тип данных для работы с символами: '%c'%n", c);
        float f = 2.718f;
        System.out.println("Примитивный тип данных для работы с числами с плавающей точкой (одинарная точность): " + f);

    }
}
