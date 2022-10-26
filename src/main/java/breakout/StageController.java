package breakout;

import breakout.GameContainer.GameFrame;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;

import static breakout.StartGameFx.playMusic2;
////////////////////////////////////////////////////////////////////////////////
/**
 * Handles all the actions on the javafx stage and plays an effect when a button is clicked
 * @author Jirat Manpadungkit
 */
public class StageController{

    private boolean playing = true;

    URL resource3 = getClass().getResource("/SoundEffects/click.wav");
    Media music3 = new Media(resource3.toString());
    MediaPlayer playMusic3 = new MediaPlayer(music3);

    /**
     * Initialize the gameFrame
     * @param e ActionEvent
     */
    public void StartGame(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        playFX(playMusic3);
        stage.close();
        SwingUtilities.invokeLater(GameFrame::GameFrameStart);
    }

    /**
     * Quits the game
     */
    public void QuitGame ( )
    {
        playFX(playMusic3);
        Platform.exit();
    }

    /**
     * Plays sound effect when button is clicked
     */
    public void music ( ) {

        playFX(playMusic3);

        if (playing)
        {
            playMusic2.setMute(true);
            playing = false;

        } else {
            playMusic2.setMute(false);
            playing = true;
            }


        }

    /**
     * resets the music to stop then play again
     * @param mediaPlayer MediaPlayer
     */
    private void playFX (MediaPlayer mediaPlayer) {
        mediaPlayer.stop();
        mediaPlayer.play();
    }
}
