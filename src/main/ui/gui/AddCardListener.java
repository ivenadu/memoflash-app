package ui.gui;

import model.Deck;
import model.Flashcard;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCardListener implements ActionListener, DocumentListener {
    private boolean enabled = false;
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
        if (!(checkEmpty(nameField) || checkEmpty(questionField) || checkEmpty(answerField))) {
            Flashcard newCard = new Flashcard(nameField.getText().trim(),
                    questionField.getText().trim(), answerField.getText().trim());
            if (activeDeck.getCardList().contains(newCard)) {
                setUp(nameField);
                setUp(questionField);
                setUp(answerField);
            } else {
                activeDeck.addCard(newCard);
                flashcardListModel.addElement(combineString(nameField.getText().trim(),
                        questionField.getText().trim(), answerField.getText().trim()));

                setUp(nameField);
                setUp(questionField);
                setUp(answerField);
            }
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
        enabler();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        seeStatus(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        seeStatus(e);
    }

    public boolean seeStatus(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
       // if (!checkEmpty(nameField) && !checkEmpty(questionField)
         //       && !checkEmpty(answerField)) {
            enabled = true;
        } else {
            enabled = false;
        }
        return enabled;
    }

    public void enabler() {
        if (!enabled) {
            addCardButton.setEnabled(true);
        }
    }

    public boolean checkEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }
}
