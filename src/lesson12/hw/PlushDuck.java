package lesson12.hw;

import lesson12.hw.interfaces.Quackable;
import lesson12.hw.interfaces.Swimable;

public class PlushDuck extends Duck implements Swimable, Quackable {

    public PlushDuck(String name, int age, double weight, Color color) {
        super(name, age, weight, color);
    }

    @Override
    public void quack() {
        System.out.println("Крякать, когда нажали на кнопку");
    }

    @Override
    public void swim() {
        System.out.println("Плавать не люблю, быстро промокаю");
    }
}
