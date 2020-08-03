package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

public class DeckCollectionTest {
    DeckCollection deckSet;
    private Deck d1;
    private Deck d2;
    private Deck d3;

    @BeforeEach
    public void preTest() {
        deckSet = new DeckCollection();
    }

    @Test
    public void getActiveIndexTest() {
        quickDeckAdder();
        assertEquals(2, deckSet.getActiveIndex());
    }

    @Test
    public void addDeckTest() {
        quickDeckAdder();
        assertEquals(deckSet.size(), 3);
    }

    @Test
    public void addDeckException() {
        try {
            deckSet.addDeck(null);
            fail("gone too far");
        } catch (RuntimeException ex) {
            //good
        }
    }

    @Test
    public void removeDeckTest() {
        quickDeckAdder();
        deckSet.removeDeck(0);
        assertEquals(deckSet.size(), 2);
        assertFalse(deckSet.deckCollection.contains(d1)); // does not contain removed deck
        assertTrue(deckSet.deckCollection.contains(d2)); // but still contains others
        assertTrue(deckSet.deckCollection.contains(d3));

    }

    @Test
    public void removeDeckException() {
        Deck d = new Deck("A deck.");
        try {
            deckSet.addDeck(d);
            assertTrue(deckSet.size() == 1);
            deckSet.removeDeck(0);
            fail("gone too far");
        } catch (RuntimeException ex) {
            //good
        }
        assertTrue(deckSet.size() == 1);

    }

    @Test
    public void removeDeckNoExist(){
        quickDeckAdder();
        try {
            deckSet.removeDeck(4); // there is no index number 4, so should throw
        } catch (IndexOutOfBoundsException ex) {
            //good
        }
        assertEquals(deckSet.size(), 3);
        assertEquals(deckSet.viewDeckTitles(), "0. History\n1. ABC's\n2. Animal Species\n");
    }

    @Test
    public void removeDeckNoDecks(){
        try {
            deckSet.removeDeck(1); // no decks were added beforehand
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
    public void testGetActiveDeck() {
        quickDeckAdder();
        assertEquals(d3, deckSet.getActiveDeck());
    }

    @Test
    public void testGetActiveDeckNone() {
        try {
            deckSet.getActiveDeck();
            fail("should have thrown");
        } catch (RuntimeException ex) {
            //good
        }
    }

    @Test
    public void testSetActiveDeck() {
        quickDeckAdder();
        assertEquals(d3, deckSet.getActiveDeck()); // verify active deck
        deckSet.setActiveDeck(d2); // switch active deck
        assertEquals(d2, deckSet.getActiveDeck()); // verify deck has been switched
    }

    @Test
    public void retrieveDeckFromIndexTest() {
        quickDeckAdder();
        assertEquals(d1, deckSet.retrieveDeckWithIndex(0));
        assertEquals(d2, deckSet.retrieveDeckWithIndex(1));
        assertEquals(d3, deckSet.retrieveDeckWithIndex(2));
    }

    public void quickDeckAdder() {
        d1 = new Deck("History");
        d2 = new Deck("ABC's");
        d3 = new Deck("Animal Species");
        deckSet.addDeck(d1);
        deckSet.addDeck(d2);
        deckSet.addDeck(d3);
    }
}
