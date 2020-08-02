package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.DeckCollection;

import java.io.File;
import java.io.IOException;

public class Load {
    public static DeckCollection loadFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DeckCollection dc = mapper.readValue(new File(path), DeckCollection.class);
        return dc;
    }
}
