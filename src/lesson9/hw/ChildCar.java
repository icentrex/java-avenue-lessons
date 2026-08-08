package lesson9.hw;

import java.util.Objects;

public class ChildCar extends Car {

    private RemoteControl remoteControl;

    public ChildCar(int wheelsNumber, String color, String material, RemoteControl remoteControl) {
        super(wheelsNumber, color, material);
        this.remoteControl = remoteControl;
    }

    public void playKidsMusic() {
        System.out.println("Включаю детскую музыку");
    }

    @Override
    public void move() {
        System.out.println("Маленькая ножка в сандалике давит на газ. Медленно начинаю двигаться вперед.");
    }

    @Override
    public void stopOnParking() {
        System.out.println("Маленькая ножка в сандалике давит на тормоз. Медленно сбавляю скорость и останавливаюсь.");
    }

    public RemoteControl getRemoteControl() {
        return remoteControl;
    }

    public void setRemoteControl(RemoteControl remoteControl) {
        this.remoteControl = remoteControl;
    }

    @Override
    public String toString() {
        return "ChildCar{" +
                "remoteControl=" + remoteControl +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChildCar childCar = (ChildCar) o;
        return Objects.equals(remoteControl, childCar.remoteControl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), remoteControl);
    }
}
