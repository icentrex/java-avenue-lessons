package lesson13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Создадим новый список");
//        List<Integer> list = new LinkedList<>();
//
//        System.out.println("\nadd(E element): Добавляет элемент в список на определенной позиции.");
//        list.add(1);
//        list.add(8);
//        list.add(2);
//        list.add(4);
//        list.add(99);
//        System.out.println(list);
//
//        System.out.println("\nadd(int index, E element): Добавляет элемент в список на определенной позиции.");
//        list.add(2, 15);
//        System.out.println(list);
//
//        System.out.println("\nremove(int index): Удаляет элемент на определенной позиции.");
//        list.remove(3);
//        System.out.println(list);
//
//        System.out.println("\nset(int index, E element): Заменяет элемент на указанной позиции новым значением.");
//        list.set(0, 99);
//        System.out.println(list);
//
//        System.out.println("\nget(int index): Возвращает элемент на данной позиции.");
//        System.out.println(list.get(0));
//
//        System.out.println("\nindexOf(Object o): Находит индекс первого вхождения данного объекта в списке.");
//        System.out.println(list.indexOf(99));
//
//        System.out.println("\nlastIndexOf(Object o): Находит последний индекс вхождения данного объекта в списке.");
//        System.out.println(list.lastIndexOf(99));
//
//        System.out.println("\ncontains(Object o): Проверяет, содержит ли список данный объект.");
//        System.out.println(list.contains(99));
//
//        System.out.println("\nclear(): Очищает список, удаляя все его элементы.");
//        list.clear();
//
//        System.out.println(list.contains(99));


        List<Integer> a = new ArrayList<>(List.of(1, 4, 6, 1));
        System.out.println("a = " + a);
        List<Integer> b = new ArrayList<>(List.of(1, 3, 4, 5));
        System.out.println("b = " + b);

        CollectionUtilsImpl collectionUtils = new CollectionUtilsImpl();
        System.out.println("union: " + collectionUtils.union(a, b));
        System.out.println("intersection: " + collectionUtils.intersection(a, b));
        System.out.println("difference = " + collectionUtils.difference(a, b));
        System.out.println("unionWithoutDuplicate: " + collectionUtils.unionWithoutDuplicate(a, b));
        System.out.println("intersectionWithoutDuplicate: " + collectionUtils.intersectionWithoutDuplicate(a, b));


    }
}
