import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void takeCardFromDeck(Deck deck) {
        hand.add(deck.takeCard());
    }
    public String toString() {
        String output = "";
        for(Card card: hand) {
            output += card + " - ";
        }
        return output;
    }
    public int calculatedScore() {
        int score = 0;
        int aces = 0;

        for(Card card: hand) {
            score += card.getScore();
            if(card.getScore() == 11){
                aces++;
            }
        }
        if(score > 21 && aces > 0) {
            while(aces > 0 && score > 21) {
                aces--;
                score-=10;
            }
        }
        return score;
    }
    public void discardHandToDeck(Deck discardDeck) {
        discardDeck.addCards(hand);
        hand.clear();
    }

    public Card getCard(int idx)
    {
        return hand.get(idx);
    }
}
