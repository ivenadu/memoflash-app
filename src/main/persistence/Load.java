package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.DeckCollection;

import java.io.File;
import java.io.IOException;

//Sources referred to:
// https://www.journaldev.com/875/java-read-file-to-string#java-read-file-to-string-using-files-class

public class Load {
    public static DeckCollection loadFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), DeckCollection.class);
    }
}
