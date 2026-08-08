package lesson9;

public class Cat extends Animal {
    public Cat(int age, double weight) {
        super(age, weight,"Кошка");
    }

    public void lookAtOwnerWithContempt() {
        System.out.println("Смотрю на хозяина с презрением!");
    }

    @Override
    public void move() {
        System.out.println("Ем и раскидываю еду вокруг миски");
    }

    @Override
    public void eat() {
        System.out.println("Бегаю по квартире и сбиваю вазы");
    }
}
