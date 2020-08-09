package ui.gui;

import model.Flashcard;
import ui.gui.AppGUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddCardListener extends AppGUI implements ActionListener, DocumentListener {
    JButton button;
    JTextField fieldName;
    JTextField fieldQuestion;
    JTextField fieldAnswer;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkEmpty(this.getNameField()) || checkEmpty(this.getQuestionField())
                || checkEmpty(this.getAnswerField())) {
            return;
        } else {
            this.getActiveDeck().addCard(new Flashcard(this.getNameField().toString(),
                    this.getQuestionField().toString(), this.getAnswerField().toString()));
            this.getFlashcardListModel().addElement(this.combineString(this.getNameField().toString(),
                    this.getQuestionField().toString(), this.getAnswerField().toString()));
        }
    }

    private boolean checkEmpty(JTextField field) {
        return field.toString().trim().isEmpty();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
