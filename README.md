# Personal Project Proposal

## MemoFlash Application


#### The App

Welcome! MemoFlash is an application that allows the user to create their own flashcards and decks to test themselves 
with how well they retain knowledge. This application is useful for students, but learners of any kind can benefit from it.

#### My Interest

I want to make an application that can help me throughout university, and an app that helps me study for courses that  
require memorization is useful.


#### User Stories:  

As a user, I want to be able to... 
- create flashcards
- view all the cards in my deck
- test myself with the flashcards (be quizzed, see answers to incorrect questions and total score)
- remove flashcards from active deck
- automatically save flashcards to deck before quitting
- retrieve the saved data when I re-enter the program

Additional Features: 
- create decks to organize flashcards (COMPLETE)
- remove decks I no longer need (COMPLETE)
- view all of my decks (COMPLETE)
- switch between decks (COMPLETE)

#### Instructions for Grader:
- You can *add* a Flashcard to the Deck by clicking the "ADD CARD" button after inputting text into the text fields 
corresponding to the Flashcard's name, question, and answer. Note that the Flashcards in the Deck must be distinct.
- You can *remove* a Flashcard from the Deck by selecting the card with your cursor and clicking the "REMOVE SELECTED 
CARD" button
- You can locate my audio component by clicking any of the buttons for a sound effect.
- You can save the state of my application by clicking the SAVE button.
- This application loads data from file automatically.

#### Phase 4: Task 2
The methods in the Flashcard class are robust. The checked exception, BlankStringException, is thrown for having 
blank string inputs when creating a Flashcard object.

#### Phase 4: Task 3
Before refactoring, the AppGUI class had poor cohesion: the methods clustered into different tasks. 
There were three buttons in the class, meaning three separate problems with cohesion. After refactoring, there are now 
three cohesive classes named "SaveButton" (for writing the data to file), "AddCardButton" (for adding a card to deck), 
and "RemoveCardButton" (for removing card from deck).
There is also tight coupling happening in the gui package classes, where fields from the AppGUI are passed 
into classes that use them for carrying out the tasks. The order in which the AppGUI calls methods in its 
constructor is important: if one method requiring the instantiation of a variable found in another method is called
before the required method, it would result in a NullPointerException. However, the fields being passed around are 
required for the execution of the separate methods. This is, I believe, the least complicated way for the program to work.
The classes take in different parameters, which makes introducing an interface unnecessarily complicated and messy. This
also would not solve the issue of tight coupling, which cannot be avoided due to the dependencies between classes.