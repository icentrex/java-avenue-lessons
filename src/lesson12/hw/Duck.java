package lesson12.hw;

import java.util.Objects;

public abstract class Duck {
    private String name;
    private int age;
    private double weight;
    private Color color;

    public enum Color {WHITE, GREY, YELLOW, RED, BLUE, GREEN}

    public Duck(String name, int age, double weight, Color color) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Родительский класс = Duck: " +
                "Имя='" + name + '\'' +
                ", Возраст=" + age +
                ", Вес=" + weight +
                ", Цвет=" + color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Duck duck = (Duck) o;
        return age == duck.age && weight == duck.weight && Objects.equals(name, duck.name) && color == duck.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, weight, color);
    }
}
