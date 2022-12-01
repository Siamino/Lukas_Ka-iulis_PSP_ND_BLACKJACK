import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
    }

    public Deck(boolean makeDeck) {
        deck = new ArrayList<Card>();
        if(makeDeck) {
            for(Type type : Type.values()){
                for (TypeScore typeScore : TypeScore.values()) {
                    deck.add(new Card(type,typeScore));
                }
            }
        }
    }

    public void addCard(Card card) {
        deck.add(card);
    }
    public void addCards(ArrayList<Card> cards){
        deck.addAll(cards);
    }
    public void shuffle() {
        ArrayList<Card> shuffled = new ArrayList<Card>();
        while(deck.size() > 0){
            int cardToPull = (int)(Math.random()*(deck.size()-1));
            shuffled.add(deck.get(cardToPull));
            deck.remove(cardToPull);
        }
        deck =shuffled;
    }

    public Card takeCard() {
        Card cardToTake = new Card(deck.get(0));
        deck.remove(0);
        return cardToTake;
    }
    public boolean hasCards() {
        if(deck.size()>0) {
            return true;
        } else {return false;}
    }

    public ArrayList<Card> getCards() {
        return deck;
    }

    public void emptyDeck() {
        deck.clear();
    }
    public int cardsLeft() {
        return deck.size();
    }
    public String toString() {
        String output = "";

        for(Card card: deck) {
            output += card;
            output += "\n";
        }
        return output;
    }
    public void reloadDeckFromDiscard(Deck discard) {
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck.");
    }
}
