package model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import persistence.Write;

import java.security.InvalidParameterException;
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
    @JsonIgnore
    public Deck getActiveDeck() {

        if (deckCollection.isEmpty()) {
            throw new RuntimeException("no deck!");
        }
        return this.deckCollection.get(getActiveIndex());
    }

    // MODIFIES: this
    // EFFECTS: sets active index corresponding to input deck
    @JsonIgnore
    public void setActiveDeck(Deck deck) {
        this.activeIndex = deckCollection.indexOf(deck);
    }

    // REQUIRES: cannot add duplicate Deck
    // MODIFIES: this
    // EFFECTS: adds Deck to the DeckCollection
    public void addDeck(Deck d) {
        if (!deckCollection.add(d) || d == null) {
            throw new RuntimeException("Deck not added successfully.");
        }
        setActiveDeck(d);
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
        if (this.deckCollection.size() == 1) {
            throw new RuntimeException("Last deck cannot be deleted");
        }
        deckCollection.remove(index);
        if (this.activeIndex >= this.deckCollection.size()) {
            this.activeIndex = this.deckCollection.size() - 1;
        }
    }

    // EFFECTS: retrieve deck at index
    public Deck retrieveDeckWithIndex(int index) {
        return deckCollection.get(index);
    }
}



