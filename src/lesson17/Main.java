package lesson17;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Coin coin1 = new Coin(5, 1999, "Золото", 2.5);
//        Coin coin2 = new Coin(5, 1999, "Золото", 2.5);
//        Coin coin3 = new Coin(10, 1999, "Золото", 2.5);
//        Coin coin4 = new Coin(50, 1899, "Олово", 5);
//        Coin coin5 = new Coin(5, 1869, "Серебро", 10);
//        Coin coin6 = new Coin(5, 1869, "Палладий", 10);
//        Coin coin7 = new Coin(15, 1859, "Золото", 10);
//        Coin coin8 = new Coin(5, 1869, "Палладий", 11);
//
//        List<Coin> coinArray = List.of(coin1, coin2, coin3, coin4, coin5, coin6, coin7, coin8);

        Coin coin1  = new Coin(50, 1899, "Олово", 15.0);
        Coin coin2  = new Coin(20, 1960, "Олово", 15.0);
        Coin coin3  = new Coin(5,  1869, "Палладий", 11.0);
        Coin coin4  = new Coin(5,  1869, "Золото", 11.0);
        Coin coin5  = new Coin(5,  1869, "Серебро", 10.0);
        Coin coin6  = new Coin(5,  1869, "Палладий", 10.0);
        Coin coin7  = new Coin(3,  1869, "Палладий", 10.0);
        Coin coin8  = new Coin(25, 1850, "Олово", 5.0);
        Coin coin9  = new Coin(10, 1850, "Олово", 5.0);
        Coin coin10 = new Coin(10, 1850, "Латунь", 5.0);
        Coin coin11 = new Coin(10, 1899, "Олово", 5.0);
        Coin coin12 = new Coin(1,  1999, "Золото", 2.5);
        Coin coin13 = new Coin(1,  1865, "Золото", 2.5);
        Coin coin14 = new Coin(1,  1950, "Серебро", 2.5);
        Coin coin15 = new Coin(1,  1950, "Золото", 2.5);
        Coin coin16 = new Coin(1,  1865, "Серебро", 2.5);

        List<Coin> coinArray = List.of(
                coin1, coin2, coin3, coin4, coin5, coin6, coin7, coin8,
                coin9, coin10, coin11, coin12, coin13, coin14, coin15, coin16);

        System.out.println("Вариант 1 (на уроке) - Используем сортировку по умолчанию Comparable");
        Set<Coin> coinSet = new TreeSet<>(coinArray);

        for (Coin coin : coinSet) {
            System.out.println(coin);
        }

        System.out.println("\nВариант 2 (на уроке) - Используем сортировку по внешнему SortByYearComparator");
        Set<Coin> coinSet2 = new TreeSet<>(new SortByYearComparator());
        coinSet2.addAll(coinArray);

        for (Coin coin : coinSet2) {
            System.out.println(coin);
        }

        System.out.println("\nВариант 3 (на уроке) - Используем сортировку по внешнему Comparator (анонимый класс)");
        Set<Coin> coinSet3 = new TreeSet<>(new Comparator<>() {
            @Override
            public int compare(Coin o1, Coin o2) {
                //По номиналу по возрастанию
                if (o2.getNominal() != o1.getNominal()) {
                    return o1.getNominal() - o2.getNominal();
                }

                //По году по убыванию
                if (o1.getYear() != o2.getYear()) {
                    return o2.getYear() - o1.getYear();
                }

                //По диаметру по убыванию
                return Double.compare(o2.getDiameter(), o1.getDiameter());
            }
        });

        coinSet3.addAll(coinArray);

        for (Coin coin : coinSet3) {
            System.out.println(coin);
        }

        System.out.println("\nВариант 4 (ДЗ) - Используем сортировку по внешнему SortByDiameterComparator");
        Set<Coin> coinSet4 = new TreeSet<>(new SortByDiameterComparator());
        coinSet4.addAll(coinArray);

        for (Coin coin : coinSet4) {
            System.out.println(coin);
        }

        System.out.println("\nВариант 5 (ДЗ) - Используем сортировку по внешнему Comparator (анонимый класс)");
        Set<Coin> coinSet5 = new TreeSet<>((o1, o2) -> {
            //По металлу по убыванию
            if (!o2.getMetalName().equalsIgnoreCase(o1.getMetalName())) {
                return o2.getMetalName().compareTo(o1.getMetalName());
            }

            //По номиналу по убыванию
            if (o2.getNominal() != o1.getNominal()) {
                return o2.getNominal() - o1.getNominal();
            }

            //По диаметру по возрастанию
            return Double.compare(o1.getDiameter(), o2.getDiameter());
        });

        coinSet5.addAll(coinArray);

        for (Coin coin : coinSet5) {
            System.out.println(coin);
        }
    }
}
