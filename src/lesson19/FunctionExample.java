package lesson19;


import java.util.TreeSet;

public class FunctionExample {
    public static void main(String[] args) {
        Coin coin1 = new Coin(5, 1999, "Золото", 2.5);
        Coin coin2 = new Coin(5, 1999, "Золото", 2.5);
        Coin coin3 = new Coin(10, 1999, "Золото", 2.5);
        Coin coin4 = new Coin(50, 1899, "Олово", 5);
        Coin coin5 = new Coin(5, 1869, "Серебро", 10);
        Coin coin6 = new Coin(5, 1869, "Палладий", 10);
        Coin coin7 = new Coin(5, 1869, "Палладий", 11);

        System.out.println("Вариант 1 - используем сортировку по умолчанию.");
        TreeSet<Coin> coinSet = new TreeSet<>();
        coinSet.add(coin1);
        coinSet.add(coin2);
        coinSet.add(coin3);
        coinSet.add(coin4);
        coinSet.add(coin5);
        coinSet.add(coin6);
        coinSet.add(coin7);

        TreeSet<ShortCoin> shortCoinSet = new TreeSet<>();
        CoinMapper mapper = new CoinMapper();
        for (Coin coin : coinSet) {
            shortCoinSet.add(mapper.apply(coin));
        }
    }
}
