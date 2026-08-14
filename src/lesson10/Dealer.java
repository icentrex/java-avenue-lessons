package lesson10;

public class Dealer extends Player {

    public Dealer() {
        super("Dealer");
    }

    @Override
    public boolean isNeedNextCard() {
        System.out.println("Карты на руках: ");
        for (Card card : getHand()) {
            System.out.println(card);
        }
        System.out.println("Сумма очков: " + countPoints() + "\n");

        int points = countPoints();
        if (points > 21) {
            System.out.println("У вас перебор. Вы проиграли.\n");
            return false;
        }

        return countPoints() < 17;
    }
}
