package ui.gui;

import javax.swing.*;
import javax.swing.event.*;

import model.Deck;
import model.DeckCollection;
import model.Flashcard;
import persistence.Load;
import persistence.Write;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the app GUI that lets user interact with active deck's card creation and deletion.
 */
//Sources cited: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html (ListDemo.java)

public class AppGUI extends JPanel implements ListSelectionListener {

    // Object[][] info;
    private ArrayList<String> cardsInfo = new ArrayList<>();
    private JList<Flashcard> flashcardJList;
    private DefaultListModel flashcardListModel;

    private DeckCollection deckCollection;

    {
        try {
            deckCollection = Load.loadFile("./data/myFile.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  final String[] columns = {"Name", "Question", "Answer"};

    private Deck activeDeck = deckCollection.getActiveDeck();

    //TODO: organize into their respective places

    private static final String removeString = "REMOVE CARD";
    private static final String addString = "ADD CARD";
    private static final String saveString = "SAVE";

    private JButton saveButton;
    private JButton addCardButton;
    private JButton removeButton;

    private JTextField nameField;
    private JTextField questionField;
    private JTextField answerField;

    SaveListener saveListener;
    AddCardListener addCardListener;
    RemoveListener removeListener;

    JScrollPane scrollPane;

    public AppGUI() {
        super(new BorderLayout());

        makeList();
        makeSaveButton();
        makeAddCardButton();
        makeRemoveButtonMaker();
        makeFields();
        makePanel();


    }

    public Deck getActiveDeck() {
        return activeDeck;
    }

    public DeckCollection getDeckCollection() {
        return deckCollection;
    }

    public DefaultListModel getFlashcardListModel() {
        return flashcardListModel;
    }

    public JList<Flashcard> getFlashcardJList() {
        return flashcardJList;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getQuestionField() {
        return questionField;
    }

    public JTextField getAnswerField() {
        return answerField;
    }

    public void makeList() {

        flashcardListModel = new DefaultListModel();
        addToListModel(convertToStringArray(activeDeck.getCardList()));
        flashcardJList = new JList(flashcardListModel);
        flashcardJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flashcardJList.setSelectedIndex(0);
        flashcardJList.addListSelectionListener(this);
        flashcardJList.setVisibleRowCount(20);
        scrollPane = new JScrollPane(flashcardJList);
    }

    public void addToListModel(ArrayList<String> strings) {
        for (String s : strings) {
            flashcardListModel.addElement(s);
        }
    }

//    public void makeTable() {
//        String[] tableColumns = {"Name", "Question", "Answer"};
//        JTable deckTableModel = new JTable(info, tableColumns);
//        deckTableModel.setPreferredScrollableViewportSize(new Dimension(800, 80));
//        deckTableModel.getColumnModel().getColumn(1).setPreferredWidth(300);
//        deckTableModel.setFillsViewportHeight(true);
//        scrollPane = new JScrollPane(deckTableModel);
//        add(scrollPane);
//        fillTable(activeDeck);
//
//
//    }

    public void fillTable(Deck cards) {
        for (int i = 0; i < cards.size(); i++) {


        }
    }
    //TODO: parts of it belong in the addCard class.

    private ArrayList<String> convertToStringArray(ArrayList<Flashcard> cards) {
        for (Flashcard f : cards) {
            String combo = combineString(f.getName(), f.getQuestion(), f.getAnswer());
            cardsInfo.add(combo);
        }
        return cardsInfo;
    }

    public String combineString(String name, String question, String answer) {
        String combined = "NAME: " + name + "   QUESTION: " + question + "   ANSWER: " + answer;
        return combined;
    }

    //TODO: belong in the Save class

    public void makeSaveButton() {

        saveButton = new JButton(saveString);
        saveListener = new SaveListener();
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(true);
    }

    //TODO: belong in the addCard class

    public void makeAddCardButton() {

        addCardButton = new JButton(addString);
        addCardListener = new AddCardListener();
        addCardButton.setActionCommand(addString);
        addCardButton.addActionListener(addCardListener);
        addCardButton.setEnabled(true);

    }

    //TODO: belong in removeCard class

    public void makeRemoveButtonMaker() {

        removeButton = new JButton(removeString);
        removeListener = new RemoveListener();
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeListener);
        removeButton.setEnabled(true); //TODO: hmm is it automatically enabled to true if I don't put this line?

    }
//TODO: these belong in the AddCard class

    public void makeFields() {

        nameField = new JTextField(5);
        questionField = new JTextField(15);
        answerField = new JTextField(5);

    }

    public void makePanel() {

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.LINE_AXIS));

        optionsPanel.add(saveButton);
        optionsPanel.add(Box.createHorizontalStrut(5));
        optionsPanel.add(new JSeparator(SwingConstants.VERTICAL));
        add(Box.createHorizontalStrut(5));
        optionsPanel.add(addCardButton);
        optionsPanel.add(removeButton);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(scrollPane, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (flashcardJList.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                flashcardJList.setEnabled(true);
            }
        }
    }

    //TODO: SEPARATE

    public class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Write.save(deckCollection, "./data/myFile.json");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    //TODO: SEPARATE
    public class AddCardListener implements ActionListener, DocumentListener {
        JButton button;
        JTextField fieldName;
        JTextField fieldQuestion;
        JTextField fieldAnswer;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkEmpty(nameField) || checkEmpty(questionField)
                    || checkEmpty(answerField)) {
                return;
            } else {
                activeDeck.addCard(new Flashcard(nameField.toString(),
                        getQuestionField().toString(), getAnswerField().toString()));
                flashcardListModel.addElement(combineString(getNameField().toString(),
                        getQuestionField().toString(), getAnswerField().toString()));
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {

        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    private boolean checkEmpty(JTextField field) {
        return field.toString().trim().isEmpty();
    }


    //TODO: SEPARATE

    public class RemoveListener implements ActionListener {
        JButton button = removeButton;

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = flashcardJList.getSelectedIndex();
            activeDeck.removeCardWithIndex(index);
            if (flashcardListModel.isEmpty()) {
                button.setEnabled(false);
            } else {
                if (index == getFlashcardListModel().size()) {
                    index--;
                } else {
                    flashcardListModel.remove(index);
                }
            }
            flashcardJList.setSelectedIndex(index);
            flashcardJList.ensureIndexIsVisible(index);
        }
    }
}
