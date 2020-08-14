package ui.gui;

import model.DeckCollection;
import persistence.Write;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Represents a Listener that saves the current information of the Deck and its Flashcards
 */

public class SaveListener implements ActionListener {

    private DeckCollection deckCollection;

    // EFFECTS: constructs a SaveListener
    public SaveListener(DeckCollection deckCollection) {
        this.deckCollection = deckCollection;
    }

    // EFFECTS: writes current app data to file
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Write.save(deckCollection, "./data/myFile.json");
            SoundPlayer sound = new SoundPlayer();
            sound.playSound("./data/inbox.wav");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
