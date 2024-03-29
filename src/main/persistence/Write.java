package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.DeckCollection;

import java.io.File;

import java.io.IOException;

//Sources referred to:
// http://tutorials.jenkov.com/java-json/jackson-installation.html


/**
 * A class with a method that can write to file
 */
public class Write {
    // EFFECTS: write instance to file; throws IOException
    public static void save(DeckCollection dc, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), dc);
    }
}
