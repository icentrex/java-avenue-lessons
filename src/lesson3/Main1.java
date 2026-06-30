package lesson3;

public class Main1 {
    public static void main(String[] args) {
        /*
        Задача 1. Есть массив чисел от 1 до 30. Выводить в консоль все четные числа, кроме 6, 10, 14.
        Как только цикл дойдет до 23, остановить вывод и написать в консоль “достигнуто целевое число!”.
         */
        int[] numbers = new int[30];

        //заполняем массив
        for (int index = 0; index < numbers.length; index++) {
            numbers[index] = index + 1;
        }

        //Вывод в консоль
        for (int index = 0; index < numbers.length; index++) {
            int number = numbers[index];
            if (number == 23) {
                System.out.println("Достигнуто целевое число!");
                break;
            }

            if (number % 2 != 0) {
                continue;
            }

            if (number == 6 || number == 10 || number == 14) {
                continue;
            }

            System.out.println(number);
        }
    }
}
