package ui.gui;

import sun.applet.AppletAudioClip;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;

//SOURCES REFERRED TO:
// https://docs.oracle.com/javase/8/docs/api/java/applet/AudioClip.html
// Sound Effects:
// https://notificationsounds.com/message-tones/all-eyes-on-me-465
// https://notificationsounds.com/message-tones/filling-your-inbox-251
// https://notificationsounds.com/notification-sounds/light-562

/**
 * Represents an audio player with a String field for its file path
 */

public class SoundPlayer {

    // EFFECTS: plays audio from the file corresponding to the input path name
    public void playSound(String path) {
        try {
            File soundEffect = new File(path);
            URL sound = soundEffect.toURI().toURL();
            AudioClip audioClip = new AppletAudioClip(sound);
            audioClip.play();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

