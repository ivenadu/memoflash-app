package ui.gui;

import model.Deck;
import model.Flashcard;
import ui.gui.AppGUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddCardListener implements ActionListener, DocumentListener {

    private JButton addCardButton;
    private DefaultListModel<String> flashcardListModel;
    private Deck activeDeck;
    private JTextField nameField;
    private JTextField questionField;
    private JTextField answerField;

    public AddCardListener(JButton addCardButton, DefaultListModel<String> flashcardListModel, Deck activeDeck,
                           JTextField nameField, JTextField questionField, JTextField answerField) {
        this.addCardButton = addCardButton;
        this.flashcardListModel = flashcardListModel;
        this.activeDeck = activeDeck;
        this.nameField = nameField;
        this.questionField = questionField;
        this.answerField = answerField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!checkEmpty(nameField) && !checkEmpty(questionField)
                && !checkEmpty(answerField)) {
            addCardButton.setEnabled(true);

            Flashcard newCard = new Flashcard(convertFieldToString(nameField),
                    convertFieldToString(questionField), convertFieldToString(answerField));

            activeDeck.addCard(new Flashcard(nameField.toString().trim(),
                    questionField.toString().trim(), answerField.toString().trim()));
            flashcardListModel.addElement(combineString(convertFieldToString(nameField),
                    convertFieldToString(questionField), convertFieldToString(answerField)));

            setUp(nameField);
            setUp(questionField);
            setUp(answerField);

        } else {
            addCardButton.setEnabled(false);
        }
    }

    public String convertFieldToString(JTextField field) {
        return field.toString().trim();
    }

    public String combineString(String name, String question, String answer) {
        return "NAME: " + name + "   QUESTION: " + question + "   ANSWER: " + answer;
    }

    public void setUp(JTextField field) {
        field.setText("");
        field.requestFocusInWindow();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        beUpdated();

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        beUpdated();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        beUpdated();

    }

    public void beUpdated() {
        if (!checkEmpty(nameField) && !checkEmpty(questionField)
                && !checkEmpty(answerField)) {
            addCardButton.setEnabled(true);
        } else {
            addCardButton.setEnabled(false);
        }
    }

    public boolean checkEmpty(JTextField field) {
        return field.toString().trim().isEmpty();
    }
}
