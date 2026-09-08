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
        /*
         Условия:
         - Может быть два победителя
         - Дилер имеет приоритет если очки равны он победитель
         - Если все проиграли, дилер проиграл, то диллер выиграл

         исходы

         1 все набрали 21 = все победители ok
         2 все набрали больше 21 = все проигравшие ok
         3 Все набрали меньше 21. Выиграл тот кто ближе к 21 ok
         4 есть 1 и 2 ok
         5 есть 1 и 3 ok
         6 есть 2 и 3 ok

         Допусловие: наличие дилера меняет логику
         7 все набрали 21 = все победители. Если дилер есть среди них - победитель он ок
         8 все набрали больше 21 = все проигравшие. Если дилер есть среди них - победитель он ок
         9 Все набрали меньше 21. Выиграл тот кто ближе к 21. Если очки равны, то победил дилер
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

        //1. все набрали 21 = все победители
        //4. есть =21 и >21
        if (!winners.isEmpty()) {
            System.out.println("Победители: ");
            winners.forEach(winner ->
                    System.out.println("Игрок: " + winner.getName() + ", количество очков: " + winner.countPoints()));
        }

        //2. все набрали больше 21 = все проигравшие
        //4. есть =21 и >21. Обычное условие, есть победители и проигравшие, которые перебрали
        //6. есть >21 и <21. Обычное условие, есть проигравшие, которые перебрали, есть победитель среди тех, кто недобрал
        if (!losers.isEmpty()) {
            System.out.println("Перебор: ");
            losers.forEach(loser -> System.out.println("Игрок: " + loser.getName()
                    + ", количество очков: " + loser.countPoints()));
        }

        //3. Все набрали меньше 21. Выиграл тот кто ближе к 21

        if (!playersToCompare.isEmpty()) {
            //5. есть =21 и <21: Если есть победители, то playersToCompare сразу проигравшие
            if (!winners.isEmpty()) {
                System.out.println("Проигравшие: ");
                playersToCompare.forEach(player -> System.out.println("Игрок: " + player.getName()
                        + ", количество очков: " + player.countPoints()));

                //6. есть >21 и <21. Обычное условие, есть проигравшие, которые перебрали и
                //есть победитель и проигравшие среди тех, кто недобрал
            } else {
                playersToCompare.sort(Comparator.comparingInt(player -> 21 - player.countPoints()));

                Player bestPlayer = playersToCompare.getFirst();
                int bestPoints = bestPlayer.countPoints();

                System.out.println("Победители: ");
                playersToCompare
                        .stream()
                        .filter(player -> player.countPoints() == bestPoints)
                        .forEach(player -> System.out.println("Игрок: " + player.getName()
                                + ", количество очков: " + player.countPoints()));

                System.out.println("Проигравшие: ");
                playersToCompare
                        .stream()
                        .filter(player -> player.countPoints() < bestPoints)
                        .forEach(player -> System.out.println("Игрок: " + player.getName()
                                + ", количество очков: " + player.countPoints()));
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
