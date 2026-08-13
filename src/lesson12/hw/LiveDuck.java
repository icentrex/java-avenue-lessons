package lesson12.hw;

import lesson12.hw.interfaces.Flyable;
import lesson12.hw.interfaces.Quackable;
import lesson12.hw.interfaces.Swimable;

public class LiveDuck extends Duck implements Swimable, Quackable, Flyable {

    public LiveDuck(String name, int age, int weight) {
        super(name, age, weight);
    }

    public void reproduce() {
        System.out.println("Могу произвести на свет маленьких утят");
    }

    @Override
    public void fly() {
        System.out.println("Летаю быстро и прямолинейно, совершая частые взмахи крыльями. Развивая скорость до 90 км/ч");
    }

    @Override
    public void quack() {
        System.out.println("Крякаю, когда хочу пообщаться");
    }

    @Override
    public void swim() {
        System.out.println("Плаваю, когда добываю еду или чищу перья");
    }
}
