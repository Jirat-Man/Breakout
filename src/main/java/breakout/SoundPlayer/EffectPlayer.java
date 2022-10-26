package breakout.SoundPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Plays an effect sound
 * @author Jirat Manpadungkit
 */
public class EffectPlayer{
    /**
     * EffectPlayer Constructor
     * plays a .mp3/.wav file once
     * <p>If no music is found , "No music found" is printed</p>
     * @param musicLocation String
     */
    public EffectPlayer (String musicLocation) {
        try{
            File music = new File(musicLocation);
            if (music.exists())
            {
                AudioInputStream audio =
                        AudioSystem.getAudioInputStream(music);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.loop(0);
                clip.start();
            }
            else
            {
                System.out.println("No music found");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
