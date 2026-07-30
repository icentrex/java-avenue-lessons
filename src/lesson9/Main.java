package lesson9;

public class Main {
    //Принципы ООП:
    //Инкапсуляция. Скрытая реализация
    //Абстракция. Использовыание Animal вместо конкретных Cat, Dog...
    //Полиморфизм. Переопределение методов
    //Наследование. Тут понятно
    public static void main(String[] args) {
        Cat cat = new Cat(5, 7);
        System.out.println(cat.getType());
        cat.move();
        cat.eat();
        cat.lookAtOwnerWithContempt();

        Dog dog = new Dog(7, 20);
        System.out.println(dog.getType());
        dog.move();
        dog.eat();
        dog.runForBall();
        dog.beNiceBoy();

        Bird bird = new Bird(2, 1);
        System.out.println(bird.getType());
        bird.eat();
        bird.move();
        bird.flyLikeABird();

        Animal[] animals = {cat, dog, bird};
        for (Animal animal : animals) {
            animal.eat();
            animal.move();
            if (animal instanceof Cat) {
                ((Cat) animal).lookAtOwnerWithContempt();
            }

            if (animal instanceof Dog) {
                ((Dog) animal).runForBall();
                ((Dog) animal).beNiceBoy();
            }

            if (animal instanceof Bird) {
                ((Bird) animal).flyLikeABird();
            }
        }
    }
}
