package lesson7.hw;

import java.util.Scanner;

public class GradeBookController {
    private final GradeBookDB db = new GradeBookDB();
    private boolean isNeedContinue = true;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (isNeedContinue) {
            printMenu();
            processRequest(scanner);
        }
        scanner.close();
    }

    public void printMenu() {
        System.out.println("\n--==Программа \"Электронный журнал 1.0\"==--");
        System.out.println("1 - Добавить оценку в журнал студенту");
        System.out.println("2 - Добавить оценку в журнал нескольким студентам");
        System.out.println("3 - Посмотреть все оценки в журнале");
        System.out.println("4 - Посчитать среднюю оценку по всем студентам");
        System.out.println("0 - Завершить программу");
    }

    public void processRequest(Scanner scanner) {
        System.out.println("\nВведите пункт меню:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                db.showStudents();
                System.out.println("Введите идентификатор студента:");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите оценку (1-5):");
                int grade = Integer.parseInt(scanner.nextLine());
                if (grade < 1 || grade > 5) {
                    System.out.println("Оценка должна быть от 1 до 5");
                    break;
                }
                db.addGrade(id, grade);
            }
            case 2 -> {
                db.showStudents();
                System.out.println("Введите идентификаторы студентов через пробел:");
                String studentsId = scanner.nextLine();
                System.out.println("Введите оценки через пробел:");
                String grades = scanner.nextLine();
                db.addSeveralGrades(studentsId, grades);
            }
            case 3 -> db.showGrades();
            case 4 -> System.out.printf("Средняя оценка: %.2f\n", db.calcAverageGrade());
            case 0 -> {
                System.out.println("Программа завершила работу.");
                isNeedContinue = false;
            }
            default -> System.out.println("Неверный выбор!");
        }
        if (choice != 0) {
            System.out.println("\nДля продолжения нажмите Enter...");
            scanner.nextLine();
        }
    }
}
