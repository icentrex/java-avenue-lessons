package lesson19;

import java.util.function.Function;

public class CoinMapper implements Function<Coin, ShortCoin> {
    @Override
    public ShortCoin apply(Coin coin) {
        return new ShortCoin(coin.getNominal());
    }
}
