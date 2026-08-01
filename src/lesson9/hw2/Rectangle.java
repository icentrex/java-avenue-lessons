package lesson9.hw2;

public class Rectangle extends Shape {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void calculateArea() {
        System.out.printf("Площадь прямоугольника: %.2f%n", length * width);
    }
}
