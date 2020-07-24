package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlashcardTest {

    private Flashcard card;

    @BeforeEach
    public void preTest(){
        card = new Flashcard("Question", "Answer");
    }

    @Test
    public void getQATest(){
        assertEquals(card.getQA(), "Question: Question\nAnswer: Answer");
    }

    @Test
    public void editCardTest(){
        card.editCard("editedQ", "editedA");
        assertEquals(card.getQuestion(),"editedQ");
        assertEquals(card.getAnswer(), "editedA");
    }

}