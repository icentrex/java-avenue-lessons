package lesson10;

public class Dealer extends Player {

    public Dealer() {
        super("Dealer");
    }

    @Override
    public boolean isNeedNextCard() {
        int points = countPoints();
        if (points > 21) {
            System.out.println("У вас перебор!\n");
            return false;
        }

        return countPoints() < 17;
    }
}
