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
        testingDeck.removeCard("First");
        assertEquals(testingDeck.size(), 0);
    }

    @Test
    public void viewDeckCardsNone(){
        assertEquals(testingDeck.viewCards(), "There are no cards in the deck.");
    }

    @Test
    public void viewDeckCardsTest(){
        quickAdd();
        assertEquals(testingDeck.viewCards(), "Card Name: Second\nQuestion: SecondQ\nAnswer: SecondA" +
                "\n\nCard Name: First\nQuestion: FirstQ\nAnswer: FirstA\n\n" );

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


    public void quickAdd() {
        Flashcard fact1 = new Flashcard("First", "FirstQ", "FirstA");
        Flashcard fact2 = new Flashcard("Second", "SecondQ", "SecondA");
        testingDeck.addCard(fact1);
        testingDeck.addCard(fact2);

    }



}
