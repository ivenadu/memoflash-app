package ui.gui;

import javax.swing.*;
import javax.swing.event.*;

import model.Deck;
import model.DeckCollection;
import model.Flashcard;
import persistence.Load;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the app GUI that lets user interact with active deck's card creation and deletion.
 */

//SOURCES REFERRED TO: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html (ListDemo.java)

public class AppGUI extends JPanel implements ListSelectionListener {

    private ArrayList<String> cardsInfo = new ArrayList<>();
    private JList<String> flashcardJList;
    private DefaultListModel<String> listModel;

    private DeckCollection deckCollection;

    {
        try {
            deckCollection = Load.loadFile("./data/myFile.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Deck activeDeck = deckCollection.getActiveDeck();

    Buttons buttons;

    private JTextField nameField;
    private JTextField questionField;
    private JTextField answerField;

    private JScrollPane scrollPane;

    private JLabel nameLabel = new JLabel(NAME_LABEL);
    private JLabel questionLabel = new JLabel(QUESTION_LABEL);
    private JLabel answerLabel = new JLabel(ANSWER_LABEL);

    private SaveListener saveListener;
    private AddCardListener addCardListener;
    private RemoveListener removeListener;

    private static final String NAME_LABEL = "Name: ";
    private static final String QUESTION_LABEL = "Question: ";
    private static final String ANSWER_LABEL = "Answer: ";

    private static final int FIXED_WIDTH = 10;
    private static final int FIXED_HEIGHT = 0;

    //EFFECTS: constructs GUI of active Deck with text fields and buttons for add and removal
    public AppGUI() {

        super(new BorderLayout());
        makeFields();
        makeList();
        scrollPane = new JScrollPane(flashcardJList);
        buttons = new Buttons(nameField, questionField, answerField, flashcardJList,
                listModel, deckCollection);
        makePanel();
        actionFields();
    }

    public void makeFields() {

        nameField = new JTextField(5);
        questionField = new JTextField(15);
        answerField = new JTextField(5);
    }

    // MODIFIES: this
    // EFFECTS: make a JList that takes in a DefaultListModel representing the flashcards in the Deck in string form
    public void makeList() {

        listModel = new DefaultListModel<>();
        addToListModel(convertToStringArray(activeDeck.getCardList()));
        flashcardJList = new JList<>(listModel);
        flashcardJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flashcardJList.setSelectedIndex(0);
        flashcardJList.addListSelectionListener(this);
        flashcardJList.setVisibleRowCount(20);

    }

    // MODIFIES: this
    // EFFECTS: adds the combined result of strings to the DefaultListModel
    private void addToListModel(ArrayList<String> strings) {
        for (String s : strings) {
            listModel.addElement(s);
        }
    }

    // EFFECTS: converts ArrayList<Flashcard> to ArrayList<String> and returns the result
    private ArrayList<String> convertToStringArray(ArrayList<Flashcard> cards) {
        for (Flashcard f : cards) {
            String combo = combineString(f.getName(), f.getQuestion(), f.getAnswer());
            cardsInfo.add(combo);
        }
        return cardsInfo;
    }

    // EFFECTS: combines the Flashcard's fields into one string and returns the string
    public String combineString(String name, String question, String answer) {
        return "NAME: " + name + "   QUESTION: " + question + "   ANSWER: " + answer;
    }

    // MODIFIES: this
    // EFFECTS: creates the panel for the GUI display

    public void makePanel() {

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
        optionsPanel.add(buttons.getSaveButton());
        optionsPanel.add(Box.createHorizontalStrut(5));
        add(Box.createHorizontalStrut(10));
        optionsPanel.add(Box.createRigidArea(new Dimension(FIXED_WIDTH, FIXED_HEIGHT)));
        optionsPanel.add(nameLabel);
        optionsPanel.add(nameField);
        optionsPanel.add(Box.createRigidArea(new Dimension(FIXED_WIDTH, FIXED_HEIGHT)));
        optionsPanel.add(questionLabel);
        optionsPanel.add(questionField);
        optionsPanel.add(Box.createRigidArea(new Dimension(FIXED_WIDTH, FIXED_HEIGHT)));
        optionsPanel.add(answerLabel);
        optionsPanel.add(answerField);
        optionsPanel.add(Box.createRigidArea(new Dimension(FIXED_WIDTH, FIXED_HEIGHT)));
        optionsPanel.add(buttons.getAddCardButton());
        optionsPanel.add(Box.createRigidArea(new Dimension(FIXED_WIDTH, FIXED_HEIGHT)));
        optionsPanel.add(buttons.getRemoveButton());
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        add(scrollPane, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.PAGE_END);
    }

    public void actionFields() {

        saveListener = new SaveListener(this.deckCollection);
        buttons.getSaveButton().addActionListener(saveListener);

        addCardListener = new AddCardListener(this.buttons.getAddCardButton(), this.listModel,
                this.deckCollection.getActiveDeck(), nameField, questionField, answerField);
        this.buttons.getAddCardButton().addActionListener(addCardListener);

        removeListener = new RemoveListener(this.buttons.getRemoveButton(), this.flashcardJList, this.listModel,
                this.deckCollection.getActiveDeck());
        this.buttons.getRemoveButton().addActionListener(removeListener);

        nameField.addActionListener(addCardListener);
        nameField.getDocument().addDocumentListener(addCardListener);
        questionField.addActionListener(addCardListener);
        questionField.getDocument().addDocumentListener(addCardListener);
        answerField.addActionListener(addCardListener);
        answerField.getDocument().addDocumentListener(addCardListener);
    }


    // MODIFIES: this and removeButton
    // EFFECTS: sets the removeButton to true if the selected index is valid; otherwise disables the button
    @Override
    public void valueChanged(ListSelectionEvent e) {
        emptyCase();
        if (!e.getValueIsAdjusting()) {
            buttons.getRemoveButton().setEnabled(flashcardJList.getSelectedIndex() != -1);
        }
    }

    // MODIFIES: this and removeButton
    // EFFECTS: if the deck is empty initially, disables the removeButton
    public void emptyCase() {
        if (activeDeck.getCardList().isEmpty()) {
            buttons.getRemoveButton().setEnabled(false);
        }
    }
}

