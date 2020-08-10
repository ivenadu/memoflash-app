package ui.gui;

import model.DeckCollection;
import persistence.Load;
import persistence.Write;

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
import java.net.*;
//Sources:
// http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
// (Audio:)
// https://notificationsounds.com/message-tones/all-eyes-on-me-465
// https://notificationsounds.com/message-tones/filling-your-inbox-251
// https://notificationsounds.com/notification-sounds/light-562

public class SaveListener implements ActionListener {

    private DeckCollection deckCollection;

    public SaveListener(DeckCollection deckCollection) {
        this.deckCollection = deckCollection;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Write.save(deckCollection, "./data/myFile.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
