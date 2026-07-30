package lesson9;

public abstract class Animal {
    //поля
    //конструктор
    //свои методы
    //геттеры
    //сеттеры
    //переопределенные

    private int age;
    private double weight;
    private String type;

    public Animal(int age, double weight, String type) {
        this.age = age;
        this.weight = weight;
        this.type = type;
    }

    public abstract void move();

    public abstract void eat();

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setType(String type) {
        this.type = type;
    }
}