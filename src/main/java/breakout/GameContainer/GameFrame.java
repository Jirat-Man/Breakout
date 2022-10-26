package breakout.GameContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import java.util.Objects;

import static java.lang.Math.abs;

/**
 * This class extends JFrame and contains the GameBoard
 *  @author Jirat Manpadungkit-modified
 */
public class GameFrame extends JFrame implements WindowFocusListener {
    private GameBoard gameBoard;
    private boolean m_gaming;
    JLabel background;

    /**
     * This method returns GameBoard object
     * @return GameBoard
     */
    public GameBoard getGameBoard ( ) {
        return gameBoard;
    }

    /**
     * This method instantiate a GameBoard object
     */
    public void setGameBoard () {
        this.gameBoard = new GameBoard(this);
    }

    /**
     * This method returns the boolean m_gaming
     * @return m_gaming boolean
     */
    public boolean getM_gaming ( ) {
        return m_gaming;
    }

    /**
     * This method sets the boolean m_gaming
     * @param m_gaming boolean
     */
    public void setM_gaming (boolean m_gaming) {
        this.m_gaming = m_gaming;
    }

    /**
     * This method creates a GameFrame object
     */
    public static void GameFrameStart ( ){
        new GameFrame();
    }

    /**
     * GameFrame Constructor
     * <p>set background image and add the game to the background</p>
     */
    public GameFrame(){
        setBackground();
        this.add(background);
        background.setLayout(new BorderLayout());
        setM_gaming(false);   //set gaming to false
        setGameBoard();//create GameBoard Object
        background.add(getGameBoard(),BorderLayout.CENTER);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * This method sets the background to an image
     */
    public void setBackground(){
        background = new JLabel();
        try {
            background = new JLabel
                    (new ImageIcon
                            (ImageIO.read
                                    (Objects.requireNonNull(getClass().
                    getResource("/Images/inGameBG.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method initializes the JComponent to have the common JComponent functions
     * <p>For example: setTitle, setDefaultCloseOperation, setVisibility , setLayout and etc.</p>
     */
    public void initialize(){
        String DEF_TITLE = "Breakout Clone   " +
                "space=start/pause  <-/-> =" +
                " move left/right  esc = menu  p = debug";
        this.setTitle(DEF_TITLE); //set title
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //terminate project when close
        this.pack(); // make window large enough to fit all the components
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * This method find the coordinate of the middle fo the screen
     */
    public void autoLocate(){ //position the window in the middle of screen
        Dimension size;
        size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (abs(size.width) - abs(this.getWidth())) / 2;
        int y = (abs(size.height) - abs(this.getHeight())) / 2;
        this.setLocation(x,y);
    }

    /**
     * This method sets m_gaming to true if window is in focus
     * @param windowEvent WindowEvent
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        setM_gaming(true);
    }

    /**
     * This method sets m_gaming to false when window is not in focus
     * @param windowEvent WindowEvent
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(getM_gaming())
            getGameBoard().onLostFocus();
    }
}
