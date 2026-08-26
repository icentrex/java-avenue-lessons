package lesson18;

public class FridgeExample {
    public static void main(String[] args) {
        Fridge fridge = new Fridge();
        System.out.println("Положить продукт в холодильник:");
        fridge.putProduct("Банан", 200);
        fridge.putProduct("Масло", 500);
        fridge.putProduct("Банан", 400);
        fridge.putProduct("Йогурт", 150);
        fridge.putProduct("Колбаса", 1000);
        System.out.println();

        System.out.println("Вывести все продукты (название + вес) по алфавиту от Я до А:");
        fridge.printAllProductsFromZToA();
        System.out.println();

        System.out.println("Вывести все продукты (название + вес) по весу от большего к меньшему:");
        fridge.printAllProductsFromMaxToMinWeight();
        System.out.println();

        System.out.println("Вывести вес всех продуктов в холодильнике(сумма):");
        fridge.printAllProductsWeight();
        System.out.println();

        System.out.println("Взять продукт из холодильника:");
        fridge.getProduct("Банан", 100);
        System.out.println();

        System.out.println("Вывести вес всех продуктов в холодильнике(сумма):");
        fridge.printAllProductsWeight();
        System.out.println();

        System.out.println("Вывести вес конкретного продукта в холодильнике:");
        fridge.printProductWeight("Йогурт");
        System.out.println();

        System.out.println("Вывести наименование продукта, который закончится первым:");
        fridge.minWeightProduct();
        System.out.println();

        System.out.println("Вывести наименование продукта, которого больше всего:");
        fridge.maxWeightProduct();
    }
}
