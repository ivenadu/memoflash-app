package model;

public class Flashcard {
    private String question;
    private String answer;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // EFFECTS: returns the question and answer of the flashcard
    public String getQA() {
        return "Question: " + getQuestion() + "\nAnswer: " + getAnswer();
    }

    // EFFECTS: returns the question
    public String getQuestion() {
        return this.question;
    }

    // EFFECTS: returns the answer
    public String getAnswer() {
        return answer;
    }

    // MODIFIES: this
    // EFFECTS: edits the card's question and answer
    public void editCard(String editQ, String editA) {
        this.question = editQ;
        this.answer = editA;

    }
}
