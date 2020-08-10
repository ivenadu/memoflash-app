package ui.gui;

import model.DeckCollection;
import persistence.Load;
import persistence.Write;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.applet.*;
import java.io.InputStream;
import java.net.*;


public class SaveListener implements ActionListener {

    private DeckCollection deckCollection;

    public SaveListener(DeckCollection deckCollection) {
        this.deckCollection = deckCollection;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Write.save(deckCollection, "./data/myFile.json");
            SoundPlayer sound = new SoundPlayer();
            sound.playSound("./data/inbox.wav");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }


}
