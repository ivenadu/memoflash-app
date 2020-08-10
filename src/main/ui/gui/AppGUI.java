package ui.gui;

import javax.swing.*;
import javax.swing.event.*;

import model.Deck;
import model.DeckCollection;
import model.Flashcard;
import persistence.Load;
import persistence.Write;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the app GUI that lets user interact with active deck's card creation and deletion.
 */
//Sources cited: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html (ListDemo.java)

public class AppGUI extends JPanel implements ListSelectionListener {

    // Object[][] info;
    private ArrayList<String> cardsInfo = new ArrayList<>();
    private JList<Flashcard> flashcardJList;
    private DefaultListModel flashcardListModel;

    private DeckCollection deckCollection;

    {
        try {
            deckCollection = Load.loadFile("./data/myFile.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  final String[] columns = {"Name", "Question", "Answer"};

    private Deck activeDeck = deckCollection.getActiveDeck();

    //TODO: organize into their respective places

    private static final String removeString = "REMOVE SELECTED CARD";
    private static final String addString = "ADD CARD";
    private static final String saveString = "SAVE DECK";

    private JButton saveButton;
    private JButton addCardButton;
    private JButton removeButton;

    private JTextField nameField;
    private JTextField questionField;
    private JTextField answerField;

    private SaveListener saveListener;
    private AddCardListener addCardListener;
    private RemoveListener removeListener;

    private JScrollPane scrollPane;

    private JLabel nameLabel;
    private JLabel questionLabel;
    private JLabel answerLabel;

    private static final String NAME_LABEL = "Name: ";
    private static final String QUESTION_LABEL = "Question: ";
    private static final String ANSWER_LABEL = "Answer: ";

    private static final int FIXED_WIDTH = 10;
    private static final int FIXED_HEIGHT = 0;

    public AppGUI() {

        super(new BorderLayout());
        nameField = new JTextField(5);
        questionField = new JTextField(15);
        answerField = new JTextField(5);
        makeList();
        makeSaveButton();
        makeAddCardButton();
        makeRemoveButtonMaker();
        makeFields();
        makePanel();

    }

    public void makeList() {

        flashcardListModel = new DefaultListModel();
        addToListModel(convertToStringArray(activeDeck.getCardList()));
        flashcardJList = new JList(flashcardListModel);
        flashcardJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flashcardJList.setSelectedIndex(0);
        flashcardJList.addListSelectionListener(this);
        flashcardJList.setVisibleRowCount(20);
        scrollPane = new JScrollPane(flashcardJList);
    }

    public void addToListModel(ArrayList<String> strings) {
        for (String s : strings) {
            flashcardListModel.addElement(s);
        }
    }

    private ArrayList<String> convertToStringArray(ArrayList<Flashcard> cards) {
        for (Flashcard f : cards) {
            String combo = combineString(f.getName(), f.getQuestion(), f.getAnswer());
            cardsInfo.add(combo);
        }
        return cardsInfo;
    }

    public String combineString(String name, String question, String answer) {
        String combined = "NAME: " + name + "   QUESTION: " + question + "   ANSWER: " + answer;
        return combined;
    }

    //TODO: belong in the Save class

    public void makeSaveButton() {

        saveButton = new JButton(saveString);
        saveListener = new SaveListener(this.deckCollection);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(true);
    }

    public void makeAddCardButton() {

        addCardButton = new JButton(addString);
        addCardListener = new AddCardListener(addCardButton, flashcardListModel, activeDeck, nameField, questionField,
                answerField);
        addCardButton.setActionCommand(addString);
        addCardButton.addActionListener(addCardListener);
        addCardButton.setEnabled(false);
    }

    public void makeRemoveButtonMaker() {

        removeButton = new JButton(removeString);
        removeListener = new RemoveListener(removeButton, this.flashcardJList, this.flashcardListModel,
                this.activeDeck);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeListener);
    }


    public void makeFields() {

        nameField.addActionListener(addCardListener);
        nameField.getDocument().addDocumentListener(addCardListener);
        questionField.addActionListener(addCardListener);
        questionField.getDocument().addDocumentListener(addCardListener);
        answerField.addActionListener(addCardListener);
        answerField.getDocument().addDocumentListener(addCardListener);

    }

    public void makePanel() {

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));

        optionsPanel.add(saveButton);
        optionsPanel.add(Box.createHorizontalStrut(5));
      //  optionsPanel.add(new JSeparator(SwingConstants.VERTICAL));
        add(Box.createHorizontalStrut(10));
        optionsPanel.add(Box.createRigidArea(new Dimension(FIXED_WIDTH, FIXED_HEIGHT)));
        nameLabel = new JLabel(NAME_LABEL);
        optionsPanel.add(nameLabel);
        optionsPanel.add(nameField);
        optionsPanel.add(Box.createRigidArea(new Dimension(FIXED_WIDTH, FIXED_HEIGHT)));
        questionLabel = new JLabel(QUESTION_LABEL);
        optionsPanel.add(questionLabel);
        optionsPanel.add(questionField);
        optionsPanel.add(Box.createRigidArea(new Dimension(FIXED_WIDTH, FIXED_HEIGHT)));
        answerLabel = new JLabel(ANSWER_LABEL);
        optionsPanel.add(answerLabel);
        optionsPanel.add(answerField);
        optionsPanel.add(Box.createRigidArea(new Dimension(FIXED_WIDTH, FIXED_HEIGHT)));
        optionsPanel.add(addCardButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(FIXED_WIDTH, FIXED_HEIGHT)));
        optionsPanel.add(removeButton);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        add(scrollPane, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        emptyCase();
        if (!e.getValueIsAdjusting()) {
            removeButton.setEnabled(flashcardJList.getSelectedIndex() != -1);
        }
    }

    public void emptyCase() {
        if (activeDeck.getCardList().isEmpty()) {
            removeButton.setEnabled(false);
        }
    }
}

