package ui.gui;

import model.Deck;
import model.Flashcard;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddCardListener implements ActionListener, DocumentListener {
    private boolean enabled = false;
    private JButton addCardButton;
    private DefaultListModel<String> flashcardListModel;
    private Deck activeDeck;
    private JTextField nameField = new JTextField();
    private JTextField questionField = new JTextField();
    private JTextField answerField = new JTextField();

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
        try {
            if (!(checkEmpty(nameField) || checkEmpty(questionField) || checkEmpty(answerField))) {

                Flashcard newCard = new Flashcard(nameField.getText().trim(),
                        questionField.getText().trim(), answerField.getText().trim());
                if (activeDeck.getCardList().contains(newCard)) {
                    Toolkit.getDefaultToolkit().beep();
                    setUp(nameField);
                    setUp(questionField);
                    setUp(answerField);
                } else {
                    SoundPlayer sound = new SoundPlayer();
                    sound.playSound("./data/light.wav");
                    activeDeck.addCard(newCard);
                    flashcardListModel.addElement(combineString(nameField.getText().trim(),
                            questionField.getText().trim(), answerField.getText().trim()));
                    setUp(nameField);
                    setUp(questionField);
                    setUp(answerField);
                }
            }
        } catch (RuntimeException ex) {
            this.nameField.setText("");
            this.questionField.setText("");
            this.answerField.setText("");
        }
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

    public void seeStatus() {
        addCardButton.setEnabled(!checkEmpty(nameField) && !checkEmpty(questionField) && !checkEmpty(answerField));
    }

    public boolean checkEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }


}
