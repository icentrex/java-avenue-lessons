package lesson19;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateAndConsumerExample {
    public static void main(String[] args) {
        List<String> words = List.of("Дом", "Город", "Треугольник", "Пирамида", "Тетрадь", "Танк", "Семечка");

        System.out.println("---Найдем все слова с буквой Т большой или маленькой, с буквой Е большой или маленькой, " +
                "и без буквы К большой или маленькой");
        for (String word : words) {
            if ((word.contains("Т") || word.contains("т")) && (word.contains("Е") || word.contains("е"))
                    && !(word.contains("К") || word.contains("к"))) {
                System.out.println(word);
            }
        }

        System.out.println("---То же самое с Predicate");
        WordValidatorPredicate wordValidatorPredicate = new WordValidatorPredicate();
        for (String word : words) {
            if (wordValidatorPredicate.test(word)) {
                System.out.println(word);
            }
        }

        System.out.println("---Выведем все слова с буквой Т через predicate с анонимной функцией");
        Predicate<String> tContainsValidator = word -> word.contains("Т") || word.contains("т");
        // запись выше аналогична записи ниже, делает одно и тоже
//        Predicate<String> tContainsValidator2 = new Predicate<String>() {
//            @Override
//            public boolean test(String word) {
//                return word.contains("Т") || word.contains("т");
//            }
//        };
        for (String word : words) {
            if (tContainsValidator.test(word)) {
                System.out.println(word);
            }
        }

        System.out.println("---Реализуем то же самое через consumer");
        Consumer<String> printConsumer = new PrintConsumer();
        for (String word : words) {
            printConsumer.accept(word);
        }

        System.out.println("---Напечатаем слова с буквой Т через анонимную функцию");
        Consumer<String> printTConsumer = word -> {
            if (word.contains("Т") || word.contains("т")) {
                System.out.println(word);
            }
        };
        for (String word : words) {
            printTConsumer.accept(word);
        }
    }
}
