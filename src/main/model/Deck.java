package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a deck of flashcards with a title
 */
public class Deck {

    private ArrayList<Flashcard> cardList;
    private String title;

    public Deck() {
    }

    // EFFECTS: makes a new Deck with title
    public Deck(String title) {
        cardList = new ArrayList<>();
        this.title = title;
    }


    // MODIFIES: this
    // EFFECTS: adds flashcard to the deck; throws exception if duplicate
    public void addCard(Flashcard card) throws NonDistinctException {
        if (!cardList.contains(card)) {
            cardList.add(card);
        } else {
            throw new NonDistinctException();
        }
    }

    // EFFECTS: returns, from least to most recently added, each Flashcard's name, question, and answer in the Deck
    public String viewCards() {
        StringBuilder appendedResult = new StringBuilder();
        if (cardList.size() == 0) {
            return "There are no cards in the deck.";
        }
        for (Flashcard c : cardList) {
            String x = "Card Name: " + c.getName() + "\n" + c.getQA() + "\n\n";
            appendedResult.append(x);
        }
        return appendedResult.toString();
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

    public ArrayList<Flashcard> getCardList() {
        return cardList;
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

    // REQUIRES: given integer is < deck size
    // MODIFIES: this
    // EFFECTS: removes Flashcard at given index
    public void removeCardWithIndex(int i) {
        cardList.remove(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        Deck deck = (Deck) o;
        return this.cardList.equals(deck.cardList)
                && this.title.equalsIgnoreCase(deck.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardList, title);
    }
}

