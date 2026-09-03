package lesson18;

import java.util.*;

public class Fridge {
    private final Map<String, Double> products = new HashMap<>();

    //Положить продукт в холодильник
    public void putProduct(String name, double weight) {
        if (products.containsKey(name)) {
            double currentWeight = products.get(name);
            double newWeight = currentWeight + weight;
            products.put(name, newWeight);
            System.out.println("Добавил " + name + ", " + weight + " грамм");
        } else {
            products.put(name, weight);
            System.out.println("Положил " + name + ", вес: " + weight + " грамм");
        }
    }

    //Взять продукт из холодильника
    public void getProduct(String name, double weight) {
        System.out.println("Хочу взять: " + name + ", вес: " + weight);
        if (products.containsKey(name)) {
            double currentWeight = products.get(name);

            double stayedWeight = currentWeight - weight;
            if (stayedWeight > 0) {
                products.put(name, stayedWeight);
                System.out.println("Взял");
            } else if (stayedWeight == 0) {
                products.remove(name);
                System.out.println("Взял и больше нет");
            } else {
                System.out.println("Вес больше, чем есть в холодильнике. Вы не можете столько взять");
            }
        } else {
            System.out.println("Такого продукта нет в холодильнике");
        }
    }

    //Вывести вес всех продуктов в холодильнике(сумма)
    public void printAllProductsWeight() {
        double allProductsWeight = 0.0;
        for (Double value : products.values()) {
            allProductsWeight += value;
        }
        System.out.println(allProductsWeight + " грамм");
    }

    //Вывести вес конкретного продукта в холодильнике
    public void printProductWeight(String name) {
        if (products.containsKey(name)) {
            System.out.println("Продукт: " + name + ", вес: " + products.get(name) + " грамм");
        } else {
            System.out.println("Такого продукта нет в холодильнике");
        }
    }

    //Вывести наименование продукта, который закончится первым
    public void minWeightProduct() {
        List<Double> values = new ArrayList<>(products.values());
        values.sort(Comparator.naturalOrder());

        double minWeight = values.getFirst();

        for (String key : products.keySet()) {
            if (products.get(key) == minWeight) {
                System.out.println(key);
            }
        }
    }

    //Вывести наименование продукта, которого больше всего
    public void maxWeightProduct() {
        List<Double> values = new ArrayList<>(products.values());
        values.sort(Comparator.reverseOrder());

        double maxWeight = values.getFirst();

        for (String key : products.keySet()) {
            if (products.get(key) == maxWeight) {
                System.out.println(key);
            }
        }
    }

    //Вывести все продукты (название + вес) по алфавиту от Я до А
    public void printAllProductsFromZToA() {
        Set<String> keys = new TreeSet<>(Comparator.reverseOrder());
        keys.addAll(products.keySet());
        for (String key : keys) {
            System.out.println(key + ": " + products.get(key) + " грамм");
        }
    }

    //Вывести все продукты (название + вес) по весу от большего к меньшему
    public void printAllProductsFromMaxToMinWeight() {
        List<Map.Entry<String, Double>> entries = new ArrayList<>(products.entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, Double> entry : entries) {
            System.out.println("Продукт: " + entry.getKey() + ", вес: " + entry.getValue());
        }
    }
}
