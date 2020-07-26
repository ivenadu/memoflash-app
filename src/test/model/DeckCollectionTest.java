package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckCollectionTest {
    DeckCollection deckSet;

    @BeforeEach
    public void preTest() {
        deckSet = new DeckCollection();
    }

    @Test
    public void addDeckTest() {
        quickDeckAdder();
        assertEquals(deckSet.size(), 3);

    }

    @Test
    public void removeDeckTest() {
        quickDeckAdder();
        deckSet.removeDeck("History");
        assertEquals(deckSet.size(), 2);
        assertEquals(deckSet.viewDeckTitles(), "Deck: ABC's\nDeck: Animal Species\n");

    }

    @Test
    public void removeDeckNoExist() {
        quickDeckAdder();
        deckSet.removeDeck("You Can't Find This");
        assertEquals(deckSet.size(), 3);
        assertEquals(deckSet.viewDeckTitles(), "Deck: History\nDeck: ABC's\nDeck: Animal Species\n");
    }

    @Test
    public void removeDeckNoDecks() {
        deckSet.removeDeck("Deck Title");
        assertEquals(deckSet.size(), 0);
    }

    @Test
    public void viewDeckTitles() {
        quickDeckAdder();
        assertEquals(deckSet.viewDeckTitles(), "Deck: History\nDeck: ABC's\nDeck: Animal Species\n");
    }

    @Test
    public void viewDeckTitlesNone() {
        assertEquals(deckSet.viewDeckTitles(), "There are no decks.");
    }

    public void quickDeckAdder() {
        Deck deck1 = new Deck("History");
        Deck deck2 = new Deck("ABC's");
        Deck deck3 = new Deck("Animal Species");
        deckSet.addDeck(deck1);
        deckSet.addDeck(deck2);
        deckSet.addDeck(deck3);
    }
}
