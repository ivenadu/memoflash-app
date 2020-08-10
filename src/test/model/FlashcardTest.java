package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        assertEquals("Question: Question\tAnswer: Answer", card.getQA());
    }

    @Test
    public void constructFlashcard() {
        Flashcard card = new Flashcard();

    }

    @Test
    public void setQATest() {
        card.setQA("edited Q", "edited A");
        assertEquals("edited Q", card.getQuestion());
        assertEquals("edited A", card.getAnswer());
    }

    @Test
    public void setCardName() {
        card.setName("New Name");
        assertEquals("New Name", card.getName());
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
    public void testEqualsQ() {
        Flashcard f = new Flashcard(card.getName(), "Wrong", card.getAnswer());
        assertFalse(equals(f));
        assertFalse(card.getQuestion().equals(f.getQuestion()));
        Flashcard g = new Flashcard("ABC", card.getQuestion(), "Not the answer");
        assertEquals(card.getQuestion(), g.getQuestion());
    }

    @Test
    public void testEqualsAllFalse() {
        Flashcard f = new Flashcard("A", "B", "C");
        assertFalse(card.equals(f));
    }

    @Test
    public void testEqualsAllMatch() {
        Flashcard f = new Flashcard(card.getName(), card.getQuestion(), card.getAnswer());
        assertTrue(card.equals(f));
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