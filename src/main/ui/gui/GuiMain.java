package ui.gui;

import javax.swing.*;
import java.io.IOException;

import persistence.Load;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * This class contains the main function for running the GUI of MemoFlashApp
 */

//SOURCES REFERRED TO: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html (ListDemo.java)

public class GuiMain {
    private static String deckName;

    static {
        try {
            deckName = Load.loadFile("./data/myFile.json").getActiveDeck().getTitle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: run program of GUI
    public static void main(String[] args) {
        invokeLater(() -> new GUImaker());
    }
}
