package ui.gui;

import model.Deck;
import model.Flashcard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RemoveListener implements ActionListener {
    private JList<Flashcard> flashcardJList;
    private DefaultListModel<String> flashcardListModel;
    private Deck activeDeck;

    public RemoveListener(JList<Flashcard> flashcardJList, DefaultListModel<String> flashcardListModel, Deck activeDeck) {
        this.flashcardJList = flashcardJList;
        this.flashcardListModel = flashcardListModel;
        this.activeDeck = activeDeck;
    }

    public JList<Flashcard> getFlashcardJList() {
        return flashcardJList;
    }

    public DefaultListModel<String> getFlashcardListModel() {
        return flashcardListModel;
    }

    public Deck getActiveDeck() {
        return activeDeck;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = flashcardJList.getSelectedIndex();
        activeDeck.removeCardWithIndex(index);
        if (flashcardListModel.isEmpty()) {
            return;
        } else {
            if (index == flashcardListModel.size()) {
                index--;
            } else {
                flashcardListModel.remove(index);
                // }
            }
            flashcardJList.setSelectedIndex(index);
            flashcardJList.ensureIndexIsVisible(index);
        }
    }
}

