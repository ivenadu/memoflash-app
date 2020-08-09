package ui.gui;

import model.Deck;
import model.Flashcard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveListener implements ActionListener {
    private JList<Flashcard> flashList;
    private DefaultListModel<String> flashcardListModel;
    private Deck activeDeck;

    public RemoveListener(JList<Flashcard> flashList, DefaultListModel<String> flashcardListModel, Deck activeDeck) {
        this.flashList = flashList;
        this.flashcardListModel = flashcardListModel;
        this.activeDeck = activeDeck;
    }

    public JList<Flashcard> getFlashList() {
        return flashList;
    }

    public DefaultListModel<String> getFlashcardListModel() {
        return flashcardListModel;
    }

    public Deck getActiveDeck() {
        return activeDeck;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = flashList.getSelectedIndex();
        activeDeck.removeCardWithIndex(index);
        if (!flashcardListModel.isEmpty()) {
            if (index == flashcardListModel.size()) {
                index--;
            } else {
                flashcardListModel.remove(index);
            }
            flashList.setSelectedIndex(index);
            flashList.ensureIndexIsVisible(index);
        }
    }
}

