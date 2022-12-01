public class Game {
    private Deck deck;
    private Deck discarded;
    private Dealer dealer;
    private Player player;
    private int wins;
    private int losses;
    private int evens;

    public Game() {
        this.wins = 0;
        this.losses = 0;
        this.evens = 0;
        deck = new Deck(true);
        discarded = new Deck();

        dealer = new Dealer();
        player = new Player();

        deck.shuffle();
        startRound();
    }

    private void startRound() {
        if (wins > 0 || losses > 0 || evens > 0) {
            System.out.println();
            System.out.println("Starting next round - Wins: " + wins + " Losses: " + losses + " Evens: " + evens);
            dealer.getHand().discardHandToDeck(discarded);
            player.getHand().discardHandToDeck(discarded);
            pause(1000);
        }
        if (deck.cardsLeft() < 4) {
            deck.reloadDeckFromDiscard(discarded);
        }
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        dealer.printFirstHand();
        pause(500);
        player.printHand();
        pause(500);

        if (dealer.hasBlackJack()) {
            dealer.printHand();
            if (player.hasBlackJack()) {
                System.out.println("You both have 21 - Even\n");
                pause(1000);
                evens++;
                startRound();
            } else {
                System.out.println("Dealer has BlackJack. You lose.\n");
                pause(1000);
                dealer.printHand();
                losses++;
                startRound();
            }
        }
        if (player.hasBlackJack()) {
            System.out.println("You have BlackJack! (You win)\n");
            pause(1000);
            wins++;
            startRound();
        }

        player.makeDecision(deck, discarded);
        if (player.hasBlackJack()) {
            System.out.println("You have BlackJack! (You win)\n");
            pause(1000);
            wins++;
            startRound();
        }
        pause(500);

        if (player.getHand().calculatedScore() > 21) {
            System.out.println("You have gone over 21 (You lose)\n");
            pause(1000);
            losses++;
            startRound();
        }

        dealer.printHand();

        while (dealer.getHand().calculatedScore() < 17) {
            dealer.hit(deck, discarded);
            pause(1000);
        }

        if (dealer.getHand().calculatedScore() > 21) {
            System.out.println("Dealer losses\n");
            pause(1000);
            wins++;
        } else if (dealer.getHand().calculatedScore() > player.getHand().calculatedScore()) {
            System.out.println("You lose\n");
            pause(1000);
            losses++;
        } else if (player.getHand().calculatedScore() > dealer.getHand().calculatedScore()) {
            System.out.println("You win\n");
            pause(1000);
            wins++;
        } else {
            System.out.println("Even\n");
            pause(1000);
            evens++;
        }
        startRound();
    }

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
