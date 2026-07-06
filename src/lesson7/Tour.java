package lesson7;

import java.util.Objects;

public class Tour {
    private String country;
    private String city;
    private String transport;
    private int days;
    private int price;
    private int stars;
    private String food;

    public Tour(String country, String city, String transport, int price, int days, int stars, String food) {
        this.country = country;
        this.city = city;
        this.transport = transport;
        this.days = days;
        this.price = price;
        this.stars = stars;
        this.food = food;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getTransport() {
        return transport;
    }

    public int getDays() {
        return days;
    }

    public int getPrice() {
        return price;
    }

    public int getStars() {
        return stars;
    }

    public String getFood() {
        return food;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", transport='" + transport + '\'' +
                ", days=" + days +
                ", price=" + price +
                ", stars=" + stars +
                ", food='" + food + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return days == tour.days && price == tour.price && stars == tour.stars && Objects.equals(country, tour.country) && Objects.equals(city, tour.city) && Objects.equals(transport, tour.transport) && Objects.equals(food, tour.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, transport, days, price, stars, food);
    }
}
