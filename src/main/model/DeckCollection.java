package model;


import persistence.Write;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static jdk.nashorn.internal.objects.NativeString.trim;
import static jdk.nashorn.internal.runtime.ScriptObject.PROTO_PROPERTY_NAME;

/**
 * Represents a list of decks
 */

public class DeckCollection extends Write {
    private int activeIndex;//TODO: add deck switch feature

    public ArrayList<Deck> deckCollection;

    // EFFECTS: makes a new ArrayList of Decks
    public DeckCollection() {
        deckCollection = new ArrayList<>();
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public Deck getActiveDeck() {
//        if (this.activeIndex == -1) {
//            this.activeIndex = 0;
//        }
        if (deckCollection.isEmpty()) {
            throw new RuntimeException("no deck!");
        }
        return this.deckCollection.get(getActiveIndex());
    }

    public void setActiveDeck(Deck deck) {

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
        setActiveDeck(d);
    }

    // EFFECTS: Returns size of DeckCollection.
    public int size() {
        return deckCollection.size();
    }

    // EFFECTS: Returns the titles of the Decks in Deck Collection, from least to most recently added
    public String viewDeckTitles() {
        String appendedResult = new String();
        if (deckCollection.size() == 0) {
            return "There are no decks.";
        }
        for (int i = 0; i < deckCollection.size(); i++) {
            String title = i + ". " + deckCollection.get(i).getTitle() + "\n";
            deckCollection.get(i);
            appendedResult += title;
        }
        return appendedResult;
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



