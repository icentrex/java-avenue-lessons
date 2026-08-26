package lesson17;

import com.sun.source.tree.Tree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Coin coin1 = new Coin(5, 1999, "Золото", 2.5);
        Coin coin2 = new Coin(5, 1999, "Золото", 2.5);
        Coin coin3 = new Coin(10, 1999, "Золото", 2.5);
        Coin coin4 = new Coin(50, 1899, "Олово", 5);
        Coin coin5 = new Coin(5, 1869, "Серебро", 10);
        Coin coin6 = new Coin(5, 1869, "Палладий", 10);
        Coin coin7 = new Coin(5, 1869, "Палладий", 11);

        System.out.println("Вариант 1 - Используем сортировку по умолчанию Comparable");
        Set<Coin> coinSet = new TreeSet<>();
        coinSet.addAll(List.of(coin1, coin2, coin3, coin4, coin5, coin6, coin7));

        for (Coin coin : coinSet) {
            System.out.println(coin);
        }

        System.out.println("\nВариант 2 - Используем сортировку по внешнему сравнивателю Comparator");
        Set<Coin> coinSet2 = new TreeSet<>(new SortByYearComparator());
        coinSet2.addAll(List.of(coin1, coin2, coin3, coin4, coin5, coin6, coin7));

        for (Coin coin : coinSet2) {
            System.out.println(coin);
        }

        System.out.println("\nВариант 3 - Используем сортировку по внешнему сравнивателю Comparator (анонимый класс)");
        Set<Coin> coinSet3 = new TreeSet<>(new Comparator<Coin>() {
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

        coinSet3.addAll(List.of(coin1, coin2, coin3, coin4, coin5, coin6, coin7));

        for (Coin coin : coinSet3) {
            System.out.println(coin);
        }
    }
}
