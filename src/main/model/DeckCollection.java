package model;


import persistence.Write;

import java.util.ArrayList;

/**
 * Represents a list of decks
 */

public class DeckCollection extends Write {
    private int activeIndex;

    public ArrayList<Deck> deckCollection;

    // EFFECTS: makes a new ArrayList of Decks
    public DeckCollection() {
        deckCollection = new ArrayList<>();
    }

    // EFFECTS: retrieves active index
    public int getActiveIndex() {
        return activeIndex;
    }

    // EFFECTS: returns the active deck
    public Deck getActiveDeck() {

        if (deckCollection.isEmpty()) {
            throw new RuntimeException("no deck!");
        }
        return this.deckCollection.get(getActiveIndex());
    }

    // MODIFIES: this
    // EFFECTS: sets active index corresponding to input deck
    public void setActiveIndex(Deck deck) {

        for (int i = 0; i < deckCollection.size(); i++) {
            if (deckCollection.get(i) == deck) {
                this.activeIndex = i;
            }
        }
    }

    // REQUIRES: cannot add duplicate Deck
    // MODIFIES: this
    // EFFECTS: adds Deck to the DeckCollection
    public void addDeck(Deck d) {
        deckCollection.add(d);
        setActiveIndex(d);
    }

    // EFFECTS: Returns size of DeckCollection.
    public int size() {
        return deckCollection.size();
    }

    // EFFECTS: Returns the titles of the Decks in Deck Collection, from least to most recently added
    public String viewDeckTitles() {
        StringBuilder appendedResult = new StringBuilder();
        if (deckCollection.size() == 0) {
            return "There are no decks.";
        }
        for (int i = 0; i < deckCollection.size(); i++) {
            String title = i + ". " + deckCollection.get(i).getTitle() + "\n";
            appendedResult.append(title);
        }
        return appendedResult.toString();
    }

    // MODIFIES: this
    // EFFECTS: removes the deck at given index
    public void removeDeck(int index) {
        deckCollection.remove(index);
    }

    // EFFECTS: retrieve deck at index
    public Deck retrieveDeckWithIndex(int index) {
        return deckCollection.get(index);
    }
}



