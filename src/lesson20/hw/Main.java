package lesson20.hw;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop("Ашан Каширское", 15_800_000, 12_000));
        shops.add(new Shop("Ашан Ленинский", 9_200_000, 3_000));
        shops.add(new Shop("Ашан Жулебино", 6_400_000, 800));
        shops.add(new Shop("Ашан Текстильщики", 5_100_000, 1_200));
        shops.add(new Shop("Ашан Бутово", 3_750_000, 900));
        shops.add(new Shop("Ашан Комсомольская", 2_900_000, 4_500));
        shops.add(new Shop("Ашан Сокол", 1_600_000, 350));
        shops.add(new Shop("Ашан Мини Марьино", 850_000, 120));

        System.out.println("Общий доход по всем магазинам сети:");
        double totalRevenue = shops
                .stream()
                .mapToDouble(shop -> shop.income())
                .sum();
        System.out.printf("%,.0f руб.%n", totalRevenue);

        System.out.println("\nКоличество магазинов в сети:");
        long quantityOfShops = shops.size();
        System.out.println(quantityOfShops);

        System.out.println("\nСредний доход на каждый магазин сети:");
        double averageIncomeByShop = totalRevenue / quantityOfShops;
        System.out.println(averageIncomeByShop);

        System.out.println("\nМагазины по доходности от большего к меньшему:");
        shops
                .stream()
                .sorted((shop1, shop2) -> Double.compare(shop2.income(), shop1.income()))
                .forEach(shop -> System.out.printf("%s - %,.0f руб. - %,.0fм2%n", shop.name(), shop.income(), shop.area()));

        System.out.println("\nМагазины сети по соотношению дохода на 1м площади магазина:");
        shops
                .stream()
                .sorted(Comparator.comparingDouble(shop -> shop.income() / shop.area()))
                .forEach(shop -> System.out.printf("%s - %,.0f руб./м2%n", shop.name(), shop.income() / shop.area()));

        System.out.println("\nТоп 2 магазинов по доходу:");
        System.out.println("Лучшие:");
        shops
                .stream()
                .sorted((shop1, shop2) -> Double.compare(shop2.income(), shop1.income()))
                .limit(2)
                .forEach(shop -> System.out.printf("%s - %,.0f руб. - %,.0fм2%n", shop.name(), shop.income(), shop.area()));

        System.out.println("Худшие:");
        shops
                .stream()
                .sorted((shop1, shop2) -> Double.compare(shop2.income(), shop1.income()))
                .skip(shops.size() - 2)
                .forEach(shop -> System.out.printf("%s - %,.0f руб. - %,.0fм2%n", shop.name(), shop.income(), shop.area()));

        System.out.println("\nМагазины, в которых соотношение доход на 1м квадратный больше 1000 рублей:");
        shops
                .stream()
                .filter(shop -> (shop.income() / shop.area() > 1000))
                .forEach(shop -> System.out.printf("%s - %,.0f руб./м2%n", shop.name(), shop.income() / shop.area()));
    }
}
