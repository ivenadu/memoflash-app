package ui.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

//SOURCES REFERRED TO:
// http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
// https://stackoverflow.com/questions/13851445/music-player-file-not-found-exception
// Sound Effects:
// https://notificationsounds.com/message-tones/all-eyes-on-me-465
// https://notificationsounds.com/message-tones/filling-your-inbox-251
// https://notificationsounds.com/notification-sounds/light-562


public class SoundPlayer {
    public void playSound(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

