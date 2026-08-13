package lesson12.hw;

import lesson12.hw.interfaces.Flyable;
import lesson12.hw.interfaces.Quackable;
import lesson12.hw.interfaces.Swimable;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        RealDuck daisy = new RealDuck("Дейзи", 3, 2.5, Duck.Color.GREY);
        RubberDuck ponochka = new RubberDuck("Поночка", 1, 0.1, Duck.Color.YELLOW);
        PlushDuck billy = new PlushDuck("Билли", 6, 0.5, Duck.Color.RED);
        PlushDuck villy = new PlushDuck("Вилли", 6, 0.5, Duck.Color.GREEN);
        PlushDuck dilly = new PlushDuck("Дилли", 6, 0.5, Duck.Color.BLUE);

        ArrayList<Duck> ducks = new ArrayList<>();
        ducks.add(daisy);
        ducks.add(ponochka);
        ducks.add(billy);
        ducks.add(villy);
        ducks.add(dilly);

        for (Duck duck : ducks) {
            System.out.println("\nЯ утка: " + duck.getClass().getSimpleName() + "\n" + duck);
            System.out.println("Я умею: ");
            if (duck instanceof Flyable) {
                ((Flyable) duck).fly();
            }

            if (duck instanceof Swimable) {
                ((Swimable) duck).swim();
            }

            if (duck instanceof Quackable) {
                ((Quackable) duck).quack();
            }

            if (duck instanceof RealDuck) {
                ((RealDuck) duck).reproduce();
            }
        }

    }
}
