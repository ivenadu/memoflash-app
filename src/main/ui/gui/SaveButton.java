package ui.gui;

import javax.swing.*;

public class SaveButton {

    private static final String saveString = "SAVE DECK";
    private JButton saveButton;

    // EFFECTS: creates button that allows user to save current deck information
    public SaveButton() {
        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.setEnabled(true);
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}

