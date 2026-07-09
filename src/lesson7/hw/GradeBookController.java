    package lesson7.hw;

    import java.util.Scanner;

    public class GradeBookController {
        private GradeBookDB db = new GradeBookDB();
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
            System.out.println("1 - добавить оценку в журнал указанному студенту");
            System.out.println("2 - добавить оценку в журнал нескольким студентам)");
            System.out.println("3 - посмотреть все оценки в журнале");
            System.out.println("4 - посчитать среднюю оценку по всем студентам");
            System.out.println("0 - завершить программу");
        }

        public void processRequest(Scanner scanner) {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Введите имя студента");
                    String fullname = scanner.nextLine();
                    System.out.println("Введите оценку");
                    int grade = scanner.nextInt();
                    scanner.nextLine();
                    db.addGrade(fullname, grade);
                }
                case 2 -> {
                    System.out.println("Введите имена студентов через пробел");
                    String studentsFullnames = scanner.nextLine();
                    System.out.println("Введите оценки через пробел");
                    String grades = scanner.nextLine();
                    db.addSomeGrades(studentsFullnames, grades);
                }
                case 3 -> db.showGrades();
                case 4 -> System.out.printf("Средняя оценка: %.2f\n", db.calcAverageGrade());
                case 0 -> {
                    System.out.println("Программа завершила работу");
                    isNeedContinue = false;
                }
                default -> System.out.println("Неверный выбор!");
            }
            if (choice != 0) {
                System.out.println("Для продолжения нажмите Enter...");
                scanner.nextLine();
            }
        }
    }
