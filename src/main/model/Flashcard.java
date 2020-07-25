package model;

import static com.sun.xml.internal.bind.v2.schemagen.Util.equalsIgnoreCase;
import static jdk.nashorn.internal.objects.NativeString.trim;

public class Flashcard {
    private String name;
    private String question;
    private String answer;

    public Flashcard(String name, String question, String answer) {
        this.name = name;
        this.question = question;
        this.answer = answer;
    }

    // EFFECTS: returns the question and answer of the flashcard
    public String getQA() {
        return "Question: " + getQuestion() + "\nAnswer: " + getAnswer();
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

    // EFFECTS: returns true if the input string equals the answer, case insensitive and regardless of whitespace
    // characters before or after the string. If not equal, return false.
    public boolean correctAnswer(String userAnswer) {
        userAnswer = trim(userAnswer);
        return equalsIgnoreCase(this.answer, userAnswer);
    }


}
