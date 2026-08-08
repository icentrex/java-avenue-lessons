package lesson9.hw;

import java.util.Objects;

public class SportCar extends Car {

    private double enginePower;
    private String gearboxType;

    public SportCar(int wheelsNumber, String color, String material, double enginePower, String gearboxType) {
        super(wheelsNumber, color, material);
        this.enginePower = enginePower;
        this.gearboxType = gearboxType;
    }

    public void startWithTwoPedals() {
        System.out.println("Стартую с двух педалей");
    }

    public void raiseSpoiler() {
        System.out.println("Поднимаю спойлер");
    }

    @Override
    public void move() {
        System.out.println("Мощно рву с места");
    }

    @Override
    public void stopOnParking() {
        System.out.println("Плавно и уверенно сбавляю скорость и останавливаюсь");
    }

    public double getEnginePower() {
        return enginePower;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    @Override
    public String toString() {
        return "SportCar{" +
                "enginePower=" + enginePower +
                ", gearboxType='" + gearboxType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SportCar sportCar = (SportCar) o;
        return Double.compare(enginePower, sportCar.enginePower) == 0 && Objects.equals(gearboxType, sportCar.gearboxType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), enginePower, gearboxType);
    }
}
