package model;


import persistence.Write;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static jdk.nashorn.internal.objects.NativeString.trim;

/**
 * Represents a list of decks
 */

public class DeckCollection extends Write {
    private int activeIndex = 0; //TODO: add deck switch feature
    private Deck activeDeck = null;
    public ArrayList<Deck> deckCollection;

    // EFFECTS: makes a new ArrayList of Decks
    public DeckCollection() {
        deckCollection = new ArrayList<>();
    }

    public Deck getActiveDeck() {
        if (this.activeDeck == null) {
            this.activeDeck = deckCollection.get(activeIndex);
        }
        return this.activeDeck;
    }

    public void setActiveDeck(Deck newDeck) {
        this.activeDeck = newDeck;
    }

    // REQUIRES: cannot add duplicate Deck
    // MODIFIES: this
    // EFFECTS: adds Deck to the DeckCollection
    public void addDeck(Deck d) {
        deckCollection.add(d);
        activeDeck = d;
    }

    // MODIFIES: this
    // EFFECTS: If there is a Deck whose Title matches input, removes it and returns true. Otherwise return false.
    public boolean removeDeck(String deckTitle) {
        trim(deckTitle);
        for (Deck d : deckCollection) {
            if (d.getTitle().equals(deckTitle)) {
                return deckCollection.remove(d);
            }
        }
        return false;
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

    public HashMap<Integer, Deck> mapDecks() {
        HashMap<Integer, Deck> deckHashMap = new HashMap<>();
        for (int i = 0; i < deckCollection.size(); i++) {
            deckHashMap.put(i, deckCollection.get(i));
        }
        return deckHashMap;
    }
}



