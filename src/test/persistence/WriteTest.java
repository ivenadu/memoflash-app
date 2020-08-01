package persistence;

import model.Deck;
import model.DeckCollection;
import model.Flashcard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriteTest {
    private File testFile;
    private DeckCollection deckCollection;
    HashMap<Integer, Deck> deck;

    @BeforeEach
    void preTest() {
        Flashcard testCard1 = new Flashcard("Name1", "Q1", "A1");
        Flashcard testCard2 = new Flashcard("Name2", "Q2", "A2");
        Flashcard testCard3 = new Flashcard("Name2", "Q2", "Q3");
        Deck testDeck1 = new Deck("testDeck1");
        Deck testDeck2 = new Deck("testDeck2");
        testDeck1.addCard(testCard1);
        testDeck2.addCard(testCard2);
        testDeck2.addCard(testCard3);
        DeckCollection deckCollection = new DeckCollection();
        deckCollection.addDeck(testDeck1);
        deckCollection.addDeck(testDeck2);
        File testFile = new File("./data/testFile.txt");
        this.testFile = testFile;
        this.deckCollection = deckCollection;
    }

    @Test
    void testMapObject() {
            this.deck = deckCollection.mapDecks();
            try {
                assertEquals("{\"0\":{\"title\":\"testDeck1\"},\"1\":{\"title\":\"testDeck2\"}}",
                        deckCollection.mapObject(deck));
            } catch (Exception ex) {
                fail("Unexpected exception");
            }

    }

    @Test
    void testIOException() {
        try {
            assertEquals("{\"0\":{\"title\":\"testDeck1\"},\"1\":{\"title\":\"testDeck2\"}}",
                    deckCollection.mapObject(deck));
            fail("Should have thrown exception");
        } catch (IOException ex) {
            //good!
        }

    }

}
