package lesson12;

import lesson12.interfaces.Soundable;

public class Elephant extends Animal implements Soundable {

    public Elephant(String name, double weight, int age, String colour) {
        super(name, weight, age, colour);
    }

    @Override
    public void eat() {
        System.out.println("Ем траву, листья, ветки");
    }

    @Override
    public void walkByLegs() {
        System.out.println("Громко топаю ногами");
    }

    @Override
    public void sound() {
        System.out.println("Издаю громкий звук трубы");
    }
}
