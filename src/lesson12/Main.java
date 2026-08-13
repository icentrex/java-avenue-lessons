package lesson12;

import lesson12.interfaces.Flyable;
import lesson12.interfaces.Huntable;
import lesson12.interfaces.Soundable;

import java.util.ArrayList;

/*
Орел, Слон, Жираф, Тигр, Лебедь

1) Состояние
    - имя
    - вес
    - возраст
    - цвет

2) Поведение
    - Кто ест? (все)
    - Кто охотится? (Орел, Тигр, Лебедь)
    - Кто издает звуки? (Орел, Слон, Тигр, Лебедь)
    - Кто летает? (Орел, Лебедь)
    - Кто ходит ногами? (все)
 */
public class Main {
    public static void main(String[] args) {
        Eagle eagle = new Eagle("Кеша", 45.6, 18, "черный");
        Elephant elephant = new Elephant("Дамби", 32, 13, "серый");
        Giraffe giraffe = new Giraffe("Аркадий", 12, 53, "желтый");
        Swan swan = new Swan("Людмила", 43, 12, "белый");
        Tiger tiger = new Tiger("Альфа-вождь", 10, 50, "желтый");

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(eagle);
        animals.add(elephant);
        animals.add(giraffe);
        animals.add(swan);
        animals.add(tiger);

        for (Animal animal : animals) {
            animal.eat();
            animal.walkByLegs();

            if (animal instanceof Flyable) {
                ((Flyable) animal).fly();
            }

            if (animal instanceof Huntable) {
                ((Huntable) animal).hunt();
            }

            if (animal instanceof Soundable) {
                ((Soundable) animal).sound();
            }
        }
    }
}
