package ui.gui;

import javax.swing.*;

/**
 * Represents a button that is intended to be clicked when adding a card
 */
public class AddCardButton {

    private static final String addString = "ADD CARD";
    private JButton addCardButton;

    // EFFECTS: creates a button for card adding that is initially set to false
    public AddCardButton() {
        addCardButton = new JButton(addString);
        addCardButton.setActionCommand(addString);
        addCardButton.setEnabled(false);
    }

    // EFFECTS: returns the button
    public JButton getAddCardButton() {
        return addCardButton;
    }
}

