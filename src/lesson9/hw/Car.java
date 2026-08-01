package lesson9.hw;

import java.util.Objects;

public abstract class Car {
    //поля
    //конструктор
    //свои методы
    //геттеры
    //сеттеры
    //переопределенные

    private int wheelsNumber;
    private String color;
    private String material;

    public Car(int wheelsNumber, String color, String material) {
        this.wheelsNumber = wheelsNumber;
        this.color = color;
        this.material = material;
    }

    public void move() {
        System.out.println("Начинаю движение с места");
    }

    public void stopOnParking() {
        System.out.println("Останавливаюсь. Перехожу в режим \"Паркинг\"");
    }

    public int getWheelsNumber() {
        return wheelsNumber;
    }

    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }

    public void setWheelsNumber(int wheelsNumber) {
        this.wheelsNumber = wheelsNumber;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Car{" +
                "wheel=" + wheelsNumber +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return wheelsNumber == car.wheelsNumber && Objects.equals(color, car.color) && Objects.equals(material, car.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wheelsNumber, color, material);
    }
}
