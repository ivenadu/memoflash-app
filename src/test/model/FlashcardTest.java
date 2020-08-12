package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {

    private Flashcard card;

    @BeforeEach
    public void preTest() throws BlankStringException { //If BlankStringException is thrown, test fails
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
    public void testEqualsQ() throws BlankStringException {
        Flashcard f = new Flashcard(card.getName(), "Wrong", card.getAnswer());
        assertFalse(equals(f));
        assertFalse(card.getQuestion().equals(f.getQuestion()));
        Flashcard g = new Flashcard("ABC", card.getQuestion(), "Not the answer");
        assertEquals(card.getQuestion(), g.getQuestion());
    }


    @Test
    public void testEqualsAllFalse() throws BlankStringException {
        Flashcard f = new Flashcard("A", "B", "C");
        assertFalse(card.equals(f));
    }

    @Test
    public void testEqualsAllMatch() throws BlankStringException {
        Flashcard f = new Flashcard(card.getName(), card.getQuestion(), card.getAnswer());
        assertTrue(card.equals(f));
    }

    @Test
    public void testEqualsAnswerMatch() throws BlankStringException {
        Flashcard f = new Flashcard(card.getName(), "Q", "A");
        assertFalse(card.equals(f));
        assertEquals(card.getName(), f.getName());
    }

    @Test
    public void testHashCode() throws BlankStringException {
        Flashcard f = new Flashcard(card.getName(), card.getQuestion(), card.getAnswer());
        assertEquals(card.hashCode(), f.hashCode());
    }

    @Test
    public void testHashCodeFalse() throws BlankStringException {
        Flashcard f = new Flashcard("a", "b", "c");
        assertFalse(card.hashCode() == f.hashCode());
    }

    @Test
    public void testHashCodeDupe() throws BlankStringException {
        Flashcard f = new Flashcard("name", "question", "answer");
        assertFalse(card.hashCode() == f.hashCode());
    }

    @Test
    public void noThrow() throws BlankStringException { //will fail if thrown!
        card = new Flashcard("N", " Q ", "A   ");
    }

    @Test
    public void yesThrowName() {
        try {
            card = new Flashcard(" ", "Q", "A");
            fail();
        } catch (BlankStringException e) {
            //good
        }
    }

    @Test
    public void yesThrowQ() {
        try {
            card = new Flashcard("N", "    ", "A");
            fail();
        } catch (BlankStringException ex) {
            //good
        }
    }

    @Test
    public void yesThrowA() {
        try {
            card = new Flashcard("N", "Q", "");
            fail();
        } catch (BlankStringException ex) {
            //good
        }
    }
}