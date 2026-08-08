package lesson10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private final ArrayList<Card> cards = new ArrayList<>();

    public void refreshDeck() {
        cards.clear();
        cards.add(new Card(2, "Двойка крести"));
        cards.add(new Card(2, "Двойка бубей"));
        cards.add(new Card(2, "Двойка пик"));
        cards.add(new Card(2, "Двойка червей"));

        cards.add(new Card(3, "Тройка крести"));
        cards.add(new Card(3, "Тройка бубей"));
        cards.add(new Card(3, "Тройка пик"));
        cards.add(new Card(3, "Тройка червей"));

        cards.add(new Card(4, "Четверка крести"));
        cards.add(new Card(4, "Четверка бубей"));
        cards.add(new Card(4, "Четверка пик"));
        cards.add(new Card(4, "Четверка червей"));

        cards.add(new Card(5, "Пятерка крести"));
        cards.add(new Card(5, "Пятерка бубей"));
        cards.add(new Card(5, "Пятерка пик"));
        cards.add(new Card(5, "Пятерка червей"));

        cards.add(new Card(6, "Шестерка крести"));
        cards.add(new Card(6, "Шестерка бубей"));
        cards.add(new Card(6, "Шестерка пик"));
        cards.add(new Card(6, "Шестерка червей"));

        cards.add(new Card(7, "Семерка крести"));
        cards.add(new Card(7, "Семерка бубей"));
        cards.add(new Card(7, "Семерка пик"));
        cards.add(new Card(7, "Семерка червей"));

        cards.add(new Card(8, "Восьмерка крести"));
        cards.add(new Card(8, "Восьмерка бубей"));
        cards.add(new Card(8, "Восьмерка пик"));
        cards.add(new Card(8, "Восьмерка червей"));

        cards.add(new Card(9, "Девятка крести"));
        cards.add(new Card(9, "Девятка бубей"));
        cards.add(new Card(9, "Девятка пик"));
        cards.add(new Card(9, "Девятка червей"));

        cards.add(new Card(10, "Десятка крести"));
        cards.add(new Card(10, "Десятка бубей"));
        cards.add(new Card(10, "Десятка пик"));
        cards.add(new Card(10, "Десятка червей"));

        cards.add(new Card(10, "Валет крести"));
        cards.add(new Card(10, "Валет бубей"));
        cards.add(new Card(10, "Валет пик"));
        cards.add(new Card(10, "Валет червей"));

        cards.add(new Card(10, "Дама крести"));
        cards.add(new Card(10, "Дама бубей"));
        cards.add(new Card(10, "Дама пик"));
        cards.add(new Card(10, "Дама червей"));

        cards.add(new Card(10, "Король крести"));
        cards.add(new Card(10, "Король бубей"));
        cards.add(new Card(10, "Король пик"));
        cards.add(new Card(10, "Король червей"));

        cards.add(new Card(11, "Туз крести"));
        cards.add(new Card(11, "Туз бубей"));
        cards.add(new Card(11, "Туз пик"));
        cards.add(new Card(11, "Туз червей"));


    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card getRandomCard() {
        return cards.get(new Random().nextInt(cards.size()));
    }
}
