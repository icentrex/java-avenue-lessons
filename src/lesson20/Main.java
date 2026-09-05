package lesson20;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Олег", 1, 18, "Россия"));
        students.add(new Student("Дмитрий", 1, 19, "Россия"));
        students.add(new Student("Владислав", 2, 20, "Беларусь"));
        students.add(new Student("Ольга", 1, 20, "Россия"));
        students.add(new Student("Джон", 2, 20, "Америка"));
        students.add(new Student("Иван", 1, 22, "Казахстан"));
        students.add(new Student("Акмал", 1, 18, "Казахстан"));
        students.add(new Student("Олег", 1, 18, "Россия"));
        students.add(new Student("Олег", 1, 18, "Россия"));
        students.add(new Student("Олег", 1, 18, "Россия"));
        students.add(new Student("Евгения", 3, 22, "Россия"));
        students.add(new Student("Олег", 1, 18, "Россия"));
        students.add(new Student("Олег", 1, 18, "Россия"));
        students.add(new Student("Алена", 2, 20, "Молдова"));
        students.add(new Student("Олег", 1, 18, "Россия"));
        students.add(new Student("Олег", 1, 18, "Россия"));
        students.add(new Student("Олег", 1, 18, "Россия"));

        System.out.println("На основе полученных данных из списка list найти сколько уникальных студентов учиться в ВУЗЕ.");
        long quantityOfStudents = students
                .stream()
                .distinct()
                .count();
        System.out.println(quantityOfStudents);

        System.out.println("На основе полученных данных из списка students: " +
                "\n1) Найти всех студентов из России " +
                "\n2) Отсортировать по порядку по имени " +
                "\n3) Преобразовать в короткую версию студента" +
                "\n4) ограничиться только первыми двумя" +
                "\n5) вывести всех в консоль");
        students
                .stream()
                .filter(student -> student.getCountry().equalsIgnoreCase("Россия"))
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .map(student -> new StudentShort(student.getName(), student.getAge()))
                .limit(2)
                .forEach(studentShort -> System.out.println(studentShort));

        System.out.println("Найдите уникальные имена и соберите их в новую коллекцию");
        Set<String> uniqueStudentNames = students
                .stream()
                .map(student -> student.getName())
                .collect(Collectors.toSet());
        System.out.println(uniqueStudentNames);

        System.out.println("На основе полученных данных из списка всех студентов, найти всех студентов, которые:" +
                "\n1) Не из Америки" +
                "\n2) При этом старше 10 лет" +
                "\n3) Если такие есть, вывести любого в консоль");
        students
                .stream()
                .filter(student -> !student.getCountry().equalsIgnoreCase("Америка"))
                .filter(student -> student.getAge() > 10)
                .findAny()
                .ifPresent(student -> System.out.println(student));
    }
}
