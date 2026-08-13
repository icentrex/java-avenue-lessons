package lesson12;

import lesson12.interfaces.Flyable;
import lesson12.interfaces.Huntable;
import lesson12.interfaces.Soundable;

public class Swan extends Animal implements Huntable, Soundable, Flyable {

    public Swan(String name, double weight, int age, String colour) {
        super(name, weight, age, colour);
    }

    @Override
    public void eat() {
        System.out.println("Ем корни, рачков..моллюсков");
    }

    @Override
    public void walkByLegs() {
        System.out.println("Неохотно хожу ногами");
    }

    @Override
    public void fly() {
        System.out.println("Грациозно парю в небе");
    }

    @Override
    public void hunt() {
        System.out.println("Я не охотник...но ловлю случайно попавшихся рачков");
    }

    @Override
    public void sound() {
        System.out.println("Издаю звонике, мелодичные звуки");
    }
}
