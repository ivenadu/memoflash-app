package ui.gui;

import model.Flashcard;
import ui.gui.AppGUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddCardListener implements ActionListener, DocumentListener {
    AppGUI app;
    JButton button;
    JTextField fieldName;
    JTextField fieldQuestion;
    JTextField fieldAnswer;

    public AddCardListener(JButton button) throws IOException {
        app = new AppGUI();
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
