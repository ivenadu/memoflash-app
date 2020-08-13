package ui.gui;

import model.DeckCollection;

import javax.swing.*;

public class Buttons {

    private JList<String> flashcardJList;
    private DefaultListModel<String> flashcardListModel;

    private DeckCollection deckCollection;

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


    public Buttons(JTextField nameField, JTextField questionField, JTextField answerField,
                   JList<String> flashcardJList, DefaultListModel<String> flashcardListModel,
                   DeckCollection deckCollection) {
        this.nameField = nameField;
        this.questionField = questionField;
        this.answerField = answerField;
        this.flashcardJList = flashcardJList;
        this.flashcardListModel = flashcardListModel;
        this.deckCollection = deckCollection;
        makeSaveButton();
        makeAddCardButton();
        makeRemoveButtonMaker();
        actionFields();
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getAddCardButton() {
        return addCardButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    // MODIFIES: this and saveButton
    // EFFECTS: creates button that allows user to save current deck information
    public void makeSaveButton() {

        saveButton = new JButton(saveString);
        saveListener = new SaveListener(this.deckCollection);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(true);
    }

    // MODIFIES: this and addCardButton
    // EFFECTS: creates button that allows user to add a card to deck
    public void makeAddCardButton() {

        addCardButton = new JButton(addString);
        addCardListener = new AddCardListener(addCardButton, flashcardListModel, deckCollection.getActiveDeck(),
                nameField, questionField, answerField);
        addCardButton.setActionCommand(addString);
        addCardButton.addActionListener(addCardListener);
        addCardButton.setEnabled(false);
    }

    // MODIFIES: this and removeButton
    // EFFECTS: creates button that allows user to remove a card from deck
    public void makeRemoveButtonMaker() {

        removeButton = new JButton(removeString);
        removeListener = new RemoveListener(removeButton, this.flashcardJList, this.flashcardListModel,
                this.deckCollection.getActiveDeck());
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeListener);
    }

    public void actionFields() {

        nameField.addActionListener(addCardListener);
        nameField.getDocument().addDocumentListener(addCardListener);
        questionField.addActionListener(addCardListener);
        questionField.getDocument().addDocumentListener(addCardListener);
        answerField.addActionListener(addCardListener);
        answerField.getDocument().addDocumentListener(addCardListener);
    }

}
