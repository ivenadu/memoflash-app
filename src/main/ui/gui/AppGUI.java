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
    private Deck activeDeck = deckCollection.getActiveDeck();

    private static final String removeString = "REMOVE CARD";
    private static final String addString = "ADD CARD";
    private static final String saveString = "SAVE";

    private JButton saveButton;
    private JButton addCardButton;
    private JButton removeButton;

    private JTextField nameField;
    private JTextArea questionField;
    private JTextField answerField;

    SaveListener saveListener;
    AddCardListener addCardListener;
    RemoveListener removeListener;

    JScrollPane scrollPane;

    public static void main(String[] args) {
        try {
            AppGUI a = new AppGUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public AppGUI() throws IOException {
        super(new BorderLayout());
        makeList();
        makeSaveButton();
        makeAddCardButton();
        makeRemoveButtonMaker();
        makeFields();
        makePanel();
        new JFrame("Displaying your active deck: " + deckCollection.getActiveDeck().getTitle());
    }

    public void makeList() {

        flashcardListModel = new DefaultListModel();
        flashcardJList = new JList(activeDeck.getCardList().toArray());

        flashcardJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flashcardJList.setSelectedIndex(0);
        flashcardJList.addListSelectionListener(this);
        flashcardJList.setVisibleRowCount(20);
        scrollPane = new JScrollPane(flashcardJList);

    }

    public void makeSaveButton() {

        JButton saveButton = new JButton(saveString);
        saveListener = new SaveListener(saveButton);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(false);
    }

    public void makeAddCardButton() {

        addCardButton = new JButton(addString);
        addCardListener = new AddCardListener();
        addCardButton.setActionCommand(addString);
        addCardButton.addActionListener(addCardListener);
        addCardButton.setEnabled(false);

    }

    public void makeRemoveButtonMaker() {

        removeButton = new JButton(removeString);
        removeListener = new RemoveListener(removeButton);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeListener);
        removeButton.setEnabled(false);

    }

    public void makeFields() {

        nameField = new JTextField(5);
        nameField.addActionListener(addCardListener);
        nameField.getDocument().addDocumentListener(addCardListener);

        questionField = new JTextArea(3, 15);

        answerField = new JTextField(5);
    }

    public void makePanel() {

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.LINE_AXIS));

        optionsPanel.add(saveButton);
        optionsPanel.add(Box.createHorizontalStrut(5));
        optionsPanel.add(new JSeparator(SwingConstants.VERTICAL));
        add(Box.createHorizontalStrut(5));
        optionsPanel.add(addCardButton);
        optionsPanel.add(removeButton);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(scrollPane, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (flashcardJList.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                flashcardJList.setEnabled(true);
            }
        }
    }

}
