package lesson9.hw;

import java.util.Objects;

public class RemovalBucket {

    private double volume;
    private String material;
    //    Bucket volume: from 0.4 to 2.5 m³ (depending on the power of the base machine and the density of the snow).
//    Material: wear-resistant sheet steel with anti-corrosion coating.

    public RemovalBucket(double volume, String material) {
        this.volume = volume;
        this.material = material;
    }

    public double getVolume() {
        return volume;
    }

    public String getMaterial() {
        return material;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "removalBucket{" +
                "volume=" + volume +
                ", material='" + material + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RemovalBucket that = (RemovalBucket) o;
        return Double.compare(volume, that.volume) == 0 && Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, material);
    }
}
