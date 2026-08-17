package lesson14;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создадим новое множество");
        Set<Integer> set = new TreeSet<>();
        System.out.println(set);

        set.add(4);
        set.add(1);
        set.add(8);
        set.add(2);
        set.add(99);
        set.add(2);
        set.add(3);
        set.add(6);
        set.add(3);
        set.add(15);
        set.add(2);

        System.out.println("\n" + set);

        System.out.println("\nsize(): Возвращает количество элементов в множестве.");
        System.out.println(set.size());

        System.out.println("\nisEmpty(): Проверяет, пусто ли множество.");
        System.out.println(set.isEmpty());

        System.out.println("\nremove(Object o): Удаляет элемент из множества, если элемент присутствует.");
        set.remove(99);
        System.out.println("\n" + set);

        System.out.println("contains(Object o): Проверяет, содержится ли элемент в множестве.");
        System.out.println(set.contains(99) ? "присутствует" : "отсутствует");

        System.out.println("Удаляем все четные числа");
//        for (Integer number : set) {
//            if (number % 2 == 0) {
//               set.remove(number);
//            }
//        }

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            if (number % 2 == 0) {
                iterator.remove();
            }
        }

        System.out.println(set);
    }
}