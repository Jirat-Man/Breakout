package breakout.debuggers;

import breakout.walls.Wall;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class extends JPanel, has sliders and buttons on it
 *  @author Jirat Manpadungkit-modified
 */
public class DebugPanel extends JPanel {

    private final Color DEF_BKG = Color.WHITE;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private Wall wall;

    /**
     * This method returns a Wall object
     * @return wall
     */
    public Wall getWall ( ) {
        return wall;
    }

    /**
     * This method sets the Wall object
     * @param wall Wall
     */
    public void setWall (Wall wall) {
        this.wall = wall;
    }

    /**
     * DebugPanel constructor
     * <p>Initialize the panel and add buttons and sliders to it</p>
     * @param wall Wall
     */
    public DebugPanel(Wall wall){
        setWall(wall);
        initialize();

        JButton skipLevel = makeButton("Skip Level", e ->
                getWall().nextLevel());
        JButton resetBalls = makeButton("Reset Balls", e ->
                getWall().resetBallCount());

        ballXSpeed = makeSlider(-4,4,e ->
                getWall().ball.setM_speedX(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e ->
                getWall().ball.setM_speedY(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }
    /**
     * This method initializes the JComponent to have the common JComponent functions
     * <p>For example: setTitle, setDefaultCloseOperation, setVisibility , setLayout and etc.</p>
     */
    public void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * This method returns a JButton object
     * @param title String
     * @param e ActionListener
     * @return out JButton
     */
    public JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    /**
     * This method returns a JSlider Object
     * @param min int
     * @param max int
     * @param e ChangeListener
     * @return out JSlider
     */
    public JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /**
     * This method sets the speed x and speed y of the ball to x and y respectively
     * @param x int
     * @param y int
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }


}
