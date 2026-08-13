package lesson12;

import lesson12.interfaces.Huntable;
import lesson12.interfaces.Soundable;

public class Tiger extends Animal implements Huntable, Soundable {

    public Tiger(String name, double weight, int age, String color) {
        super(name, weight, age, color);
    }

    @Override
    public void eat() {
        System.out.println("Ем мясо");
    }

    @Override
    public void walkByLegs() {
        System.out.println("Бегу на четырех ногах");
    }


    @Override
    public void hunt() {
        System.out.println("Жду в фасаде");
    }

    @Override
    public void sound() {
        System.out.println("Громко рычу");
    }
}
