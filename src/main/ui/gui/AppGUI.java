package ui.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Deck;
import model.DeckCollection;
import model.Flashcard;
import persistence.Load;

import java.awt.*;
import java.io.IOException;

import static persistence.Load.*;

/**
 * Represents the app GUI that lets user interact with active deck's card creation and deletion.
 */
//Sources cited: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html (ListDemo.java)

public class AppGUI extends JPanel implements ListSelectionListener {
    private JList<Flashcard> flashcardJList;
    private DefaultListModel flashcardListModel;

    private DeckCollection deckCollection = Load.loadFile("./data/myFile.json");

    private static final String remove = "Remove";
    private static final String add = "Add";
    private JButton removeButton;
    private JTextField nameField;
    private JTextArea questionField;
    private JTextField answerField;

    public static void main(String[] args) {
        try {
            AppGUI a = new AppGUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public AppGUI() throws IOException {
        super(new BorderLayout());

        flashcardListModel = new DefaultListModel();
        flashcardJList = new JList((loadFile("./data/myFile.json").decks.toArray()));
        System.out.println(flashcardJList);
        runGUI();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public void runGUI() throws IOException {

        JFrame frame = new JFrame("Displaying your active deck: " + deckCollection.getActiveDeck().getTitle());

    }
}
