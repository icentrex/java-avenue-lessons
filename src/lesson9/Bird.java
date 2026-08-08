package lesson9;

public class Bird extends Animal {
    public Bird(int age, double weight) {
        super(age, weight, "Птица");
    }

    public void flyLikeABird() {
        System.out.println("Парю словно орел");
    }

    @Override
    public void move() {
        System.out.println("Прыгаю на задних лапках");
    }

    @Override
    public void eat() {
        System.out.println("Клюю по зернышку");
    }
}
