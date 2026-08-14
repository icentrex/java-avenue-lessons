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
            System.out.println("Игрок: " + player.getName());
            System.out.println("Карты на руках: ");
            for (Card card : player.getHand()) {
                System.out.println(card);
            }
            System.out.println("Сумма очков: " + player.countPoints() + "\n");

            while (player.isNeedNextCard()) {
                System.out.println("Беру еще одну карту..");
                Card card = deck.getRandomCard();
                System.out.println(card);
                player.addCardToHand(card);
                System.out.println("Сумма очков: " + player.countPoints() + "\n");
            }
        }

        //4 Печать результатов в консоль
        System.out.println("\n=== Подсчет очков ===");
        for (Player player : players) {
            System.out.println(player.getName());
            System.out.println("Сумма очков: " + player.countPoints() + "\n");
        }

        //5 Определение победителя
        //может быть два победителя
        //диллер имеет приоритет если очки равны он победитель
        //если все проиграли, дилер проиграл, то диллер выиграл
        System.out.println("\n=== Таблица результатов ===");
        ArrayList<Player> winners = new ArrayList<>();
        ArrayList<Player> losers = new ArrayList<>();
        ArrayList<Player> playersToCompare = new ArrayList<>();

        for (Player player : players) {
            if (player.countPoints() == 21) {
                winners.add(player);
            } else if (player.countPoints() > 21) {
                losers.add(player);
            } else {
                playersToCompare.add(player);
            }
        }

        if (!winners.isEmpty()) {
            System.out.println("Победители: ");
            for (Player winner : winners) {
                System.out.println("Игрок: " + winner.getName() + ", количество очков: " + winner.countPoints());
            }
        }

        if (!playersToCompare.isEmpty() && winners.isEmpty()) {
            playersToCompare.sort((Player currentPlayer, Player nextPlayer) -> {
                int currentPlayerDistanceTo21 = 21 - currentPlayer.countPoints();
                int nextPlayerDistanceTo21 = 21 - nextPlayer.countPoints();
                return Integer.compare(currentPlayerDistanceTo21, nextPlayerDistanceTo21);
            });

            Player bestPlayer = playersToCompare.getFirst();
            int bestPoints = bestPlayer.countPoints();
            System.out.println("Победитель: " + bestPlayer.getName() + " с " + bestPoints + " очками");

            for (int player = 1; player < playersToCompare.size(); player++) {
                Player nextWinner = playersToCompare.get(player);

                int nextWinnerPoints = nextWinner.countPoints();
                if (nextWinnerPoints == bestPoints) {
                    System.out.println("Также победитель: " + nextWinner.getName() + " с " + nextWinnerPoints + " очками");
                }
            }
        }

        if (!losers.isEmpty() && winners.isEmpty() && playersToCompare.isEmpty()) {
            System.out.println("Проигравший: ");
            for (Player loser : losers) {
                System.out.println("Игрок: " + loser.getName() + ", количество очков: " + loser.countPoints());
            }

            if (losers.size() > 1 && losers.contains(dealer)) {
                System.out.println("По правилам победу одержал дилер!\n");
            }
        }
    }
}
