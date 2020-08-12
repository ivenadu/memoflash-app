package ui.gui;

import model.Deck;
import model.Flashcard;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

/**
 * Represents a Listener that can add cards to Deck
 */

public class AddCardListener implements ActionListener, DocumentListener {
    private boolean enabled = false;
    private JButton addCardButton;
    private DefaultListModel<String> flashcardListModel;
    private Deck activeDeck;
    private JTextField nameField;
    private JTextField questionField;
    private JTextField answerField;


    // EFFECTS: constructs AddCardListener
    public AddCardListener(JButton addCardButton, DefaultListModel<String> flashcardListModel, Deck activeDeck,
                           JTextField nameField, JTextField questionField, JTextField answerField) {
        this.addCardButton = addCardButton;
        this.flashcardListModel = flashcardListModel;
        this.activeDeck = activeDeck;
        this.nameField = nameField;
        this.questionField = questionField;
        this.answerField = answerField;
    }

    // MODIFIES: this
    // EFFECTS: creates a Flashcard from non empty text fields inputs; will only add it to deck if card is distinct
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(checkEmpty(nameField) || checkEmpty(questionField) || checkEmpty(answerField))) {
            Flashcard newCard = new Flashcard(nameField.getText().trim(),
                    questionField.getText().trim(), answerField.getText().trim());
            if (activeDeck.getCardList().contains(newCard)) {
                Toolkit.getDefaultToolkit().beep();
            } else {
                SoundPlayer sound = new SoundPlayer();
                sound.playSound("./data/light.wav");
                activeDeck.addCard(newCard);
                flashcardListModel.addElement(combineString(nameField.getText().trim(),
                        questionField.getText().trim(), answerField.getText().trim()));
            }
            setUp(nameField);
            setUp(questionField);
            setUp(answerField);
        }
    }

    // EFFECTS: returns result of combining the Flashcard's fields
    public String combineString(String name, String question, String answer) {
        return "NAME: " + name + "   QUESTION: " + question + "   ANSWER: " + answer;
    }

    // MODIFIES: field
    // EFFECTS: clears the text field and request its focus
    public void setUp(JTextField field) {
        field.setText("");
        field.requestFocusInWindow();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        seeStatus();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        seeStatus();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        seeStatus();
    }

    // MODIFIES: this and addCardButton
    // EFFECTS: enables button if text fields are non-empty; otherwise the button is disabled
    public void seeStatus() {
        addCardButton.setEnabled(!checkEmpty(nameField) && !checkEmpty(questionField) && !checkEmpty(answerField));
    }

    // EFFECTS: checks if the text field is empty or only filled with whitespace characters
    public boolean checkEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

}
