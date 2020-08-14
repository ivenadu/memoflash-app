package ui.gui;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * This class contains the main function for running the GUI of MemoFlashApp
 */

//SOURCES REFERRED TO: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html (ListDemo.java)

public class GuiMain {

    // EFFECTS: run program of GUI
    public static void main(String[] args) {
        invokeLater(() -> new MakerGUI());
    }
}
