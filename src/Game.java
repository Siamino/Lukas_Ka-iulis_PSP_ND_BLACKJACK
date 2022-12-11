public class Game {
    private Deck deck;
    private Deck discarded;
    private Dealer dealer;
    private Player player;
    private int wins;
    private int losses;
    private int evens;

    static final int shortPause = 500;

    static final int longPause = 1000;

    private String youWinLogo = "##    ##  #######  ##     ##    ##      ## #### ##    ## \n" +
            " ##  ##  ##     ## ##     ##    ##  ##  ##  ##  ###   ## \n" +
            "  ####   ##     ## ##     ##    ##  ##  ##  ##  ####  ## \n" +
            "   ##    ##     ## ##     ##    ##  ##  ##  ##  ## ## ## \n" +
            "   ##    ##     ## ##     ##    ##  ##  ##  ##  ##  #### \n" +
            "   ##    ##     ## ##     ##    ##  ##  ##  ##  ##   ### \n" +
            "   ##     #######   #######      ###  ###  #### ##    ## ";

    public Game() {
        this.wins = 0;
        this.losses = 0;
        this.evens = 0;
        deck = new Deck(true);
        discarded = new Deck();

        dealer = new Dealer(); //paveldejimas
        player = new Player(); //paveldejimas

        deck.shuffle();
        startRound();
    }

    private void startRound() {
        if (wins > 0 || losses > 0 || evens > 0) {
            System.out.println();
            System.out.println("Starting next round - Wins: " + wins + " Losses: " + losses + " Evens: " + evens);
            dealer.getHand().discardHandToDeck(discarded);
            player.getHand().discardHandToDeck(discarded);
            pause(longPause);
        }
        if (deck.cardsLeft() < 4) {
            deck.reloadDeckFromDiscard(discarded);
        }
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        dealer.printHand(); //Polimorfizmas
        pause(shortPause);
        player.printHand();
        pause(shortPause);

        if (dealer.hasBlackJack()) {
            dealer.printHand();
            if (player.hasBlackJack()) {
                System.out.println("You both have 21 - Even\n");
                pause(longPause);
                evens++;
                startRound();
            } else {
                System.out.println("Dealer has BlackJack. (YOU LOSE)\n");
                pause(longPause);
                dealer.printHand();
                losses++;
                startRound();
            }
        }
        if (player.hasBlackJack()) {
            System.out.println("You have BlackJack!");
            System.out.println(youWinLogo);
            pause(longPause);
            wins++;
            startRound();
        }

        player.makeDecision(deck, discarded);
        if (player.hasBlackJack()) {
            System.out.println("You have BlackJack!");
            System.out.println(youWinLogo);
            pause(longPause);
            wins++;
            startRound();
        }
        pause(shortPause);

        if (player.getHand().calculatedScore() > 21) {
            System.out.println("You have gone over 21 (YOU LOSE)\n");
            pause(longPause);
            losses++;
            startRound();
        }

        dealer.printHand();

        while (dealer.getHand().calculatedScore() < 17) {
            dealer.hit(deck, discarded);
            pause(longPause);
        }

        if (dealer.getHand().calculatedScore() > 21) {
            System.out.println(youWinLogo);
            wins++;
        } else if (dealer.getHand().calculatedScore() > player.getHand().calculatedScore()) {
            System.out.println("YOU LOSE\n");
            pause(longPause);
            losses++;
        } else if (player.getHand().calculatedScore() > dealer.getHand().calculatedScore()) {
            System.out.println(youWinLogo);
            pause(longPause);
            wins++;
        } else {
            System.out.println("Even\n");
            pause(longPause);
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
