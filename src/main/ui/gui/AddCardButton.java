package ui.gui;

import model.Deck;
import model.DeckCollection;
import persistence.Load;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a button that is intended to be clicked when adding a card
 */
public class AddCardButton {

    private static final String addString = "ADD CARD";
    private JButton addCardButton;

    // EFFECTS: creates a button for card adding that is initially set to false
    public AddCardButton(DefaultListModel<String> listModel, Deck active, JTextField nameF, JTextField questionF,
                         JTextField answerF) {
        addCardButton = new JButton(addString);
        addCardButton.addActionListener(new AddCardListener(this.getAddCardButton(), listModel, active, nameF,
                questionF, answerF));
        addCardButton.setActionCommand(addString);
        addCardButton.setEnabled(false);
    }

    // EFFECTS: returns the button
    public JButton getAddCardButton() {
        return addCardButton;
    }
}

