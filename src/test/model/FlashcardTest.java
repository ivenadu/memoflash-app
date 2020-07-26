package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {

    private Flashcard card;

    @BeforeEach
    public void preTest(){
        card = new Flashcard("Name","Question", "Answer");
    }

    @Test
    public void getQATest(){
        assertEquals(card.getQA(),"Question: Question\tAnswer: Answer");
    }

    @Test
    public void setQATest(){
        card.setQA("edited Q", "edited A");
        assertEquals(card.getQuestion(),"edited Q");
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
    public void wrongAnswer(){
        assertFalse(card.correctAnswer("Answe r"));
    }


}