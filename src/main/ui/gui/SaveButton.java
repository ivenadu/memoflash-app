package ui.gui;

import javax.swing.*;

/**
 * Represents a button intended to save deck information upon click
 */

public class SaveButton {

    private static final String saveString = "SAVE DECK";
    private JButton saveButton;

    // EFFECTS: creates the button for saving
    public SaveButton() {
        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.setEnabled(true);
    }

    // EFFECTS: returns the button
    public JButton getSaveButton() {
        return saveButton;
    }
}

