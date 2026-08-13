package lesson12.hw;

import lesson12.hw.interfaces.Swimable;

public class RubberDuck extends Duck implements Swimable {

    public RubberDuck(String name, int age, double weight, Color color) {
        super(name, age, weight, color);
    }

    @Override
    public void swim() {
        System.out.println("Купаться вместе с малышом в ванной");
    }
}
