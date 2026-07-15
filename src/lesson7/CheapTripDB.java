package lesson7;

public class CheapTripDB {
    private final Tour[] tours = {
            new Tour("Турция", "Стамбул", "Самолет", 100_000, 5, 3, "завтрак"),
            new Tour("Турция", "Анталья", "Самолет", 150_000, 7, 5, "завтрак"),
            new Tour("Италия", "Рим", "Автобус", 150_000, 10, 4, "все включено"),
            new Tour("Турция", "Самсун", "Паром", 70000, 5, 2, "завтрак + обед"),
            new Tour("Германия", "Берлин", "Автобус", 135_000, 9, 4, "все включено"),
            new Tour("Россия", "Алтай", "Поезд", 78000, 4, 5, "завтрак"),
            new Tour("Иран", "Шираз", "Самолет", 80000, 5, 4, "завтрак"),
            new Tour("Шри-ланка", "Коломбо", "Самолет", 200_000, 10, 5, "все включено")
    };

    public void searchByCountry(String country) {
        for (Tour tour : tours) {
            if (tour.getCountry().equalsIgnoreCase(country)) {
                System.out.println(tour);
            }
        }
    }

    public void searchByCity(String city) {
        for (Tour tour : tours) {
            if (tour.getCity().equalsIgnoreCase(city)) {
                System.out.println(tour);
            }
        }
    }

    public void searchByPrice(int price) {
        for (Tour tour : tours) {
            if (tour.getPrice() <= price) {
                System.out.println(tour);
            }
        }
    }

    public void searchByPriceAndStars(int price, int stars) {
        for (Tour tour : tours) {
            if (tour.getPrice() <= price && tour.getStars() >= stars) {
                System.out.println(tour);
            }
        }
    }

    public void showTours() {
        for (Tour tour : tours) {
            System.out.println(tour);
        }
    }
}
