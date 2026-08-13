package lesson12.hw;

import lesson12.hw.interfaces.Flyable;
import lesson12.hw.interfaces.Quackable;
import lesson12.hw.interfaces.Swimable;

public class RealDuck extends Duck implements Swimable, Quackable, Flyable {

    public RealDuck(String name, int age, double weight, Color color) {
        super(name, age, weight, color);
    }

    public void reproduce() {
        System.out.println("Производить на свет маленьких утят");
    }

    @Override
    public void fly() {
        System.out.println("Летать быстро и прямолинейно, совершая частые взмахи крыльями. Развивать скорость до 90 км/ч");
    }

    @Override
    public void quack() {
        System.out.println("Крякать, когда хочу пообщаться");
    }

    @Override
    public void swim() {
        System.out.println("Плавать, когда добываю еду или чищу перья");
    }
}
