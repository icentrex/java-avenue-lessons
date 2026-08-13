package lesson10;

import java.util.Objects;

public record Card(int nominal, String suit) {

    @Override
    public String toString() {
        return "Card{" +
                "nominal=" + nominal +
                ", suit='" + suit + '\'' +
                '}';
    }
}
