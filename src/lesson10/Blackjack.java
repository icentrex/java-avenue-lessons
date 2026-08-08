package lesson10;

public class Blackjack {

    public void start(int playersQuantity) {
        if (playersQuantity < 1) {
            System.out.println("Слишком мало игроков. Добавьте хотя бы 1 игрока.");
            return;
        }

        if (playersQuantity > 5) {
            System.out.println("Превышено максимальное количество игроков (5)");
            return;
        }

        Deck deck = new Deck();
        deck.refreshDeck();
        deck.shuffleDeck();

        //1 Создать игрока
        //2 Раздать по две карты
        //3 Раздать остальные карты (пока игроки берут)
        //4 Печать результатов в консоль
        //5 Определение победителя
    }
}
