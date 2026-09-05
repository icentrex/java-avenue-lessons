package lesson19;

import java.util.Random;
import java.util.function.Supplier;

public class RandomEvenNumberSupplier implements Supplier<Integer> {
    @Override
    public Integer get() {
        int number = -1;
        while (number % 2 != 0) {
            number = new Random().nextInt(1, 1000);
        }

        return number;
    }
}
