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
    void testLoad() {
        DeckCollection dc = null;
        try {
            dc = Load.loadFile("./data/myTest0.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(2, dc.size());
        Deck dk0 = dc.deckCollection.get(0);
        assertEquals(0, dk0.size());
        Deck dk1 = dc.deckCollection.get(1);
        assertEquals(2, dk1.size());
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

