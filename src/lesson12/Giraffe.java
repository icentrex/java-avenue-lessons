package lesson12;

public class Giraffe extends Animal {

    public Giraffe(String name, double weight, int age, String color) {
        super(name, weight, age, color);
    }

    @Override
    public void eat() {
        System.out.println("Ем листья на деревьях");
    }

    @Override
    public void walkByLegs() {
        System.out.println("Хожу на длинных ногах");
    }
}
