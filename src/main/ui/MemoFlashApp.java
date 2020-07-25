package ui;

import model.Flashcard;
import model.Deck;

import java.util.*;

//TODO: W.I.P.'s: testMode

public class MemoFlashApp {
    private Scanner scan = new Scanner(System.in);
    private int total;
    private Deck userDeck = new Deck("Default Deck");
    int card;
    int deck;
    static final int MAX = 999;

    //EFFECTS: Runs MemoFlash application
    public MemoFlashApp() {
        runApp(scan);
    }

    // EFFECTS:
    private void runApp(Scanner scan) {
        boolean status = true;
        String input = null;

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
        System.out.println("Welcome to the main menu. Any time you want to come back here, just press 'm'!");
        System.out.println("What do you want to do?");
        System.out.println();
        System.out.println("Go to flashcard menu: Press f");
        System.out.println("Go to deck menu: Press d");
        System.out.println("Begin test: Press t"); //TODO: WIP
        System.out.println("End Program: Press q");

    }

    private void processInput(String in) {
        if (in.equalsIgnoreCase("f")) {
            flashcardMenu();
        } else if (in.equalsIgnoreCase("d")) {
            deckMenu();
//        } else if (in.equalsIgnoreCase("t")) { //TODO: WIP
//            testMode();
        }

    }

    private void flashcardMenu() {
        String in = this.scan.nextLine();
        System.out.println("Create a new flashcard:\tPress n");
        System.out.println("Edit an existing flashcard:\tPress e");
        System.out.println("View all flashcards:\tPress v");
        System.out.println("Delete a flashcard:\tPress d");
        System.out.println("Go to main menu:\tPress m");
        if (in.equalsIgnoreCase("n")) {
            cardMaker(this.scan);
        } else if (in.equalsIgnoreCase("e")) {
            cardEditor(this.scan);
        } else if (in.equalsIgnoreCase("v")) {
            cardViewer(this.scan);
        } else if (in.equalsIgnoreCase("d")) {
            cardDeleter(this.scan);
        } else if (in.equalsIgnoreCase("m")) {
            mainMenu();
        }
    }

    private void deckMenu() {
        String in = scan.nextLine();

        System.out.println("Create a new deck: Press n");
        System.out.println("Edit active deck: Press e");
        System.out.println("View flashcards in current deck: Press v");
        System.out.println("View all decks: Press g");
        System.out.println("Switch active deck: Press s");
        System.out.println("Delete a deck: Press d");
        System.out.println("Go to main menu: Press m");

        if (in.equalsIgnoreCase("n")) {
            deckCreator(this.scan);
        } else if (in.equalsIgnoreCase("e")) {
            deckEditor(this.scan);
        } else if (in.equalsIgnoreCase("v")) {
            deckCardsViewer(this.scan);
        } else if (in.equalsIgnoreCase("g")) {
            decksViewer(this.scan);
        } else if (in.equalsIgnoreCase("s")) {
            deckSwitcher(this.scan);
        } else if (in.equalsIgnoreCase("d")) {
            deckDeleter(this.scan);
        } else if (in.equalsIgnoreCase("m")) {
            mainMenu();
        }
    }

    private void cardMaker(Scanner scan) {
        String in = scan.nextLine();
        System.out.println("Enter name of card: ");
        String in2 = scan.nextLine(); // or set in2 = in to save input??
        System.out.println("Enter question: ");
        String in3 = scan.nextLine();
        System.out.println("Enter answer: ");
        Flashcard card = new Flashcard(in, in2, in3);
        userDeck.addCard(card);
        System.out.println("Your flashcard " + card.getName() + " " + card.getQA() + " has been created and "
                + "added to your current deck.");
        flashcardMenu();

    }

    private void cardEditor(Scanner scan) {}

    private void cardViewer(Scanner scan) {}

    private void cardDeleter(Scanner scan) {}

    private void deckCreator(Scanner scan) {}

    public void deckEditor(Scanner scan) {}

    public void deckCardsViewer(Scanner scan) {}

    public void decksViewer(Scanner scan) {}

    public void deckSwitcher(Scanner scan) {}

    public void deckDeleter(Scanner scan) {}

}
