package lesson2;

import java.text.DecimalFormatSymbols;

public class Main {
    public static void main(String[] args) {
        //Вы хотите разместить вклад в банке на сумму 1 миллион 200 тысяч рублей
        //Срок вклада 1 год
        //Вам предложено 2 варианта
        //Вариант 1 - с ежемесячной капитализацией по ставке 22%
        //Вариант 2 - с начислением процентов в конце срока по ставке 22,5%
        //1) Определить, какая сумма у вас накопится к концу сроку вклада по обоим вариантам
        //2) Выбрать наиболее выгодный
        //3) Вывести результаты в консоль
        double depositAmount = 1_200_000;
        int depositTermMonth = 12;
        double rateWithCapitalization = 22;
        double amountWithCapitalization = depositAmount;
        double rateWithoutCapitalization = 22.5;
        double amountWithoutCapitalization = depositAmount;

        //Рассчет по варианту 1
        for (int month = 1; month <= depositTermMonth; month++) {
            amountWithCapitalization += amountWithCapitalization *
                    (rateWithCapitalization / 12 / 100);
        }

        //Рассчет по варианту 2
        amountWithoutCapitalization += amountWithoutCapitalization * rateWithoutCapitalization / 12 / 100;

        System.out.printf("Ваш запрос: вклад в банке на сумму %,.2f рублей на %d месяц(-ев)\n", depositAmount,
                depositTermMonth);
        System.out.println("===");

        System.out.printf("Вариант 1 - с ежемесячной капитализацией по ставке %.1f%%\n",
                rateWithCapitalization);
        System.out.printf("Сумма к концу срока: %,.2f рублей.\n", amountWithCapitalization);
        System.out.println("---");

        System.out.printf("Вариант 2 - с начислением процентов в конце срока по ставке %.1f%%\n",
                rateWithoutCapitalization);
        System.out.printf("Сумма к концу срока: %,.2f рублей.\n", amountWithoutCapitalization);
        System.out.println("---");

        if (amountWithCapitalization > amountWithoutCapitalization) {
            System.out.println("Вам выгоднее взять первый вариант");
        } else if (amountWithCapitalization < amountWithoutCapitalization) {
            System.out.println("Вам выгоднее взять второй вариант");
        } else {
            System.out.println("Оба варианта одинаково выгодны");
        }
    }
}