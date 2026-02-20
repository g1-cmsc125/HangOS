package layout.design.designStartElements;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {

    public void playSystemSound(String fileName) {
        try {
            URL soundURL = getClass().getResource("/resources/sounds/" + fileName);
            
            if (soundURL == null) {
                System.err.println("Could not find sound file: " + fileName);
                return;
            }
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
