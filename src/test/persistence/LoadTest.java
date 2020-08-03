package persistence;

import model.Deck;
import model.DeckCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class LoadTest {

    @Test
    void constructorForBot() {
        new Load();
    }

    @Test
    void testLoad() {
        DeckCollection dc;
        try {
            dc = Load.loadFile("./data/myTest0.txt");
            assertEquals(2, dc.size());
            Deck dk0 = dc.deckCollection.get(0);
            assertEquals(0, dk0.size());
            Deck dk1 = dc.deckCollection.get(1);
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

