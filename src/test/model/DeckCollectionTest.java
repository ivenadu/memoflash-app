package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        deckSet.removeDeck(0);
        assertEquals(deckSet.size(), 2);
        assertEquals(deckSet.viewDeckTitles(), "0. ABC's\n1. Animal Species\n");

    }

    @Test
    public void removeDeckNoExist(){
        quickDeckAdder();
        try {
            deckSet.removeDeck(4);
        } catch (IndexOutOfBoundsException ex) {
            //good
        }
        assertEquals(deckSet.size(), 3);
        assertEquals(deckSet.viewDeckTitles(), "0. History\n1. ABC's\n2. Animal Species\n");
    }

    @Test
    public void removeDeckNoDecks(){
        try {
            deckSet.removeDeck(1);
        } catch (IndexOutOfBoundsException ex) {
            //good
        }
        assertEquals(deckSet.size(), 0);
    }

    @Test
    public void viewDeckTitles() {
        quickDeckAdder();
        assertEquals(deckSet.viewDeckTitles(), "0. History\n1. ABC's\n2. Animal Species\n");
    }

    @Test
    public void viewDeckTitlesNone() {
        assertEquals(deckSet.viewDeckTitles(), "There are no decks.");
    }


    @Test
    public void testSetActiveDeck() {
        Deck d0 = new Deck("Everything");
        Deck d1 = new Deck("Nothing");

        deckSet.addDeck(d0);
        deckSet.addDeck(d1);
        deckSet.setActiveDeck(d0);
        assertEquals("Everything", deckSet.getActiveDeck().getTitle());
    }

    @Test
    public void testGetActiveDeck() {
        Deck d0 = new Deck("Everything");
        Deck d1 = new Deck("Nothing");
        deckSet.addDeck(d0);
        deckSet.addDeck(d1);
        assertEquals(d1, deckSet.getActiveDeck()); // verify active deck
        deckSet.setActiveDeck(d0); // switch active deck
        assertEquals(d0, deckSet.getActiveDeck()); //verify deck has been switched
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
