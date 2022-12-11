import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DeckTest {

    @Test
    public void deckInitializationValues() {
        Deck deck = new Deck();
        assertEquals(0, deck.cardsLeft());
    }

    @Test
    public void deckAfterAddingCardsSize() {
        Deck deck = new Deck(true);
        assertEquals(52, deck.cardsLeft());
    }

    @Test public void deckAfterClearingSize() {
        Deck deck = new Deck(true);
        deck.emptyDeck();
        assertEquals(0, deck.cardsLeft());

    }

}
