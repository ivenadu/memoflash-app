package persistence;

import model.Deck;
import model.DeckCollection;
import model.Flashcard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriteTest {
    private DeckCollection deckCollection;
    HashMap<Integer, Deck> deckHashMap;
    String path;

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
//        path = "./data/testFile.txt";
//        deckCollection.setPath(path);
        this.deckCollection = deckCollection;
    }

    @Test
    void testMapDeckCollection() throws IOException {
        deckCollection.save(this.deckCollection, "./data/myTest.txt");
    }

//    @Test
//    void testMapDeckCollectionException() {
//        this.deckCollection.setPath("");
//        try {
//            deckCollection.save(deckHashMap);
//            fail("gone too far");
//        }catch (IOException ex) {
//            //good
//        }
//    }


}
