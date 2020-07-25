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
        assertEquals(card.getQA(),"Question: Question\nAnswer: Answer");
    }

    @Test
    public void setQATest(){
        card.setQA("edited Q", "edited A");
        assertEquals(card.getQuestion(),"edited Q");
        assertEquals(card.getAnswer(), "edited A");
    }

    @Test
    public void correctAnswer(){
        assertTrue(card.correctAnswer("Answer"));
    }

    @Test
    public void wrongAnswer(){
        assertFalse(card.correctAnswer("Not the answer."));
    }


}