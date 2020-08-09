package ui.gui;

import model.DeckCollection;
import persistence.Load;
import persistence.Write;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveListener implements ActionListener {

    private DeckCollection deckCollection;

    public SaveListener(DeckCollection deckCollection) {
        this.deckCollection = deckCollection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Write.save(deckCollection, "./data/myFile.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
