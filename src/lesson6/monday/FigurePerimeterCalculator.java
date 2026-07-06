package lesson6.monday;

import java.util.Scanner;

public class FigurePerimeterCalculator {
    public static void main(String[] args) {
        System.out.println("Добрый день! Я калькулятор периметров фигур.");
        System.out.println("Выберите фигуру: ");
        System.out.println("1 - треугольник");
        System.out.println("2 - квадрат");
        System.out.println("3 - круг");
        System.out.println("4 - трапеция");
        System.out.println("5 - овал");

        //Scanner - java класс, который имеет функционал работы с консолью на чтение и запись
        //System.in - на вход, читаем ввод
        //System.out - на выход, пишем в консоль
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();

        if (userChoice == 1) {
            System.out.println("Введите сторону A");
            double a = scanner.nextDouble();
            System.out.println("Введите сторону B");
            double b = scanner.nextDouble();
            System.out.println("Введите сторону C");
            double c = scanner.nextDouble();
            System.out.println("Периметр треугольника: " + MathUtils.calculateTrianglePerimeter(a, b, c));
        } else if (userChoice == 2) {
            System.out.println("Введите длинну стороны");
            double a = scanner.nextDouble();
            System.out.println("Периметр квадрата: " + MathUtils.calculateSquarePerimeter(a));
        } else if (userChoice == 3) {
            System.out.println("Введите радиус окружности r");
            double r = scanner.nextDouble();
            System.out.printf("Длинна окружности: %.5f", MathUtils.calculateCirclePerimeter(r));
        } else if (userChoice == 4) {
            System.out.println("Введите сторону A");
            double a = scanner.nextDouble();
            System.out.println("Введите сторону B");
            double b = scanner.nextDouble();
            System.out.println("Введите сторону C");
            double c = scanner.nextDouble();
            System.out.println("Введите сторону D");
            double d = scanner.nextDouble();
            System.out.println("Периметр трапеции: " + MathUtils.calculateTrapezoidPerimeter(a, b, c, d));
        } else if (userChoice == 5) {
            System.out.println("Введите длину большой оси D");
            double D = scanner.nextDouble();
            System.out.println("Введите длину малой оси d");
            double d = scanner.nextDouble();
            System.out.printf("Периметр овала: %.5f", MathUtils.calculateEllipsePerimeter(D, d));
        } else {
            System.out.println("Некорректная фигура");
        }
    }
}