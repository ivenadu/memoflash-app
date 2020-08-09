package ui.gui;

import persistence.Write;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class SaveListener extends AppGUI implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Write.save(this.getDeckCollection(),".data/myFile.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
