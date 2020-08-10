package ui.console;

import model.DeckCollection;
import model.Flashcard;
import model.Deck;
import persistence.Load;
import persistence.Write;

import java.io.IOException;
import java.util.*;

//Sources referred to:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git (ui menu and while loop designs)
// https://beginnersbook.com/2017/08/java-break-statement/

/**
 * MemoFlash application
 */

public class MemoFlashApp extends Load {
    private DeckCollection deckCollection = null;
    private Scanner scan = new Scanner(System.in);
    private int count;
    private static final String NEW = "n";
    private static final String VIEW_CARDS = "v";
    private static final String VIEW_DECKS = "g";
    private static final String DELETE = "x";
    private static final String MAIN_MENU = "m";
    private static final String CARD_MENU = "f";
    private static final String DECK_MENU = "d";
    private static final String TEST = "t";
    private static final String SWITCH = "s";
    private static final String QUIT = "q";

    private Deck getUserDeck() {
        return deckCollection.getActiveDeck();
    }

    // EFFECTS:
    public void runApp() {
        try {
            deckCollection = Load.loadFile("./data/myFile.json");
        } catch (Exception ex) {
            deckCollection = new DeckCollection();
            deckCollection.addDeck(new Deck("Default"));
        }

        String input;

        boolean status = true;
        mainMenu();

        while (status) {
            input = getInput();
            if (input.equalsIgnoreCase(QUIT)) {
                try {
                    Write.save(deckCollection, "./data/myFile.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                status = false;
            } else {
                processMenu(input);
            }
        }
        System.out.println("Come back and study soon!");
    }

    // EFFECTS: displays main menu's options
    private void mainMenu() {
        System.out.println("Welcome to the main menu. Your active deck is: \"" + getUserDeck().getTitle()
                + "\" \nWhat would you like to do?");
        System.out.println();
        System.out.println("Go to flashcard menu: Press f");
        System.out.println("Go to deck menu: Press d");
        System.out.println("Begin test: Press t");
        System.out.println("End Program: Press q\n");

    }

    // MODIFIES: this
    // EFFECTS: processes user input at main menu
    private void processMenu(String in) {
        if (in.equalsIgnoreCase(CARD_MENU)) {
            flashcardMenu();
        } else if (in.equalsIgnoreCase(DECK_MENU)) {
            deckMenu();
        } else if (in.equalsIgnoreCase(TEST)) {
            testMode();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays flashcard menu, takes in user input to evaluate next actions
    private void flashcardMenu() {
        String in = "";
        while (!(in.equalsIgnoreCase(NEW) || in.equalsIgnoreCase(VIEW_CARDS)
                || in.equalsIgnoreCase(DELETE) || in.equalsIgnoreCase(MAIN_MENU))) {
            System.out.println("Create a new flashcard: Press n");
            System.out.println("View all flashcards: Press v");
            System.out.println("Delete a flashcard: Press x");
            System.out.println("Go to main menu: Press m");
            in = getInput();
        }
        processFMenu(in);
    }

    // MODIFIES: this
    // EFFECTS: process user's input at Flashcard menu
    public void processFMenu(String in) {
        if (in.equalsIgnoreCase(NEW)) {
            cardMaker();
        } else if (in.equalsIgnoreCase(VIEW_CARDS)) {
            cardViewer();
        } else if (in.equalsIgnoreCase(DELETE)) {
            cardDeleter();
        } else if (in.equalsIgnoreCase(MAIN_MENU)) {
            mainMenu();
        }
    }


    // MODIFIES: this
    // EFFECTS: displays deck menu and takes in user input to be processed
    private void deckMenu() {
        String in = "";

        while (!(in.equalsIgnoreCase(NEW) || in.equalsIgnoreCase(VIEW_CARDS) || in.equalsIgnoreCase(VIEW_DECKS)
                || in.equalsIgnoreCase(SWITCH) || in.equalsIgnoreCase(DELETE) || in.equalsIgnoreCase(MAIN_MENU))) {
            System.out.println("Create a new deck: Press n");
            System.out.println("View flashcards in current deck: Press v");
            System.out.println("View all decks: Press g");
            System.out.println("Switch active deck: Press s");
            System.out.println("Delete a deck: Press x");
            System.out.println("Go to main menu: Press m");
            in = getInput();
        }


        processDMenu(in);
    }

    // MODIFIES: this
    // EFFECTS: process user's input keys at deck menu
    public void processDMenu(String in) {

        if (in.equalsIgnoreCase(NEW)) {
            deckCreator();
        } else if (in.equalsIgnoreCase(VIEW_CARDS)) {
            deckCardsViewer();
        } else if (in.equalsIgnoreCase(VIEW_DECKS)) {
            decksViewer();
        } else if (in.equalsIgnoreCase(SWITCH)) {
            deckSwitcher();
        } else if (in.equalsIgnoreCase(DELETE)) {
            deckDeleter();
        } else if (in.equalsIgnoreCase(MAIN_MENU)) {
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
        if (!getUserDeck().getCardList().contains(card)) {
            getUserDeck().addCard(card);

            System.out.println("Your flashcard- \tname: " + card.getName() + "\t" + card.getQA()
                    + "\t has been created and " + "added to your current deck: " + getUserDeck().getTitle());
        } else {
            System.out.println("Add unsuccessful. There is already an identical card in your current deck.");
        }
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

    // EFFECTS: displays cards in deck and available options
    private void cardViewer() {
        System.out.println("You have " + getUserDeck().size() + " card(s) in your active deck: "
                + getUserDeck().getTitle() + "\n");
        System.out.println(getUserDeck().viewCards());
        System.out.println();
        goToMenu();
    }

    // REQUIRES: must input an integer
    // MODIFIES: this
    // EFFECTS: If there are no cards in deck, will display message and go to flashcard menu.
    // Otherwise, displays indexed card names, then deletes the card corresponding to index of user's input number.
    private void cardDeleter() {
        if (getUserDeck().size() == 0) {
            System.out.println("You have no cards in your active deck: " + getUserDeck().getTitle());
            flashcardMenu();
        }
        loopCardDelete();
    }

    // MODIFIES: this
    // EFFECTS: Displays indexed card names, then deletes the card corresponding to index of user's input number.
    private void loopCardDelete() {
        for (int i = 0; i < getUserDeck().size(); i++) {
            String name = getUserDeck().getCardFromIndex(i).getName();
            System.out.println(i + ". " + name);
        }
        System.out.println("Press the number corresponding to the card that you wish to remove from your active deck: "
                + getUserDeck().getTitle());

        int intIn;

        while (true) {
            String number = getInput();
            intIn = tryNumFormExcept(number, getUserDeck().size());
            if (intIn != -1) {
                break;
            } else {
                System.out.println("Please enter integer within range.");
            }
        }
        getUserDeck().removeCardWithIndex(intIn);
        System.out.println("Card removed from \"" + getUserDeck().getTitle() + "\"" + "deck.");
        goToMenu();
    }

    // EFFECTS: Returns user input if it is a valid index integer. If not an integer, catches exception.
    // Otherwise return -1 and keeps user in loop.
    private int tryNumFormExcept(String in, int size) {
        try {
            int i = Integer.parseInt(in);
            if (i >= 0 && i < size) {
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
        System.out.println("Please enter a title for your deck: ");
        String in = getInput();
        Deck newDeck = new Deck(in);
        deckCollection.addDeck(newDeck);
        System.out.println("Your deck " + getUserDeck().getTitle() + " has been created and set as your active deck.");
        goToMenu();
    }

    // EFFECTS: view card titles in current deck
    private void deckCardsViewer() {
        cardViewer();
    }

    // EFFECTS: displays all decks
    private void decksViewer() {
        System.out.println("Active deck: " + deckCollection.getActiveDeck().getTitle());
        System.out.println("You have " + deckCollection.size() + " deck(s) in your collection.");
        System.out.println(deckCollection.viewDeckTitles());
        goToMenu();
    }

    // MODIFIES: this
    // EFFECTS: switches current deck to another deck
    private void deckSwitcher() {
        System.out.println("Press the number corresponding to the deck that you wish to switch to.");
        System.out.println(deckCollection.viewDeckTitles());
        int intIn;
        while (true) {
            String number = getInput();
            intIn = tryNumFormExcept(number, deckCollection.size());
            if (intIn != -1) {
                break;
            }
            System.out.println("Please enter integer within range.");
        }
        deckCollection.setActiveDeck(deckCollection.retrieveDeckWithIndex(intIn));
        System.out.println("Active deck switched to " + deckCollection.getActiveDeck().getTitle() + ".");
        goToMenu();
    }

    // MODIFIES: this
    // EFFECTS: deletes deck according to index in list
    private void deckDeleter() {
        if (deckCollection.size() == 1) {
            System.out.println("You only have 1 deck, cannot delete.\n");
            deckMenu();
        } else {
            System.out.println("Press the number corresponding to the deck that you wish to remove.");
            System.out.println(deckCollection.viewDeckTitles());
            int intIn;
            while (true) {
                String number = getInput();
                intIn = tryNumFormExcept(number, deckCollection.size());
                if (intIn != -1) {
                    break;
                }
                System.out.println("Please enter integer within range.");
            }
            deckCollection.removeDeck(intIn);
            System.out.println("Deck removed.");
            goToMenu();
        }
    }

    // EFFECTS: If deck empty, goes back to menu. Otherwise, tests user and displays score at the end.
    private void testMode() {
        if (getUserDeck().size() == 0) {
            System.out.println("Cannot start test. You have no cards in this deck. \n");
        } else {
            doTest();
            System.out.println("\nFinished! Your score: " + this.count + "/" + getUserDeck().size());
        }
        goToMenu();
    }

    // EFFECTS: goes to main menu once user presses 'm'
    private void goToMenu() {
        String in = "";
        while (!in.equalsIgnoreCase(MAIN_MENU)) {
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
        for (int i = 0; i < getUserDeck().size(); i++) {
            Flashcard card = getUserDeck().getCardFromIndex(i);
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


