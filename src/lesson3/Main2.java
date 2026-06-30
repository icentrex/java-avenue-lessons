package lesson3;

public class Main2 {
    public static void main(String[] args) {
        /*
         *Задача 2. Вывести предложение “Я Java разработчик" наоборот (задом наперед)" в одну строку.
         */

        String stringStraight = "Я Java разработчик";

        for (int charPosition = stringStraight.length() - 1; charPosition >= 0; charPosition--) {
            System.out.print(stringStraight.charAt(charPosition));
        }
    }
}
