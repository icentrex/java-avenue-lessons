package lesson10;

public class Dealer extends Player {

    public Dealer() {
        super("Dealer");
    }

    @Override
    public boolean isNeedNextCard() {
        return countPoints() < 17;
    }
}
