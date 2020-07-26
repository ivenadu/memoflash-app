package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private Deck testingDeck;

    @BeforeEach
    public void preTest(){
        testingDeck = new Deck("Random Facts I know");
    }

    @Test
    public void addCardTest(){
        quickAdd();
        assertEquals(testingDeck.size(), 2);
    }

    @Test
    public void removeCardTest(){
        Flashcard fact1 = new Flashcard("First", "FirstQ", "FirstA");
        testingDeck.addCard(fact1);
        assertTrue(testingDeck.removeCard("First"));
        assertEquals(testingDeck.size(), 0);
    }

    @Test
    public void removeCardNoExist() {
        quickAdd();
        assertFalse(testingDeck.removeCard("You Can't Find This"));
        assertEquals(testingDeck.size(), 2);
    }

    @Test
    public void removeCardEmptyDeck() {
        assertFalse(testingDeck.removeCard("Hmm"));
    }

    @Test
    public void viewDeckCardsNone(){
        assertEquals(testingDeck.viewCards(), "There are no cards in the deck.");
    }

    @Test
    public void viewDeckCardsTest(){
        quickAdd();
        assertEquals(testingDeck.viewCards(), "Card Name: First\nQuestion: FirstQ\tAnswer: FirstA" +
                "\n\nCard Name: Second\nQuestion: SecondQ\tAnswer: SecondA\n\n" );

    }

    @Test
    public void clearDeckTest(){
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

    public void quickAdd() {
        Flashcard fact1 = new Flashcard("First", "FirstQ", "FirstA");
        Flashcard fact2 = new Flashcard("Second", "SecondQ", "SecondA");
        testingDeck.addCard(fact1);
        testingDeck.addCard(fact2);
    }




}
