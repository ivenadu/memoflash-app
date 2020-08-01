package model;


import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a deck of flashcards with a title
 */
public class Deck {

    ArrayList<Flashcard> cardList;
    String title;


    // EFFECTS: makes a new Deck with title
    public Deck(String title) {
        cardList = new ArrayList<>();
        this.title = title;
    }

    // REQUIRES: cannot add duplicate card to Deck
    // MODIFIES: this
    // EFFECTS: adds flashcard to the deck
    public void addCard(Flashcard card) {
        cardList.add(card);
    }


    // EFFECTS: returns, from least to most recently added, each Flashcard's name, question, and answer in the Deck
    public String viewCards() {
        String appendedResult = new String();
        if (cardList.size() == 0) {
            return "There are no cards in the deck.";
        }
        for (Flashcard c : cardList) {
            String x = "Card Name: " + c.getName() + "\n" + c.getQA() + "\n\n";
            appendedResult += x;
        }
        return appendedResult;
    }


    // MODIFIES: this
    // EFFECTS: removes all of the cards in the deck
    public void clearDeck() {
        cardList.clear();
    }

    // EFFECTS: If this and the Deck being compared share the same title, return true. Otherwise return false.
    public boolean sameTitle(Deck d) {
        return (this.title.equals(d.title));
    }

    // EFFECTS: return size of deck
    public int size() {
        return cardList.size();
    }

    // EFFECTS: return title of the Deck
    public String getTitle() {
        return title;
    }

    // MODIFIES: this
    // EFFECTS: rename the title of the Deck
    public void setTitle(String title) {
        this.title = title;
    }

    // REQUIRES: given integer is < deck size
    // EFFECTS: retrieves Flashcard at given index
    public Flashcard getCardFromIndex(int i) {
        return cardList.get(i);
    }

    // MODIFIES: this
    // EFFECTS: removes Flashcard at given index
    public Flashcard removeCardWithIndex(int i) {
        return cardList.remove(i);
    }
}
