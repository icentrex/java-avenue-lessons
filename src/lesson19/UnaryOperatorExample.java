package lesson19;

import java.util.List;
import java.util.function.UnaryOperator;

public class UnaryOperatorExample {
    public static void main(String[] args) {
        List<String> words = List.of("Дом", "Город", "Треугольник", "Пирамида", "Тетрадь", "Танк", "Семечка");
        UnaryOperator<String> upperCaseOperator = word -> word.toUpperCase();
        for (String word : words) {
            System.out.println(upperCaseOperator.apply(word));
        }
    }
}
