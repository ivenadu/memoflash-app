package ui.gui;

import model.Deck;

import javax.swing.*;

/**
 * Represents a button intended to be used for card removal upon click
 */

public class RemoveCardButton {

    private static final String removeString = "REMOVE SELECTED CARD";
    private JButton removeCardButton;

    // EFFECTS: makes the button for card removal
    public RemoveCardButton(JList<String> flashcardJList, DefaultListModel<String> listModel, Deck activeDeck) {

        removeCardButton = new JButton(removeString);
        removeCardButton.addActionListener(new RemoveCardListener(this.getRemoveCardButton(), flashcardJList, listModel,
                activeDeck));
        removeCardButton.setActionCommand(removeString);
    }

    // EFFECTS: returns the button
    public JButton getRemoveCardButton() {
        return removeCardButton;
    }
}

