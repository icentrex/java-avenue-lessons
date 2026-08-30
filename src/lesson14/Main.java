package lesson14;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создадим новое множество");
        Set<Integer> integerSet = new TreeSet<>();
        System.out.println(integerSet);

        integerSet.add(4);
        integerSet.add(1);
        integerSet.add(8);
        integerSet.add(2);
        integerSet.add(99);
        integerSet.add(2);
        integerSet.add(3);
        integerSet.add(6);
        integerSet.add(3);
        integerSet.add(15);
        integerSet.add(2);

        System.out.println("\n" + integerSet);

        System.out.println("\nsize(): Возвращает количество элементов в множестве.");
        System.out.println(integerSet.size());

        System.out.println("\nisEmpty(): Проверяет, пусто ли множество.");
        System.out.println(integerSet.isEmpty());

        System.out.println("\nremove(Object o): Удаляет элемент из множества, если элемент присутствует.");
        integerSet.remove(99);
        System.out.println("\n" + integerSet);

        System.out.println("contains(Object o): Проверяет, содержится ли элемент в множестве.");
        System.out.println(integerSet.contains(99) ? "присутствует" : "отсутствует");

        System.out.println("Удаляем все четные числа");
//        for (Integer number : integerSet) {
//            if (number % 2 == 0) {
//               integerSet.remove(number);
//            }
//        }

        Iterator<Integer> iterator = integerSet.iterator();
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            if (number % 2 == 0) {
                iterator.remove();
            }
        }

        System.out.println(integerSet);
    }
}