package lesson6.wednesday;

public class Main {
    public static void main(String[] args) {
        /*
        1 Выучить теорию
        2 Создать 3 животных, которые имеют параметры рост, вес, имя, цвет, количество лап и любимую еду
        3 Имя, рост и вес должно задаваться при создании животного
        4 Животным должно быть возможно поменять вес, рост и любимую еду в любое время, а другие параметры должны
        оставаться неизменными всегда
        5 Животные говорящие, поэтому должны уметь сообщать о каждом своем параметре (по отдельности),
        например “Меня зовут Лео”
        6 Также, должен быть доступ на чтение всех данных животного извне(из других классов)
        7 2 животных из 3 должны быть полными близнецами и совпадать даже по имени
        8 Нужно не только верно создать всех животных, но и продемонстрировать,
        что все работает корректно в условном классе Main, в котором бы они создавались, сообщали свои данные, и прочее
        9 Также необходимо продемонстрировать сравнение животных, близнецы должны быть равны, а вот не близнецы - нет




         */
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
