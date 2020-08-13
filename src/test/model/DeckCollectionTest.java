package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckCollectionTest {
    DeckCollection decks;
    private Deck d1;
    private Deck d2;
    private Deck d3;

    @BeforeEach
    public void preTest() {
        decks = new DeckCollection();
    }

    @Test
    public void getActiveIndexTest() {
        quickDeckAdder();
        assertEquals(2, decks.getActiveIndex());
    }

    @Test
    public void addDeckTest() {
        quickDeckAdder();
        assertEquals(decks.size(), 3);
    }

    @Test
    public void addDeckExceptionNull() {
        try {
            decks.addDeck(null);
            fail("gone too far");
        } catch (RuntimeException ex) {
            //good
        }
    }

    @Test
    public void removeDeckTest() {
        quickDeckAdder();
        decks.removeDeck(0);
        assertEquals(decks.size(), 2);
        assertFalse(decks.decks.contains(d1)); // does not contain removed deck
        assertTrue(decks.decks.contains(d2)); // but still contains others
        assertTrue(decks.decks.contains(d3));
    }

    @Test
    public void removeDeckIndexLessThanSize() {
        quickDeckAdder();
        decks.setActiveDeck(d1);
        assertEquals(0, decks.getActiveIndex()); // verify the index is < the number of decks present
        decks.removeDeck(0);
        assertFalse(decks.decks.contains(d1));
        assertTrue(decks.decks.contains(d2));
        assertTrue(decks.decks.contains(d3));
        assertTrue(decks.decks.get(0) == d2);
        assertTrue(decks.decks.get(1) == d3);
    }

    @Test
    public void removeDeckException() {
        Deck d = new Deck("A deck.");
        try {
            decks.addDeck(d);
            assertTrue(decks.size() == 1);
            decks.removeDeck(0);
            fail("gone too far");
        } catch (RuntimeException ex) {
            //good
        }
        assertTrue(decks.size() == 1);

    }

    @Test
    public void removeDeckNoExist(){
        quickDeckAdder();
        try {
            decks.removeDeck(4); // there is no index number 4, so should throw
        } catch (IndexOutOfBoundsException ex) {
            //good
        }
        assertEquals(decks.size(), 3);
        assertEquals(decks.viewDeckTitles(), "0. History\n1. ABC's\n2. Animal Species\n");
    }

    @Test
    public void removeDeckNoDecks(){
        try {
            decks.removeDeck(1); // no decks were added beforehand
        } catch (IndexOutOfBoundsException ex) {
            //good
        }
        assertEquals(decks.size(), 0);
    }

    @Test
    public void viewDeckTitles() {
        quickDeckAdder();
        assertEquals(decks.viewDeckTitles(), "0. History\n1. ABC's\n2. Animal Species\n");
    }

    @Test
    public void viewDeckTitlesNone() {
        assertEquals(decks.viewDeckTitles(), "There are no decks.");
    }

    @Test
    public void testGetActiveDeck() {
        quickDeckAdder();
        assertEquals(d3, decks.getActiveDeck());
    }

    @Test
    public void testGetActiveDeckNone() {
        try {
            decks.getActiveDeck();
            fail("should have thrown");
        } catch (RuntimeException ex) {
            //good
        }
    }

    @Test
    public void testSetActiveDeck() {
        quickDeckAdder();
        assertEquals(d3, decks.getActiveDeck()); // verify active deck
        decks.setActiveDeck(d2); // switch active deck
        assertEquals(d2, decks.getActiveDeck()); // verify deck has been switched
    }

    @Test
    public void retrieveDeckFromIndexTest() {
        quickDeckAdder();
        assertEquals(d1, decks.retrieveDeckWithIndex(0));
        assertEquals(d2, decks.retrieveDeckWithIndex(1));
        assertEquals(d3, decks.retrieveDeckWithIndex(2));
    }

    public void quickDeckAdder() {
        d1 = new Deck("History");
        d2 = new Deck("ABC's");
        d3 = new Deck("Animal Species");
        decks.addDeck(d1);
        decks.addDeck(d2);
        decks.addDeck(d3);
    }
}
