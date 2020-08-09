package ui.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RemoveListener extends AppGUI implements ActionListener {
    JButton button = this.getRemoveButton();

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = this.getFlashcardJList().getSelectedIndex();
        this.getActiveDeck().removeCardWithIndex(index);
        if (this.getFlashcardListModel().isEmpty()) {
            button.setEnabled(false);
        } else {
            if (index == getFlashcardListModel().size()) {
                index--;
            } else {
                this.getFlashcardListModel().remove(index);
            }
        }
        this.getFlashcardJList().setSelectedIndex(index);
        this.getFlashcardJList().ensureIndexIsVisible(index);
    }

}

