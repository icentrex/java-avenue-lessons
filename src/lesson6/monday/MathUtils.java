package lesson6.monday;

/**
 * Создание метода
 * 1 - модификатор доступа
 * public - публичный, доступен внутри всего проекта
 * protected - доступ только внутри наследников
 * package - доступен только внутри пакета
 * private - доступен только внутри класса
 * <p>
 * 2 - static
 * обозначает статичность метода
 * <p>
 * 3 - возвращаемый тип
 * (void - ничего не возвращает или int, char, double и так далее)
 * <p>
 * 4 - название метода (строится в глагольной форме - calculate, print...)
 * <p>
 * 5 - входящие параметры - передаются внутри ()
 * при объявлении метода (String name, int age)
 * <p>
 * 6 - тело метода - тот код, который выполняется при вызове метода, пишется внутри {}
 */
public class MathUtils {
    public static void calculatePerimeterAndPrint(double a, double b, double c) {
        double perimeter = a + b + c;
        System.out.println("Периметр треугольника: " + perimeter);
    }

    public static double calculateTrianglePerimeter(double a, double b, double c) {
        double perimeter = a + b + c;
        return perimeter;
    }

    public static double calculateSquarePerimeter(double a, double b) {
        return (a + b) * 2;
    }

    public static double calculateCirclePerimeter(double r) {
        return 2 * Math.PI * r;
    }

    public static double calculateTrapezoidPerimeter(double a, double b, double c, double d) {
        return a + b + c + d;
    }

    public static double calculateEllipsePerimeter(double D, double d) {
        return 2 * Math.PI * Math.sqrt((Math.pow(D, 2) + Math.pow(d, 2)) / 8);
    }
}