package breakout.debuggers;

import breakout.GameContainer.GameBoard;
import breakout.walls.Wall;
import breakout.balls.BallTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This class creates a JDialog that controls the levels and ball
 *  @author Jirat Manpadungkit-modified
 */
public class DebugConsole extends JDialog implements WindowListener{

    private JFrame owner;
    private DebugPanel debugPanel;
    private GameBoard gameBoard;
    private Wall wall;

    /**
     * This method returns the current JFrame
     * @return owner JFrame
     */
    @Override
    public JFrame getOwner ( ) {
        return owner;
    }

    /**
     * This method sets the JFrame = owner
     * @param owner JFrame
     */
    public void setOwner (JFrame owner) {
        this.owner = owner;
    }

    /**
     * This method returns the object debugPanel
     * @return debugPanel DebugPanel
     */
    public DebugPanel getDebugPanel ( ) {
        return debugPanel;
    }

    /**
     * This method instantiate DebugPanel Object
     * @param wall Wall
     */
    public void setDebugPanel (Wall wall) {
        this.debugPanel = new DebugPanel(wall);
    }

    /**
     * This method returns gameBoard object
     * @return gameBoard GameBoard
     */
    public GameBoard getGameBoard ( ) {
        return gameBoard;
    }

    /**
     * This method instantiate the gameBoard object
     * @param gameBoard GameBoard
     */
    public void setGameBoard (GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * This method returns the object wall
     * @return wall
     */
    public Wall getWall ( ) {
        return wall;
    }

    /**
     * This method sets the wall
     * @param wall Wall
     */
    public void setWall (Wall wall) {
        this.wall = wall;
    }

    /**
     * DebugConsole Constructor
     * <p>set the wall, the JFrame and the GameBoard</p>
     * <p>so the Panel can be added to the same Frame</p>
     * @param owner JFrame
     * @param wall Wall
     * @param gameBoard GameBoard
     */
    public DebugConsole(JFrame owner, Wall wall, GameBoard gameBoard){
        setWall(wall);
        setOwner(owner);
        setGameBoard(gameBoard);
        initialize();
        setDebugPanel(wall);
        this.add(getDebugPanel(),BorderLayout.CENTER);
        this.pack();
    }

    /**
     * This method initializes the JComponent to have the common JComponent functions
     * <p>For example: setTitle, setDefaultCloseOperation, setVisibility , setLayout and etc.</p>
     */
    public void initialize(){
        this.setModal(true);
        String TITLE = "Debug Console";
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    /**
     * This method sets the location of the DebugPanel
     */
    public void setLocation(){
        int x=((owner.getWidth()-this.getWidth())/2)+owner.getX();
        int y=((owner.getHeight()-this.getHeight())/2)+owner.getY();
        this.setLocation(x,y);
    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        getGameBoard().repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * This method get the speedX and speedY of the ball when the JPanel is activated
     * @param windowEvent WindowEvent
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        BallTemplate b = getWall().ball;
        getDebugPanel().setValues(b.getM_speedX(),b.getM_speedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
