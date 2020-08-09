package ui.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import model.Deck;
import model.DeckCollection;
import model.Flashcard;
import persistence.Load;

import ui.gui.*;

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

    private static final String removeString = "REMOVE CARD";
    private static final String addString = "ADD CARD";
    private static final String saveString = "SAVE";

    private JButton saveButton;
    private JButton addCardButton;
    private JButton removeButton;

    private JTextField nameField;
    private JTextField questionArea;
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

    public JList<Flashcard> getFlashcardJList() {
        return flashcardJList;
    }

    public void makeList() {

        flashcardListModel = new DefaultListModel();
        flashcardJList = new JList(convertToStringArray(activeDeck.getCardList()).toArray());
        flashcardJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flashcardJList.setSelectedIndex(0);
        flashcardJList.addListSelectionListener(this);
        flashcardJList.setVisibleRowCount(20);
        scrollPane = new JScrollPane(flashcardJList);
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

    public ArrayList<String> convertToStringArray(ArrayList<Flashcard> cards) {
        for (Flashcard f : cards) {
            String combine = f.getName() + "   " + f.getQuestion() + "   " + f.getAnswer();
            cardsInfo.add(combine);
        }
        return cardsInfo;
    }


    public void makeSaveButton() {

        saveButton = new JButton(saveString);
        saveListener = new SaveListener(saveButton);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(false);
    }

    public void makeAddCardButton() {

        addCardButton = new JButton(addString);
        addCardButton.setActionCommand(addString);
        addCardButton.addActionListener(addCardListener);
        addCardButton.setEnabled(false);

    }

    public void makeRemoveButtonMaker() {

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeListener);
        removeButton.setEnabled(false);

    }

    public void makeFields() {

        nameField = new JTextField(5);
        questionArea = new JTextField(15);
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
}
