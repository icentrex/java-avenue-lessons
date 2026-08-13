package lesson12.hw;

import lesson12.hw.interfaces.Swimable;

public class RubberDuck extends Duck implements Swimable {

    public RubberDuck(String name, int age, int weight) {
        super(name, age, weight);
    }

    @Override
    public void swim() {
        System.out.println("Люблю плавать в компании маленьких детей в ванной");
    }
}
