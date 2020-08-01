package ui;

import model.DeckCollection;
import model.Flashcard;
import model.Deck;

import java.util.*;

//Sources referred to:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git (ui menu and while loop designs)
// https://beginnersbook.com/2017/08/java-break-statement/

/**
 * MemoFlash application
 */

public class MemoFlashApp {
    private Scanner scan = new Scanner(System.in);
    Deck userDeck;
    private int count;
    DeckCollection deckCollection;


    //EFFECTS: Runs MemoFlash application
    public MemoFlashApp() {
        runApp();
    }

    // EFFECTS:
    private void runApp() {

        String input;
        userDeck = new Deck("Default");
        Deck deck1 = new Deck("Random");
        deckCollection = new DeckCollection();
        deckCollection.addDeck(userDeck);
        deckCollection.addDeck(deck1);

        boolean status = true;

        mainMenu();

        while (status) {
            input = getInput();

            if (input.equalsIgnoreCase("q")) {
                HashMap<Integer, Deck> deckMap = (deckCollection.mapDecks());
                deckCollection.mapObject(deckMap);
                status = false;
            } else {
                processMenu(input);
            }
        }
        System.out.println("Come back and study soon!");
    }

    // EFFECTS: displays main menu's options
    private void mainMenu() {
        System.out.println("Welcome to the main menu. What would you like to do?");
        System.out.println();
        System.out.println("Go to flashcard menu: Press f");//TODO: not all features done yet
        System.out.println("Go to deck menu: Press d [WIP]"); //TODO: not done yet
        System.out.println("Begin test: Press t");
        System.out.println("End Program: Press q\n");

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
            System.out.println("Create a new flashcard: Press n");
            System.out.println("Edit an existing flashcard: Press e [WIP]"); //TODO: not done yet
            System.out.println("View all flashcards: Press v");
            System.out.println("Delete a flashcard: Press d");
            System.out.println("Go to main menu: Press m");
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
        String in = "";

        while (!(in.equalsIgnoreCase("n") || in.equalsIgnoreCase("e")
                || in.equalsIgnoreCase("v") || in.equalsIgnoreCase("g")
                || in.equalsIgnoreCase("s") || in.equalsIgnoreCase("d")
                || in.equalsIgnoreCase("m"))) {
            System.out.println("Create a new deck: Press n"); //TODO: WIP
            System.out.println("Edit active deck: Press e"); //TODO: WIP
            System.out.println("View flashcards in current deck: Press v"); //TODO: WIP
            System.out.println("View all decks: Press g"); //TODO: WIP
            System.out.println("Switch active deck: Press s"); //TODO: WIP
            System.out.println("Delete a deck: Press d"); //TODO: WIP
            System.out.println("Go to main menu: Press m");
            in = getInput();
        }


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
        System.out.println("You have " + userDeck.size() + " cards in the deck.\n");
        System.out.println(userDeck.viewCards());
        System.out.println();
        goToMenu();
    }

    // REQUIRES: must input an integer
    // MODIFIES: this
    // EFFECTS: If there are no cards in deck, will display message and go to flashcard menu.
    // Otherwise, displays indexed card names, then deletes the card corresponding to index of user's input number.
    private void cardDeleter() {
        if (userDeck.size() == 0) {
            System.out.println("You have no cards in this deck.");
            flashcardMenu();
        }
        loopCardDelete();
    }

    // MODIFIES: this
    // EFFECTS: Displays indexed card names, then deletes the card corresponding to index of user's input number.
    private void loopCardDelete() {
        for (int i = 0; i < userDeck.size(); i++) {
            String name = userDeck.getCardFromIndex(i).getName();
            System.out.println(i + ". " + name);
        }
        System.out.println("Press the number corresponding to the card that you wish to remove from deck.");

        int intIn;

        while (true) {
            String number = getInput();
            intIn = tryNumFormExcept(number);
            if (intIn != -1) {
                break;
            }
            System.out.println("Please enter integer within range.");
        }
        userDeck.removeCardWithIndex(intIn);
        System.out.println("Card removed.");
        goToMenu();
    }

    // EFFECTS: Returns user input if it is a valid index integer. If not an integer, catches exception.
    // Otherwise return -1 and keeps user in loop.
    private int tryNumFormExcept(String in) {
        try {
            int i = Integer.parseInt(in);
            if (i >= 0 && i < userDeck.size()) {
                return i;
            }
        } catch (NumberFormatException e) {
            System.out.println("Your input is not an integer!");
        }
        return -1;
    }

    // MODIFIES: this
    // EFFECTS: creates deck with a title
    private void deckCreator() {
        System.out.println("WIP");
        goToMenu();
    }

    // MODIFIES: this
    // EFFECTS: edits current deck
    private void deckEditor() {
        System.out.println("WIP");
        goToMenu();
    }

    // EFFECTS: view card titles in current deck
    private void deckCardsViewer() {
        System.out.println(userDeck.viewCards());
        goToMenu();
    }

    // EFFECTS: displays all decks
    private void decksViewer() {
        System.out.println("WIP");
        goToMenu();
    }

    // MODIFIES: this
    // EFFECTS: switches current deck to another deck
    private void deckSwitcher() {
        System.out.println("WIP");
        goToMenu();
    }

    // MODIFIES: this
    // EFFECTS: deletes deck according to index in list
    private void deckDeleter() {
        System.out.println("WIP");
        goToMenu();
    }

    // EFFECTS: If deck empty, goes back to menu. Otherwise, tests user and displays score at the end.
    private void testMode() {
        if (userDeck.size() == 0) {
            System.out.println("Cannot start test. You have no cards in this deck. \n");
            goToMenu();
        } else {
            doTest();
            System.out.println("\nFinished! Your score: " + this.count + "/" + userDeck.size());
            goToMenu();
        }
    }

    // EFFECTS: goes to main menu once user presses 'm'
    private void goToMenu() {
        String in = "";
        while (!in.equalsIgnoreCase("m")) {
            System.out.println("Go back to main menu: Press m");
            in = getInput();
        }
        mainMenu();
    }

    // MODIFIES: this
    // EFFECTS: for each card, displays question and compares user's input with answer; if matches, adds this.count by
    // 1. Otherwise, will display correct answer and count does not increment.
    private void doTest() {
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
                System.out.println("Incorrect. The answer is: " + "\"" + card.getAnswer() + "\"");
            }
        }
    }
}


