package ui;

import model.Flashcard;
import model.Deck;

import java.util.*;

//TODO: W.I.P.'s: testMode


public class MemoFlashApp {
    private Scanner scan = new Scanner(System.in);
    Deck userDeck = new Deck("Default");
    private int count;

    //EFFECTS: Runs MemoFlash application
    public MemoFlashApp() {
        runApp(scan);
    }

    // EFFECTS:
    private void runApp(Scanner scan) {
        boolean status = true;
        String input;

        mainMenu();

        while (status) {
            input = scan.next();

            if (input.equalsIgnoreCase("q")) {
                status = false;
            } else {
                processInput(input);
            }

        }
        System.out.println("Come back and study again soon!");

    }

    private void mainMenu() {
        System.out.println("Welcome to the main menu. Any time you want to exit program, just press 'q'.");
        System.out.println("What do you want to do?");
        System.out.println();
        System.out.println("Go to flashcard menu: Press f");
        System.out.println("Go to deck menu: Press d");
        System.out.println("Begin test: Press t");
        System.out.println("End Program: Press q");

    }

    private void processInput(String in) {
        if (in.equalsIgnoreCase("f")) {
            flashcardMenu();
        } else if (in.equalsIgnoreCase("d")) {
            deckMenu();
        } else if (in.equalsIgnoreCase("t")) {
            testMode();
        }

    }

    private void flashcardMenu() {
        System.out.println("Create a new flashcard:\tPress n");
        System.out.println("Edit an existing flashcard:\tPress e");
        System.out.println("View all flashcards:\tPress v");
        System.out.println("Delete a flashcard:\tPress d");
        System.out.println("Go to main menu:\tPress m");
        String in = this.scan.next();
        processFMenu(in);
    }

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
        } else {
            System.out.println("Please enter a valid input.");
        }
    }

    private void deckMenu() {


        System.out.println("Create a new deck: Press n");
        System.out.println("Edit active deck: Press e");
        System.out.println("View flashcards in current deck: Press v");
        System.out.println("View all decks: Press g");
        System.out.println("Switch active deck: Press s");
        System.out.println("Delete a deck: Press d");
        System.out.println("Go to main menu: Press m");

        String in = getInput();
        processDMenu(in);
    }

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


    private void cardMaker() {
        System.out.println("Enter name of card: ");
        String in = getInput();
        System.out.println("Enter question: ");
        String in2 = getInput(); // or set in2 = in to save input??
        System.out.println("Enter answer: ");
        String in3 = getInput();
        Flashcard card = new Flashcard(in, in2, in3);
        userDeck.addCard(card);
        System.out.println("Your flashcard- \t name: " + card.getName() + "\t" + card.getQA()
                + "\t has been created and " + "added to your current deck: " + userDeck.getTitle());
        flashcardMenu();

    }

    private String getInput() {
        String str = "";

        while (str.isEmpty()) {
            str = scan.nextLine();
        }
        return str;
    }

    private void cardEditor() {
    }

    private void cardViewer() {
        System.out.println("You have " + userDeck.size() + " cards in the deck.\n");
        System.out.println(userDeck.viewCards());
        System.out.println();
        System.out.println("Go to flashcard menu: Press f");
        System.out.println("Go to main menu: Press m");
        String in = getInput();
        processCViewer(in);

    }

    private void processCViewer(String in) {
        if (in.equalsIgnoreCase("f")) {
            flashcardMenu();
        } else if (in.equalsIgnoreCase("m")) {
            mainMenu();
        }
    }

    private void cardDeleter() {
    }

    private void deckCreator() {
    }

    private void deckEditor() {
    }

    private void deckCardsViewer() {
    }

    private void decksViewer() {
    }

    private void deckSwitcher() {
    }

    private void deckDeleter() {
    }

    private void testMode() {
        while (userDeck.size() == 0) {
            System.out.println("You have no cards in this deck. Please press 'm' to return to main menu.");
            String in = getInput();
            processAfterTest(in);
        }
        doTest();
        System.out.println("\nFinished! Your score: " + this.count + "/" + userDeck.size());
        System.out.println("Go back to main menu: Press m");
        String in = getInput();
        processAfterTest(in);
    }

    private void processAfterTest(String in) {
        if (in.equalsIgnoreCase("m")) {
            mainMenu();
        }
    }

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


