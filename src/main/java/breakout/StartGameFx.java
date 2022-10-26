package breakout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.util.Objects;
////////////////////////////////////////////////////////////////////////////////
/**
 * This class plays background music and creates a javafx stage
 * @author Jirat Manpadungkit
 */
public class StartGameFx extends Application {

    URL resource1 = getClass().getResource
            ("/SoundEffects/StartSound.wav");
    Media music1 = new Media(resource1.toString());
    MediaPlayer playMusic1 = new MediaPlayer(music1);

    static URL resource2 = StartGameFx.class.getResource
            ("/SoundEffects/RetroFuture.mp3");
    static Media music2 = new Media(resource2.toString());
    static MediaPlayer playMusic2 = new MediaPlayer(music2);

    /**
     * Initializes the stage
     * @param stage Stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        playMusic1.play();

        playMusic2.setCycleCount(MediaPlayer.INDEFINITE);
        playMusic2.play();

        Parent root;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("/fxml/StartScreen.fxml")));
        Scene scene = new Scene(root);

        stage.setTitle("Breakout");

        Image icon = new Image(Objects.requireNonNull(getClass().
                getResourceAsStream("/Images/BreakoutIcon.jpg")));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();

    }

    /**
     * launch the stage
     * @param args
     */
    public static void main (String[] args) {
        launch(args);
    }
}
