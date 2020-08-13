package ui.gui;

import javax.swing.*;

public class RemoveCardButton {

    private static final String removeString = "REMOVE SELECTED CARD";
    private JButton removeCardButton;

    public RemoveCardButton() {

        removeCardButton = new JButton(removeString);
        removeCardButton.setActionCommand(removeString);
    }

    public JButton getRemoveCardButton() {
        return removeCardButton;
    }
}

