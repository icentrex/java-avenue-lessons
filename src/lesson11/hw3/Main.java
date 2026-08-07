package lesson11.hw3;

public class Main {
    public static void main(String[] args) {
        /*
        Задание 3: Работа с классами-обёртками
        Создайте программу, которая демонстрирует работу с классами-обёртками.
        Программа должна выполнять следующее:
        Создать объект класса Integer с помощью конструктора и методом valueOf().
        Использовать метод equals() для сравнения двух объектов Integer.
        Применить метод parseInt() для преобразования строки в целое число.
         */
        //Integer i = new Integer(20); подчеркивает красным второй Integer
        //'Integer(int)' is deprecated since version 9 and marked for removal
        //Внес правки
        //Integer number1 = new Integer(23);
        Integer number1 = 24;
        System.out.println("Число 1 = " + number1);
        Integer number2 = Integer.valueOf(45);
        System.out.println("Число 2 = " + number2);

        System.out.println("Сравниваем числа...");
        if (number1.equals(number2)) {
            System.out.println("Числа равны!");
        } else {
            System.out.println("Числа не равны!");
        }

        String string = "2000";
        System.out.println("Строка = \"" + string + "\"");
        int string2int = Integer.parseInt(string);
        System.out.println("Строка в целое число = " + string2int);

    }
}
