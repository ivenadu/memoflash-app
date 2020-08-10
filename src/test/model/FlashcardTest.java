package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {

    private Flashcard card;

    @BeforeEach
    public void preTest() {
        card = new Flashcard("Name", "Question", "Answer");
    }

    @Test
    public void getQATest() {
        assertEquals(card.getQA(), "Question: Question\tAnswer: Answer");
    }

    @Test //TODO: how to test?
    public void constructFlashcard() {
        Flashcard card = new Flashcard();

    }

    @Test
    public void setQATest() {
        card.setQA("edited Q", "edited A");
        assertEquals(card.getQuestion(), "edited Q");
        assertEquals(card.getAnswer(), "edited A");
    }

    @Test
    public void setCardName() {
        card.setName("New Name");
        assertEquals(card.getName(), "New Name");
    }

    @Test
    public void correctAnswer() {
        assertTrue(card.correctAnswer("Answer"));
    }

    @Test
    public void correctAnswerCaseInsensitive() {
        assertTrue(card.correctAnswer("aNsWeR"));
    }

    @Test
    public void wrongAnswer() {
        assertFalse(card.correctAnswer("Answe r"));
    }

    @Test
    public void testEquals() {
        assertFalse(equals("ABC"));
    }

    @Test
    public void testEqualsQ() {
        Flashcard f = new Flashcard(card.getName(), "Wrong", card.getAnswer());
        assertFalse(equals(f));
        assertFalse(card.getQuestion().equals(f.getQuestion()));
    }

    @Test
    public void testInstance() {
        assertFalse(equals(null));
        assertFalse((null instanceof Flashcard));
    }

    @Test
    public void testHashCode() {
        Flashcard f = new Flashcard(card.getName(), card.getQuestion(), card.getAnswer());
        assertEquals(card.hashCode(), f.hashCode());
    }

    @Test
    public void testHashCodeFalse() {
        Flashcard f = new Flashcard("a", "b", "c");
        assertFalse(card.hashCode() == f.hashCode());
    }
}