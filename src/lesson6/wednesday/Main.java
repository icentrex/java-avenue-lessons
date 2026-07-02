package lesson6.wednesday;

public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Феликс", 30, 3);
        cat1.setFavoriteFood("Мороженка и дыня:)");
        cat1.setWeight(3.5);
        Hippopotamus hippopotamus = new Hippopotamus("Тотошка", 150, 400);
        hippopotamus.setFavoriteFood("Свежая арбузная корочка:)");
        hippopotamus.setGrowth(200);
        Cat cat2 = new Cat("Феликс", 30, 3);
        cat2.setWeight(3.5);
        cat2.setFavoriteFood("Мороженка и дыня:)");

        System.out.println("---Вывожу информацию о коте через отдельные методы (не геттеры):");
        cat1.showWhoIAm();
        cat1.showMyName();
        cat1.showMyColor();
        cat1.showMyFavoriteFood();
        cat1.showMyGrowth();
        cat1.showMyWeight();
        cat1.showNumberOfPaws();

        System.out.println();
        System.out.println("---Вывожу информацию о гиппопотаме через отдельные методы (не геттеры):");
        hippopotamus.showWhoIAm();
        hippopotamus.showMyName();
        hippopotamus.showMyColor();
        hippopotamus.showMyFavoriteFood();
        hippopotamus.showMyGrowth();
        hippopotamus.showMyWeight();
        hippopotamus.showNumberOfPaws();

        System.out.println();
        System.out.println("---Вывожу информацию о гиппопотаме через геттеры:");
        System.out.printf("Коротко обо мне: я - %s, имя: %s, цвет: %s, еда: %s, рост: %.0f, вес: %.0f," +
                        " лапы: %d штуки", hippopotamus.getClass().getSimpleName(), hippopotamus.getName(),
                hippopotamus.getColor(), hippopotamus.getFavoriteFood(), hippopotamus.getGrowth(),
                hippopotamus.getWeight(), hippopotamus.getNumberOfPaws());

        System.out.println();
        System.out.println();
        System.out.println("---Вывожу результат сравнения через переопределенный метод equals:");
        if (cat1.equals(cat2)) {
            System.out.println("cat1 и cat2 - близнецы");
        }

        if (!cat1.equals(hippopotamus)) {
            System.out.println("А вот cat1 и hippopotamus - совсем не близнецы");
        }
    }
}
