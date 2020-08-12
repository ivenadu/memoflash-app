package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private Deck testingDeck;

    @BeforeEach
    public void preTest() {
        testingDeck = new Deck("Random Facts I know");
    }

    @Test
    public void addCardTest() throws NonDistinctException, BlankStringException {
        quickAdd();
        assertEquals(testingDeck.size(), 2);
    }

    @Test
    public void addCardDupe() throws NonDistinctException, BlankStringException {
        quickAdd();
        try {
            testingDeck.addCard(new Flashcard("First", "FirstQ", "FirstA"));
            fail("they are duplicates!");
        } catch (BlankStringException e) {
            fail();
        } catch (NonDistinctException e) {
            //good
        }
    }

    @Test
    public void removeCardTest() throws NonDistinctException, BlankStringException {
        quickAdd();
        testingDeck.removeCardWithIndex(0);
        assertEquals(testingDeck.size(), 1);
    }

    @Test
    public void viewDeckCardsNone() {
        assertEquals(testingDeck.viewCards(), "There are no cards in the deck.");
    }

    @Test
    public void viewDeckCardsTest() throws NonDistinctException, BlankStringException {
        quickAdd();
        assertEquals(testingDeck.viewCards(), "Card Name: First\nQuestion: FirstQ\tAnswer: FirstA" +
                "\n\nCard Name: Second\nQuestion: SecondQ\tAnswer: SecondA\n\n");
    }

    @Test
    public void clearDeckTest() throws NonDistinctException, BlankStringException {
        quickAdd();
        testingDeck.clearDeck();
        assertEquals(testingDeck.size(), 0);
    }

    @Test
    public void sameTitleTrue() {
        Deck otherDeck = new Deck("Random Facts I know");
        assertTrue(testingDeck.sameTitle(otherDeck));
    }

    @Test
    public void differentTitleTest() {
        Deck otherDeck = new Deck("Random-er Facts");
        assertFalse(testingDeck.sameTitle(otherDeck));
    }

    @Test
    public void setDeckTitle() {
        testingDeck.setTitle("New Title");
        assertEquals(testingDeck.getTitle(), "New Title");
    }

    @Test
    public void getCardFromIndexTest() throws NonDistinctException, BlankStringException {
        quickAdd();
        assertEquals(testingDeck.getCardFromIndex(0), testingDeck.getCardList().get(0));
    }

    @Test
    public void equalsAll() {
        Deck compare = new Deck(testingDeck.getTitle());
        assertTrue(compare.getTitle().equals(testingDeck.getTitle()));
        assertTrue(compare.getCardList().equals(testingDeck.getCardList()));
        assertTrue(testingDeck.equals(compare));
        assertTrue(testingDeck.hashCode() == compare.hashCode());
    }

    @Test
    public void equalsCardList() {
        Deck compare = new Deck("X");
        assertTrue(compare.getCardList().equals(testingDeck.getCardList()));
        assertFalse(compare.getTitle().equals(testingDeck.getTitle()));
        assertFalse(testingDeck.equals(compare));
        assertFalse(testingDeck.hashCode() == compare.hashCode());
    }

    @Test
    public void equalsTitle() throws NonDistinctException, BlankStringException {
        quickAdd();
        Deck compare = new Deck(testingDeck.getTitle());
        assertTrue(compare.getTitle().equals(testingDeck.getTitle()));
        assertFalse(compare.getCardList().equals(testingDeck.getCardList()));
        assertFalse(testingDeck.equals(compare));
        assertFalse(testingDeck.hashCode() == compare.hashCode());
    }

    public void quickAdd() throws BlankStringException, NonDistinctException { //method to avoid dupe code; not supposed to throw any exceptions
            Flashcard fact1 = new Flashcard("First", "FirstQ", "FirstA");
            Flashcard fact2 = new Flashcard("Second", "SecondQ", "SecondA");
            testingDeck.addCard(fact1);
            testingDeck.addCard(fact2);
    }
}
