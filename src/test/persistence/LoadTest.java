package persistence;

import model.Deck;
import model.DeckCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadTest {

    @Test
    void test_load() throws IOException {
        DeckCollection dc = Load.loadFile("./data/myTest0.txt");
        assertEquals(dc.size(), 2);
        Deck dk0 = dc.deckCollection.get(0);
        assertEquals(dk0.size(), 0);
        Deck dk1 = dc.deckCollection.get(1);
        assertEquals(dk1.size(), 2);
    }
}
