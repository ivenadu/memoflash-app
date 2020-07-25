package model;

import java.util.ArrayList;
import java.util.HashSet;

public class Deck {

    ArrayList<Flashcard> cardList;
    String title;


    // EFFECTS: makes a new Deck with title
    public Deck(String title) {
        cardList = new ArrayList<>();
        this.title = title;
    }

    // EFFECTS: returns Deck
    public ArrayList<Flashcard> getCardList() {
        return cardList;
    }

    // REQUIRES: cannot add duplicate card to Deck
    // MODIFIES: this
    // EFFECTS: adds flashcard to the deck
    public void addCard(Flashcard card) {
        cardList.add(card);
    }

    // MODIFIES: this
    // EFFECTS: If Deck contains Flashcard with given name, removes it and return true. Otherwise return false.
    public boolean removeCard(String cardName) {
        for (Flashcard c : cardList) {
            if (c.getName() == cardName) {
                return cardList.remove(c);
            }
        }
        return false;
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
}
