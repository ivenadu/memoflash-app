package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.DeckCollection;

import java.io.File;
import java.io.IOException;

//Sources referred to:
// https://www.journaldev.com/875/java-read-file-to-string#java-read-file-to-string-using-files-class

/**
 * A class with a method that loads the saved file
 */

public class Load {
    //EFFECTS: loads the saved file; throws IO exception
    public static DeckCollection loadFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), DeckCollection.class);
    }
}
