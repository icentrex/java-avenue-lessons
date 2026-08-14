package lesson10;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
    Deck deck = new Deck();
    Dealer dealer = new Dealer();
    ArrayList<Player> players = new ArrayList<>();

    public void start(int playersQuantity) {
        System.out.println("=== Консольная игра Blackjack v1.0 ===");
        if (playersQuantity < 1) {
            System.out.println("Слишком мало игроков. Добавьте хотя бы 1 игрока.");
            return;
        }

        if (playersQuantity > 5) {
            System.out.println("Превышено максимальное количество игроков (5)");
            return;
        }

        //1 Создать игрока
        players.add(dealer);
        Scanner scanner = new Scanner(System.in);
        for (int playerNumber = 1; playerNumber <= playersQuantity; playerNumber++) {
            System.out.println("Игрок номер " + playerNumber + " введите своё имя:");
            String playerName = scanner.nextLine();
            Player player = new Player(playerName);
            players.add(player);
        }
        //scanner.close();
        System.out.println("\nТасую колоду...");
        System.out.println("Раздаю по две карты...\n");

        //2 Раздать по две карты
        deck.refreshDeck();
        deck.shuffleDeck();

        for (Player player : players) {
            Card card1 = deck.getRandomCard();
            Card card2 = deck.getRandomCard();
            player.addCardToHand(card1);
            player.addCardToHand(card2);
        }

        //3 Раздать остальные карты (пока игроки берут)
        for (Player player : players) {
            System.out.println(player);
            while (player.isNeedNextCard()) {
                System.out.println("Получаю еще одну карту..");
                Card card = deck.getRandomCard();
                player.addCardToHand(card);
            }
        }

        //4 Печать результатов в консоль
        System.out.println("\n=== Подсчет результатов ===");
        for (Player player : players) {
            System.out.println(player);
            System.out.println("Сумма очков: " + player.countPoints() + "\n");
        }
        //5 Определение победителя
        //может быть два победителя
        //диллер имеет приоритет если очки равны он победитель
        // если все проиграли, дилер проиграл, то диллер выиграл
    }
}
