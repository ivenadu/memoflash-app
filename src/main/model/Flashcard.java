package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.String;
import java.util.Objects;

/**
 * Represents a Flashcard with a name, a question, and an answer.
 */

public class Flashcard {
    private String name;
    private String question;
    private String answer;

    //EFFECTS: an empty Flashcard constructor
    public Flashcard() {
    }

    //REQUIRES: parameter inputs cannot be empty string
    // EFFECTS: make a flashcard with a name, question, and answer
    public Flashcard(String name, String question, String answer) {
        this.name = name;
        this.question = question;
        this.answer = answer;
    }

    // EFFECTS: returns the question and answer of the flashcard
    @JsonIgnore
    public String getQA() {
        return "Question: " + getQuestion() + "\tAnswer: " + getAnswer();
    }

    // EFFECTS: returns the Flashcard's question
    public String getQuestion() {
        return this.question;
    }

    // EFFECTS: returns the Flashcard's answer
    public String getAnswer() {
        return answer;
    }

    // EFFECTS: returns the Flashcard's name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: edits the card's question and answer
    public void setQA(String editQ, String editA) {
        this.question = editQ;
        this.answer = editA;
    }

    // MODIFIES: this
    // EFFECTS: changes the card's name
    public void setName(String newName) {
        this.name = newName;
    }

    // EFFECTS: returns true if the input string equals the answer, case insensitive. Otherwise, return false.
    public boolean correctAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(this.answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flashcard)) {
            return false;
        }
        Flashcard flashcard = (Flashcard) o;
        return Objects.equals(getName(), flashcard.getName())
                && Objects.equals(getQuestion(), flashcard.getQuestion())
                && Objects.equals(getAnswer(), flashcard.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getQuestion(), getAnswer());
    }
}
