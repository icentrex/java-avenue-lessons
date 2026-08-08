package lesson9.hw2;

public class Main {
    public static void main(String[] args) {

        Circle circle = new Circle(10);
        Triangle triangle = new Triangle(4, 5);
        Rectangle rectangle = new Rectangle(6, 7);

        circle.calculateArea();
        triangle.calculateArea();
        rectangle.calculateArea();
    }
}
