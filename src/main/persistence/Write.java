package persistence;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Deck;
import model.DeckCollection;
import model.Flashcard;
import ui.MemoFlashApp;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.nio.file.Paths;

//Sources referred to:
// http://tutorials.jenkov.com/java-json/jackson-installation.html


/**
 * Represents a class that is able to write instances to JSON file
 */
public abstract class Write {
    public void save(DeckCollection dc, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), dc);
    }
}
