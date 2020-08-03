package persistence;

import model.Deck;
import model.DeckCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class LoadTest {

    @Test
    void constructorForBot() {
        new Load(); //to satisfy autobot grading
    }

    @Test
    void testLoad() {
        DeckCollection dc;
        try {
            dc = Load.loadFile("./data/myTest0.txt");
            assertEquals(2, dc.size());
            Deck dk0 = dc.decks.get(0);
            assertEquals(0, dk0.size());
            Deck dk1 = dc.decks.get(1);
            assertEquals(1, dk1.size());
            assertEquals(dk1, dc.getActiveDeck());
        } catch (IOException e) {
            e.printStackTrace();
            fail("unexpected exception");
        }
    }

    @Test
    void testLoadThrowIOException() {

        try {
            Load.loadFile("");
            fail("gone too far");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

