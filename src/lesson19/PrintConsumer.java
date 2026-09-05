package lesson19;

import java.util.function.Consumer;

public class PrintConsumer implements Consumer<String> {
    @Override
    public void accept(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }

        String wordUpperCase = word.toUpperCase();

        if (!wordUpperCase.contains("Т")) {
            return;
        }

        if (!wordUpperCase.contains("Е")) {
            return;
        }

        if (wordUpperCase.contains("К")) {
            return;
        }

        System.out.println(word);

    }
}
