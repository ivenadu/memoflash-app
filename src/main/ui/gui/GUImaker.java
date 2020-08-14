package ui.gui;

import javax.swing.*;
import java.io.IOException;

import persistence.Load;

/**
 * Represents a class that makes a GUI for the MemoFlashApp
 */

public class GUImaker {

    private static String deckName;

    static {
        try {
            deckName = Load.loadFile("./data/myFile.json").getActiveDeck().getTitle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: makes a GUI
    public GUImaker() {

        JFrame frame = new JFrame("Displaying your active deck: " + deckName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent contentPane = new AppGUI();
        contentPane.setOpaque(true);
        frame.setContentPane(contentPane);

        frame.pack();
        frame.setVisible(true);
    }
}

