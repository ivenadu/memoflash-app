package ui.gui;

import model.Deck;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a Listener that removes cards from the Deck
 */

public class RemoveListener implements ActionListener {
    JButton button;
    private JList<String> flashList;
    private DefaultListModel<String> flashcardListModel;
    private Deck activeDeck;

    // EFFECTS: creates a RemoveListener and sets its fields; disables button for removal if deck is empty
    public RemoveListener(JButton button, JList<String> flashList, DefaultListModel<String> flashcardListModel,
                          Deck activeDeck) {
        this.flashList = flashList;
        this.flashcardListModel = flashcardListModel;
        this.activeDeck = activeDeck;
        this.button = button;
        emptyCase();
    }

    // EFFECTS: Removes card at selected index. If there are no more cards in deck, disables removal button.
    @Override
    public void actionPerformed(ActionEvent e) {

        int index = flashList.getSelectedIndex();
        SoundPlayer sound = new SoundPlayer();
        sound.playSound("./data/remove.wav");
        flashcardListModel.remove(index);
        activeDeck.removeCardWithIndex(index);
        if (!flashcardListModel.isEmpty()) {
            if (index == flashcardListModel.size()) {
                index--;
            } else {
                if (index == -1) {
                    index++;
                }
            }
            flashList.setSelectedIndex(index);
            flashList.ensureIndexIsVisible(index);
        } else {
            button.setEnabled(false);
        }
    }

    // MODIFIES: button
    // EFFECTS: sets removal button to false if the deck is empty
    public void emptyCase() {
        if (activeDeck.getCardList().isEmpty()) {
            button.setEnabled(false);
        }
    }
}

