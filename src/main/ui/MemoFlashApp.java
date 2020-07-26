package ui;

import model.Flashcard;
import model.Deck;

import java.util.*;

//TODO: W.I.P.'s:


public class MemoFlashApp {
    private Scanner scan = new Scanner(System.in);
    Deck userDeck;
    private int count;

    //EFFECTS: Runs MemoFlash application
    public MemoFlashApp() {
        runApp();
    }

    // EFFECTS:
    private void runApp() {
        boolean status = true;
        String input;
        userDeck = new Deck("Default");

        mainMenu();

        while (status) {
            input = getInput();

            if (input.equalsIgnoreCase("q")) {
                status = false;
            } else {
                processMenu(input);
            }
            mainMenu();

        }
        System.out.println("Come back and study soon!");

    }

    // EFFECTS: displays main menu's options
    private void mainMenu() {
        System.out.println("Welcome to the main menu. What would you like to do?");
        System.out.println();
        System.out.println("Go to flashcard menu: Press f");
        System.out.println("Go to deck menu: Press d");
        System.out.println("Begin test: Press t");
        System.out.println("End Program: Press q");

    }

    // MODIFIES: this
    // EFFECTS: processes user input at main menu
    private void processMenu(String in) {
        if (in.equalsIgnoreCase("f")) {
            flashcardMenu();
        } else if (in.equalsIgnoreCase("d")) {
            deckMenu();
        } else if (in.equalsIgnoreCase("t")) {
            testMode();
        }

    }

    // MODIFIES: this
    // EFFECTS: displays flashcard menu, takes in user input to evaluate next actions
    private void flashcardMenu() {
        String in = "";
        while (!(in.equalsIgnoreCase("n") || in.equalsIgnoreCase("e")
                || in.equalsIgnoreCase("v") || in.equalsIgnoreCase("d")
                || in.equalsIgnoreCase("m"))) {
            System.out.println("Create a new flashcard:\tPress n");
            System.out.println("Edit an existing flashcard:\tPress e");
            System.out.println("View all flashcards:\tPress v");
            System.out.println("Delete a flashcard:\tPress d");
            System.out.println("Go to main menu:\tPress m");
            in = getInput();
        }
        processFMenu(in);
    }

    // MODIFIES: this
    // EFFECTS: process user's input at Flashcard menu
    public void processFMenu(String in) {
        if (in.equalsIgnoreCase("n")) {
            cardMaker();
        } else if (in.equalsIgnoreCase("e")) {
            cardEditor();
        } else if (in.equalsIgnoreCase("v")) {
            cardViewer();
        } else if (in.equalsIgnoreCase("d")) {
            cardDeleter();
        } else if (in.equalsIgnoreCase("m")) {
            mainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays deck menu and takes in user input to be processed
    private void deckMenu() {

        System.out.println("Create a new deck: Press n");
        System.out.println("Edit active deck: Press e"); //TODO: WIP
        System.out.println("View flashcards in current deck: Press v");
        System.out.println("View all decks: Press g"); //TODO: WIP
        System.out.println("Switch active deck: Press s"); //TODO: WIP
        System.out.println("Delete a deck: Press d"); //TODO: WIP
        System.out.println("Go to main menu: Press m");

        String in = getInput();
        processDMenu(in);
    }

    // MODIFIES: this
    // EFFECTS: process user's input keys at deck menu
    public void processDMenu(String in) {

        if (in.equalsIgnoreCase("n")) {
            deckCreator();
        } else if (in.equalsIgnoreCase("e")) {
            deckEditor();
        } else if (in.equalsIgnoreCase("v")) {
            deckCardsViewer();
        } else if (in.equalsIgnoreCase("g")) {
            decksViewer();
        } else if (in.equalsIgnoreCase("s")) {
            deckSwitcher();
        } else if (in.equalsIgnoreCase("d")) {
            deckDeleter();
        } else if (in.equalsIgnoreCase("m")) {
            mainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new card to the deck
    private void cardMaker() {
        System.out.println("Enter name of card: ");
        String in = getInput();
        System.out.println("Enter question: ");
        String in2 = getInput(); // or set in2 = in to save input??
        System.out.println("Enter answer: ");
        String in3 = getInput();
        Flashcard card = new Flashcard(in, in2, in3);
        userDeck.addCard(card);
        System.out.println("Your flashcard- \tname: " + card.getName() + "\t" + card.getQA()
                + "\t has been created and " + "added to your current deck: " + userDeck.getTitle());
        flashcardMenu();

    }

    // EFFECTS: returns a non empty string
    private String getInput() {
        String str = "";

        while (str.isEmpty()) {
            str = scan.nextLine();
            str = str.trim();
        }
        return str;
    }

    private void cardEditor() {
        System.out.println("WIP");
        mainMenu();
    }

    // EFFECTS: displays cards in deck and available options
    private void cardViewer() {
        String in = "";
        while (!(in.equalsIgnoreCase("f") || in.equalsIgnoreCase("m"))) {
            System.out.println("You have " + userDeck.size() + " cards in the deck.\n");
            System.out.println(userDeck.viewCards());
            System.out.println();
            System.out.println("Go to flashcard menu: Press f");
            System.out.println("Go to main menu: Press m");
            in = getInput();

        }
        processCViewer(in);
    }

    // EFFECTS: process user input at cardViewer
    private void processCViewer(String in) {
        if (in.equalsIgnoreCase("f")) {
            flashcardMenu();
        } else if (in.equalsIgnoreCase("m")) {
            mainMenu();
        }
    }

    private void cardDeleter() {
        if (userDeck.size() == 0) {
            System.out.println("You have no cards in this deck.");
            flashcardMenu();
        }
        loopCardDelete();
    }

    private void loopCardDelete() {
        for (int i = 0; i < userDeck.size(); i++) {
            String name = userDeck.getCardFromIndex(i).getName();
            System.out.println(i + ". " + name);
        }
        System.out.println("Press the number corresponding to the card that you wish to remove from deck.");
        String number = getInput();
        int intIn = Integer.parseInt(number);
        while ((intIn >= userDeck.size()) || intIn < 0) {
            System.out.println("Please enter a number within range to continue.");
            intIn = scan.nextInt();
        }
        userDeck.removeCardWithIndex(intIn);
        String in = "";
        while (!(in.equalsIgnoreCase("m") || in.equalsIgnoreCase("f"))) {
            System.out.println("Card removed. Press 'm' to return to menu or 'f' to return to flashcard menu");
            in = getInput();
        }
        if (in.equalsIgnoreCase("m")) {
            mainMenu();
        }
        flashcardMenu();
    }

    // MODIFIES: this
    // EFFECTS: creates deck with a title
    private void deckCreator() {
        System.out.println("WIP");
        mainMenu();
    }

    // MODIFIES: this
    // EFFECTS: edits current deck
    private void deckEditor() {
        System.out.println("WIP");
        mainMenu();
    }

    // EFFECTS: view card titles in current deck
    private void deckCardsViewer() {
        System.out.println("WIP");
        mainMenu();
    }

    // EFFECTS: displays all decks
    private void decksViewer() {
        System.out.println("WIP");
        mainMenu();
    }

    // MODIFIES: this
    // EFFECTS: switches current deck to another deck
    private void deckSwitcher() {
        System.out.println("WIP");
        mainMenu();
    }

    // MODIFIES: this
    // EFFECTS: deletes deck according to index in list
    private void deckDeleter() {
        System.out.println("WIP");
        mainMenu();
    }

    // EFFECTS: If deck empty, goes back to menu. Otherwise, tests user and displays score at the end.
    private void testMode() {
        if (userDeck.size() == 0) {
            System.out.println("Cannot start test. You have no cards in this deck. \n");
            mainMenu();
        } else {
            doTest();
            System.out.println("\nFinished! Your score: " + this.count + "/" + userDeck.size());

            String in = "";
            while (!in.equalsIgnoreCase("m")) {
                System.out.println("Go back to main menu: Press m");
                in = getInput();
            }
            mainMenu();
        }
    }

    // EFFECTS: go to menu
    private void goToMenu(String in) {
        if (in.equalsIgnoreCase("m")) {
            mainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: for each card, displays question and compares user's input with answer; if matching, adds counter by
    // 1. Otherwise, will display correct answer and no points will be added. Returns count after all cards done.
    private int doTest() {
        int count = 0;
        for (int i = 0; i < userDeck.size(); i++) {
            Flashcard card = userDeck.getCardFromIndex(i);
            String question = card.getQuestion();
            System.out.println(question);
            String in = getInput();
            if (card.getAnswer().equalsIgnoreCase(in)) {
                System.out.println("Correct!");
                count++;
                this.count = count;
            } else {
                this.count = count;
                System.out.println("Incorrect. The answer is " + card.getAnswer());
            }
        }
        return this.count;
    }
}


