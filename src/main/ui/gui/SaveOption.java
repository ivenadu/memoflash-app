package ui.gui;

import model.DeckCollection;
import persistence.Load;
import persistence.Write;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveOption implements ActionListener {

    public DeckCollection deckCollection;

    {
        try {
            deckCollection = Load.loadFile("./data/myFile.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
