package lesson10;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Player {

    private final String name;
    private final ArrayList<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    /**
     * Получить карту из колоды и положить в руку игрока
     *
     * @param card
     */
    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public boolean isNeedNextCard() {
        int points = countPoints();
        if (points > 21) {
            System.out.println("У вас " + points + " очков. Перебор. Вы проиграли.");
            return false;
        }

        System.out.println("Карты у вас на руках: ");
        for (Card card : hand) {
            System.out.println(card);
        }
        System.out.println("Сумма очков: " + countPoints());

        System.out.println("\nХотите ли вы взять еще карту (Да/Нет)?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input.equalsIgnoreCase("Да");
    }

    public int countPoints() {
        int points = 0;
        for (Card card : hand) {
            points += card.nominal();
        }
        return points;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hand=" + hand +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(hand, player.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }
}
