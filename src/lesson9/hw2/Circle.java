package lesson9.hw2;

public class Circle extends Shape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculateArea() {
        System.out.printf("Площадь круга: %.2f%n", Math.PI * Math.pow(radius, 2));
    }
}
