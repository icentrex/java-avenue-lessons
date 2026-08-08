package lesson9;

public class Dog extends Animal {
    public Dog(int age, double weight) {
        super(age, weight, "Собака");
    }

    public void runForBall() {
        System.out.println("Лечу за мячом вприпрыжку");
    }

    public void beNiceBoy() {
        System.out.println("Глажусь и прикидываюсь милахой");
    }

    @Override
    public void move() {
        System.out.println("Двигаюсь и виляю хвостом");
    }

    @Override
    public void eat() {
        System.out.println("Загребаю из миски и чавкаю");
    }
}
