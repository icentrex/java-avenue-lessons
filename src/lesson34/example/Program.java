package lesson34.example;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите возраст");
        int age = scanner.nextInt();
        try {
            createPerson(name, age);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) throws Exception {
            if (name.length() < 3) throw new Exception("Слишком короткое имя");
            if (age < 0) throw new Exception("Возраст не может быть отрицательным");
            this.name = name;
            this.age = age;
        }

        void print() {
            System.out.println(name + " " + age);
        }
    }

    static void createPerson(String name, int age) throws Exception {
        Person person = new Person(name, age);
    }
}
