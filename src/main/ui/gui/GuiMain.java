package ui.gui;

import javax.swing.*;
import java.io.IOException;

import model.Deck;
import model.DeckCollection;
import persistence.Load;

import static javax.swing.SwingUtilities.invokeLater;

public class GuiMain {
    private static String deckName;

    static {
        try {
            deckName = Load.loadFile("./data/myFile.json").getActiveDeck().getTitle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        invokeLater(new Runnable() {
            @Override
            public void run() {
                makeGUI();
            }
        });

    }

    public static void makeGUI() {
        JFrame frame = new JFrame("Displaying your active deck: " + deckName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent contentPane = new AppGUI();
        contentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(contentPane);

        frame.pack();
        frame.setVisible(true);

    }

}
