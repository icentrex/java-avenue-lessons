package lesson10;

public record Card(int nominal, String suit) {

    @Override
    public String toString() {
        return "Card{" +
                "nominal=" + nominal +
                ", suit='" + suit + '\'' +
                '}';
    }


}
