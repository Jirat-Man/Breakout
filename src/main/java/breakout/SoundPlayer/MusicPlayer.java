package breakout.SoundPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Play background music
 * @author Jirat Manpadungkit
 */
public class MusicPlayer {
    private Clip clip;

    /**MusicPlayer Constructor
     * Plays a .mp3/.wav on a 10000 times loop
     * <p>if no music is found,"No music found" is printed</p>
     * @param musicLocation String
     */
    public MusicPlayer(String musicLocation){
        try{
            File music = new File(musicLocation);
            if (music.exists())
            {
                AudioInputStream audio =
                        AudioSystem.getAudioInputStream(music);
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.loop(10000);
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
    public void stop(){
        clip.stop();
    }
}
