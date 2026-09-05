package lesson19.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

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

        System.out.println("==Пример Predicate");
        Predicate<Shop> checkShopIncomePredicate = shop -> shop.income() > 5_000_000;

        for (Shop shop : shops) {
            if (checkShopIncomePredicate.test(shop)) {
                System.out.printf("%s - %,.0f руб. - %,.0fм2%n", shop.name(), shop.income(), shop.area());
            }
        }

        System.out.println("\n==Пример Consumer");
        Consumer<Shop> checkShopIncomeConsumer = shop -> {
            if (shop.income() < 5_000_000) {
                System.out.printf("%s - %,.0f руб. - %,.0fм2%n", shop.name(), shop.income(), shop.area());
            }
        };

        for (Shop shop : shops) {
            checkShopIncomeConsumer.accept(shop);
        }

        System.out.println("\n==Пример Supplier");
        Supplier<String> shopNameGenerator = () -> {
            List<String> shopNameList = new ArrayList<>(List.of("Ашан", "Пятёрочка", "Магнит", "Дикси", "Лента"));

            return shopNameList.get(new Random().nextInt(0, shopNameList.size() - 1));
        };

        System.out.println(shopNameGenerator.get());

        System.out.println("\n==Пример Function");
        Function<Shop, ShopShort> shopMapper = shop -> new ShopShort(shop.name());

        System.out.println(shopMapper.apply(shops.getFirst()));

        System.out.println("\n==Пример UnaryOperator");
        List<Shop> shopsIncoming = new ArrayList<>();
        shopsIncoming.add(new Shop("    Ашан Каширское", 15_800_000, 12_000));
        shopsIncoming.add(new Shop("Ашан Ленинский", 9_200_000, 3_000));
        shopsIncoming.add(new Shop("Ашан     Жулебино", 6_400_000, 800));
        shopsIncoming.add(new Shop("Ашан Текстильщики", 5_100_000, 1_200));
        shopsIncoming.add(new Shop("     Ашан Бутово", 3_750_000, 900));
        shopsIncoming.add(new Shop("Ашан Комсомольская", 2_900_000, 4_500));
        shopsIncoming.add(new Shop("Ашан    Сокол", 1_600_000, 350));
        shopsIncoming.add(new Shop("   Ашан Мини Марьино", 850_000, 120));

        UnaryOperator<Shop> shopNameTrim = shop -> new Shop(shop.name().trim(), shop.income(), shop.area());

        List<Shop> shopsCleaned = shopsIncoming
                .stream()
                .map(shopNameTrim)
                .toList();

        System.out.println(shopsIncoming);
        System.out.println(shopsCleaned);
    }
}
