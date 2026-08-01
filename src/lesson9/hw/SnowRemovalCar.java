package lesson9.hw;

import java.util.Objects;

public class SnowRemovalCar extends Car {

    private RemovalBucket removalBucket;

    public SnowRemovalCar(int wheelsNumber, String color, String material, RemovalBucket removalBucket) {
        super(wheelsNumber, color, material);
        this.removalBucket = removalBucket;
    }

    public void startSnowRemoval() {
        System.out.println("Начинаю уборку снега");
    }

    public RemovalBucket getRemovalBucket() {
        return removalBucket;
    }

    public void setRemovalBucket(RemovalBucket removalBucket) {
        this.removalBucket = removalBucket;
    }

    @Override
    public String toString() {
        return "SnowRemovalCar{" +
                "removalBucket=" + removalBucket +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SnowRemovalCar that = (SnowRemovalCar) o;
        return Objects.equals(removalBucket, that.removalBucket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), removalBucket);
    }
}
