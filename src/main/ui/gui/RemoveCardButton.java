package ui.gui;

import javax.swing.*;

/**
 * Represents a button intended to be used for card removal upon click
 */

public class RemoveCardButton {

    private static final String removeString = "REMOVE SELECTED CARD";
    private JButton removeCardButton;

    // EFFECTS: makes the button for card removal
    public RemoveCardButton() {

        removeCardButton = new JButton(removeString);
        removeCardButton.setActionCommand(removeString);
    }

    // EFFECTS: returns the button
    public JButton getRemoveCardButton() {
        return removeCardButton;
    }
}

