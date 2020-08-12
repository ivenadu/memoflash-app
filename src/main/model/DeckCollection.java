package model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

/**
 * Represents a list of decks
 */

public class DeckCollection {
    private int activeIndex;

    public ArrayList<Deck> decks;

    // EFFECTS: makes a new ArrayList of Decks
    public DeckCollection() {
        decks = new ArrayList<>();
    }

    // EFFECTS: retrieves active index
    public int getActiveIndex() {
        return activeIndex;
    }

    // EFFECTS: returns the active deck
    @JsonIgnore
    public Deck getActiveDeck() {

        if (decks.isEmpty()) {
            throw new RuntimeException("no deck!");
        }
        return this.decks.get(getActiveIndex());
    }

    // MODIFIES: this
    // EFFECTS: sets active index corresponding to input deck
    @JsonIgnore
    public void setActiveDeck(Deck deck) {
        this.activeIndex = decks.indexOf(deck);
    }

    // REQUIRES: cannot add duplicate Deck
    // MODIFIES: this
    // EFFECTS: adds Deck to the DeckCollection
    public void addDeck(Deck d) {
        if (d == null) {
            throw new RuntimeException("Cannot be null");
        }
        decks.add(d);
        setActiveDeck(d);
    }

    // EFFECTS: Returns size of DeckCollection.
    public int size() {
        return decks.size();
    }

    // EFFECTS: Returns the titles of the Decks in Deck Collection, from least to most recently added
    public String viewDeckTitles() {
        StringBuilder appendedResult = new StringBuilder();
        if (decks.size() == 0) {
            return "There are no decks.";
        }
        for (int i = 0; i < decks.size(); i++) {
            String title = i + ". " + decks.get(i).getTitle() + "\n";
            appendedResult.append(title);
        }
        return appendedResult.toString();
    }

    // MODIFIES: this
    // EFFECTS: removes the deck at given index
    public void removeDeck(int index) {
        if (this.decks.size() == 1) {
            throw new RuntimeException("Last deck cannot be deleted");
        }
        decks.remove(index);
        if (this.activeIndex >= this.decks.size()) {
            this.activeIndex = this.decks.size() - 1;
        }
    }

    // EFFECTS: retrieve deck at index
    public Deck retrieveDeckWithIndex(int index) throws IndexOutOfBoundsException {
        return decks.get(index);
    }
}



