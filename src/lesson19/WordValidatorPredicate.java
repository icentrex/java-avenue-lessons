package lesson19;

import java.util.function.Predicate;

public class WordValidatorPredicate implements Predicate<String> {

    @Override
    public boolean test(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        String wordUpperCase = word.toUpperCase();

        if (!wordUpperCase.contains("Т")) {
            return false;
        }

        if (!wordUpperCase.contains("Е")) {
            return false;
        }

        if (wordUpperCase.contains("К")) {
            return false;
        }

        return true;
    }
}
