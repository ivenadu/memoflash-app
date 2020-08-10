package ui.gui;

import model.Deck;
import model.Flashcard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveListener implements ActionListener {
    JButton button;
    private JList<Flashcard> flashList;
    private DefaultListModel<String> flashcardListModel;
    private Deck activeDeck;

    public RemoveListener(JButton button, JList<Flashcard> flashList, DefaultListModel<String> flashcardListModel,
                          Deck activeDeck) {
        this.flashList = flashList;
        this.flashcardListModel = flashcardListModel;
        this.activeDeck = activeDeck;
        this.button = button;
        emptyCase();
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

    public void emptyCase() {
        if (activeDeck.getCardList().isEmpty()) {
            button.setEnabled(false);
        }
    }
}

