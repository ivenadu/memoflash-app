package model;

import java.util.ArrayList;
import java.util.HashSet;

import static jdk.nashorn.internal.objects.NativeString.trim;

public class DeckCollection {
    ArrayList<Deck> deckCollection;

    // EFFECTS: makes a new Hashset of Decks
    public DeckCollection() {
        deckCollection = new ArrayList<>();
    }

    // REQUIRES: cannot add duplicate Deck
    // MODIFIES: this
    // EFFECTS: adds Deck to the DeckCollection
    public void addDeck(Deck d) {
        deckCollection.add(d);
    }

    // MODIFIES: this
    // EFFECTS: If there is a Deck whose Title matches input, removes it and returns true. Otherwise return false.
    public boolean removeDeck(String deckTitle) {
        trim(deckTitle);
        for (Deck d: deckCollection) {
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
        for (Deck d : deckCollection) {
            String title = "Deck: " + d.getTitle() + "\n";
            appendedResult += title;
        }
        return appendedResult;
    }
}
