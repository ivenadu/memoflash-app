package persistence;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Deck;
import model.DeckCollection;
import ui.MemoFlashApp;

import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.nio.file.Paths;

//Sources referred to: http://tutorials.jenkov.com/java-json/jackson-installation.html

/**
 * Represents a class that is able to write instances to JSON file
 */
public abstract class Write {

    public String mapObject(HashMap<Integer, Deck> hm) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString;

        try {
            mapper.writeValue(Paths.get("./data/myFile.txt").toFile(), hm);
            jsonString = mapper.writeValueAsString(hm);
            return jsonString;

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
