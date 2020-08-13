package ui.gui;

import javax.swing.*;

public class AddCardButton {

    private static final String addString = "ADD CARD";
    private JButton addCardButton;

    public AddCardButton() {
        addCardButton = new JButton(addString);
        addCardButton.setActionCommand(addString);
        addCardButton.setEnabled(false);
    }

    public JButton getAddCardButton() {
        return addCardButton;
    }
}

