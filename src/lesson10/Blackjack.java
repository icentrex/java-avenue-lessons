package lesson10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
        createPlayer(playersQuantity);

        //2 Раздать по две карты
        giveStartHand();

        //3 Раздать остальные карты (пока игроки берут)
        giveMoreCards();

        //4 Печать результатов в консоль
        printResults();

        //5 Определение победителя
        calcWinner();
    }

    public void calcWinner() {
        /**
         * Правила определения победителя
         *
         * Условия:
         * - Может быть два победителя
         * - Дилер имеет приоритет если очки равны он победитель
         * - Если все проиграли, дилер проиграл, то диллер выиграл
         *
         * Колонки:
         * рука игрока (=21, <21, >21) и рука дилера (dealer = 21, dealer < 21, dealer > 21).
         * "+" — условие выполняется для данного исхода.
         *
         * +--------+-----+-----+-----+-------------+------------+-------------+------------------------------------------------+----------------+
         * | Исходы | =21 | <21 | >21 | dealer = 21 | dealer <21 | dealer > 21 | Победитель                                    | Проигравший    |
         * +--------+-----+-----+-----+-------------+------------+-------------+------------------------------------------------+----------------+
         * |   1    |  +  |     |     |      +      |            |             | дилер                                          | все остальные  |
         * |  10    |  +  |  +  |     |      +      |            |             | дилер                                          | все остальные  |
         * |  13    |  +  |     |  +  |      +      |            |             | дилер                                          | все остальные  |
         * |  19    |  +  |  +  |  +  |      +      |            |             | дилер                                          | все остальные  |
         * |  16    |     |  +  |  +  |      +      |            |             | дилер                                          | все остальные  |
         * |   4    |     |  +  |     |      +      |            |             | дилер                                          | все остальные  |
         * |   7    |     |     |  +  |      +      |            |             | дилер                                          | все остальные  |
         * |   8    |     |     |  +  |             |      +     |             | дилер                                          | все остальные  |
         * |   9    |     |     |  +  |             |            |      +      | дилер                                          | все остальные  |
         * |  18    |     |  +  |  +  |             |            |      +      | кто ближе к 21                                 | все остальные  |
         * |   6    |     |  +  |     |             |            |      +      | кто ближе к 21                                 | все остальные  |
         * |  17    |     |  +  |  +  |             |      +     |             | кто ближе к 21. Если = с дилером победил дилер | все остальные  |
         * |   5    |     |  +  |     |             |      +     |             | кто ближе к 21. Если = с дилером победил дилер | все остальные  |
         * |  11    |  +  |  +  |     |             |      +     |             | кто набрал 21                                  | все остальные  |
         * |  12    |  +  |  +  |     |             |            |      +      | кто набрал 21                                  | все остальные  |
         * |  14    |  +  |     |  +  |             |      +     |             | кто набрал 21                                  | все остальные  |
         * |  15    |  +  |     |  +  |             |            |      +      | кто набрал 21                                  | все остальные  |
         * |   2    |  +  |     |     |             |      +     |             | кто набрал 21                                  | дилер          |
         * |  20    |  +  |  +  |  +  |             |      +     |             | кто набрал 21                                  | все остальные  |
         * |  21    |  +  |  +  |  +  |             |            |      +      | кто набрал 21                                  | все остальные  |
         * |   3    |  +  |     |     |             |            |      +      | кто набрал 21                                  | дилер          |
         * +--------+-----+-----+-----+-------------+------------+-------------+------------------------------------------------+----------------+
         */
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
            if (winners.contains(dealer)) {
                System.out.println("Победил дилер, количество очков: " + dealer.countPoints());
                System.out.println("Проигравшие: ");
                players.stream()
                        .filter(player -> !player.equals(dealer))
                        .forEach(player ->
                                System.out.println("Игрок: " + player.getName() + ", количество очков: " + player.countPoints()));
            } else {
                System.out.println("Победители: ");
                winners.forEach(winner ->
                        System.out.println("Игрок: " + winner.getName() + ", количество очков: " + winner.countPoints()));
                System.out.println("Проигравшие: ");
                players.stream()
                        .filter(player -> !winners.contains(player))
                        .forEach(player ->
                                System.out.println("Игрок: " + player.getName() + ", количество очков: " + player.countPoints()));
            }
        } else if (!playersToCompare.isEmpty()) {
            playersToCompare.sort(Comparator.comparingInt(player -> 21 - player.countPoints()));

            Player bestPlayer = playersToCompare.getFirst();
            int bestPoints = bestPlayer.countPoints();

            List<Player> playersToCompareWinners = playersToCompare.stream()
                    .filter(player -> player.countPoints() == bestPoints)
                    .toList();

            if (playersToCompareWinners.contains(dealer)) {
                System.out.println("Победил дилер, количество очков: " + dealer.countPoints());
                System.out.println("Проигравшие: ");
                players.stream()
                        .filter(player -> !player.equals(dealer))
                        .forEach(player ->
                                System.out.println("Игрок: " + player.getName() + ", количество очков: " + player.countPoints()));
            } else {
                System.out.println("Победители: ");
                playersToCompareWinners.forEach(player -> System.out.println("Игрок: " + player.getName()
                        + ", количество очков: " + player.countPoints()));

                System.out.println("Проигравшие: ");
                players.stream()
                        .filter(player -> !playersToCompareWinners.contains(player))
                        .forEach(player ->
                                System.out.println("Игрок: " + player.getName() + ", количество очков: " + player.countPoints()));
            }
        } else if (!losers.isEmpty()) {
            if (losers.contains(dealer)) {
                System.out.println("Победил дилер (приоритет дилера), количество очков: " + dealer.countPoints());
                System.out.println("Проигравшие: ");
                players.stream()
                        .filter(player -> !player.equals(dealer))
                        .forEach(player ->
                                System.out.println("Игрок: " + player.getName() + ", количество очков: " + player.countPoints()));
            } else {
                System.out.println("Проигравшие: ");
                players.forEach(player ->
                        System.out.println("Игрок: " + player.getName() + ", количество очков: " + player.countPoints()));
            }
        }
    }

    public void createPlayer(int playersQuantity) {
        Scanner scanner = new Scanner(System.in);
        for (int playerNumber = 1; playerNumber <= playersQuantity; playerNumber++) {
            System.out.println("Игрок номер " + playerNumber + " введите своё имя:");
            String playerName = scanner.nextLine();
            Player player = new Player(playerName);
            players.add(player);
        }
        players.add(dealer);
        System.out.println("\nТасую колоду...");
        System.out.println("Раздаю по две карты...\n");
    }

    public void giveStartHand() {
        deck.refreshDeck();
        deck.shuffleDeck();

        for (Player player : players) {
            Card card1 = deck.getRandomCard();
            Card card2 = deck.getRandomCard();
            player.addCardToHand(card1);
            player.addCardToHand(card2);
        }
    }

    public void giveMoreCards() {
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
    }

    public void printResults() {
        System.out.println("\n=== Подсчет очков ===");
        for (Player player : players) {
            System.out.println(player.getName());
            System.out.println("Сумма очков: " + player.countPoints() + "\n");
        }
    }
}