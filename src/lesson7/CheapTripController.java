package lesson7;

import java.util.Scanner;

public class CheapTripController {
    private final CheapTripDB db = new CheapTripDB();
    private boolean isNeedContinue = true;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (isNeedContinue) {
            //Печать меню
            printMenu();
            //Обработка ввода пользователя
            processRequest(scanner);
        }
        scanner.close();
    }

    private void processRequest(Scanner scanner) {
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.println("В какую страну вы хотите полететь?");
                String country = scanner.nextLine();
                db.searchByCountry(country);
            }
            case 2 -> {
                System.out.println("В какой город вы хотите полететь?");
                String city = scanner.nextLine();
                db.searchByCity(city);
            }
            case 3 -> {
                System.out.println("Введите бюджет на поездку?");
                int price = scanner.nextInt();
                scanner.nextLine();
                db.searchByPrice(price);
            }
            case 4 -> {
                System.out.println("Введите бюджет на поездку?");
                int price = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Введите предпочитаемое количество звезд?");
                int stars = scanner.nextInt();
                scanner.nextLine();
                db.searchByPriceAndStars(price, stars);
            }
            case 5 -> {
                System.out.println("Список всех туров:");
                db.showTours();
            }
            case 0 -> {
                System.out.println("Гид завершил работу");
                isNeedContinue = false;
            }
            default -> System.out.println("Неверный выбор!");
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Добрый день! Я ваш персональный гид");
        System.out.println("Выберите фильтр, по которому вы хотите искать туры");
        System.out.println("1 - поиск туров по стране");
        System.out.println("2 - поиск туров по городу");
        System.out.println("3 - поиск туров по цене");
        System.out.println("4 - поиск туров по цене и по звездам");
        System.out.println("5 - вывести все туры");
        System.out.println("0 - выйти из гида");
    }
}
