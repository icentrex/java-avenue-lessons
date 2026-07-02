package lesson6.monday;

public class Main {
    public static void main(String[] args) {
        //сделаем методы, который посчитает периметр треугольника по трем сторонам
        // а) вернет значение
        double result = MathUtils.calculateTrianglePerimeter(11, 65, 3);
        System.out.println(result);

        // б) сразу выведет его на консоль
        MathUtils.calculatePerimeterAndPrint(4, 7, 9);
    }
}
