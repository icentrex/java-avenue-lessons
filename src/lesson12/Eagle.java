package lesson12;

import lesson12.interfaces.Flyable;
import lesson12.interfaces.Huntable;
import lesson12.interfaces.Soundable;

public class Eagle extends Animal implements Flyable, Huntable, Soundable {

    public Eagle(String name, double weight, int age, String color) {
        super(name, weight, age, color);
    }

    @Override
    public void eat() {
        System.out.println("Ем падаль");
    }

    @Override
    public void walkByLegs() {
        System.out.println("неуклюже хожу ногами");
    }

    @Override
    public void fly() {
        System.out.println("Летаю высоко в небе");
    }

    @Override
    public void hunt() {
        System.out.println("Падаю резко вниз на добычу");
    }

    @Override
    public void sound() {
        System.out.println("Противно кричу в небе");
    }
}
