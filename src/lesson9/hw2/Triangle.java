package lesson9.hw2;

public class Triangle extends Shape {

    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void calculateArea() {
        System.out.printf("Площадь треугольника: %.2f%n", (base * height) / 2);
    }
}
