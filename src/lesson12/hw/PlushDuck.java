package lesson12.hw;

import lesson12.hw.interfaces.Quackable;
import lesson12.hw.interfaces.Swimable;

public class PlushDuck extends Duck implements Swimable, Quackable {

    public PlushDuck(String name, int age, int weight) {
        super(name, age, weight);
    }

    @Override
    public void quack() {
        System.out.println("Крякаю, когда нажали на кнопку");
    }

    @Override
    public void swim() {
        System.out.println("Плавать не люблю, быстро промокаю");
    }
}
