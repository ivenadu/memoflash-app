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
        invokeLater(() -> makeGUI());
    }

    // MODIFIES: this
    // EFFECTS: creates and displays GUI
    public static void makeGUI() {
        JFrame frame = new JFrame("Displaying your active deck: " + deckName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent contentPane = new AppGUI();
        contentPane.setOpaque(true);
        frame.setContentPane(contentPane);

        frame.pack();
        frame.setVisible(true);
    }
}
