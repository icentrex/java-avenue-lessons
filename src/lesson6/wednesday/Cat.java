package lesson6.wednesday;

import java.util.Objects;
/*
1 Переменные
2 Конструктор
3 Новые методы
4 Геттеры и сеттеры. Лучше группировать геттеры рядом, сеттеры рядом
5 Переопределенные методы
 */

public class Cat {
    private double growth;
    private double weight;
    private final String name;
    private final String color;
    private final int numberOfPaws;
    private String favoriteFood;

    public Cat(String name, double growth, double weight) {
        this.name = name;
        this.growth = growth;
        this.weight = weight;
        this.color = "белый";
        this.numberOfPaws = 4;
    }

    public void showWhoIAm() {
        switch (this.getClass().getSimpleName()) {
            case "Cat":
                System.out.println("Я кот");
                break;
            case "Hippopotamus":
                System.out.println("Я гипопотам");
                break;
            default:
                System.out.println("Я не знаю кто я :(");
        }
    }

    public void showMyGrowth() {
        System.out.printf("Мой рост в холке %.0f сантиметров", growth);
        System.out.println();
    }

    public void showMyWeight() {
        System.out.printf("Я вешу уже %.1f килограмм(-а)", weight);
        System.out.println();
    }

    public void showMyName() {
        System.out.printf("Моё полное имя %s", name);
        System.out.println();
    }

    public void showMyColor() {
        System.out.printf("Мой цвет %s", color);
        System.out.println();
    }

    public void showNumberOfPaws() {
        System.out.printf("И у меня %d лапы", numberOfPaws);
        System.out.println();
    }

    public void showMyFavoriteFood() {
        System.out.printf("Мой любимая вкусняшка - это %s", favoriteFood);
        System.out.println();
    }

    public double getGrowth() {
        return growth;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getNumberOfPaws() {
        return numberOfPaws;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setGrowth(double growth) {
        this.growth = growth;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "growth=" + growth +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", numberOfPaws=" + numberOfPaws +
                ", favoriteFood='" + favoriteFood + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Double.compare(growth, cat.growth) == 0 && Double.compare(weight, cat.weight) == 0 && numberOfPaws == cat.numberOfPaws && Objects.equals(name, cat.name) && Objects.equals(color, cat.color) && Objects.equals(favoriteFood, cat.favoriteFood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(growth, weight, name, color, numberOfPaws, favoriteFood);
    }
}
