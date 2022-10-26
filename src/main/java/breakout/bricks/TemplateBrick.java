package breakout.bricks;
import breakout.balls.*;
import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Abstract class of bricks, used to create bricks
 *  @author Jirat Manpadungkit-modified
 */
abstract public class TemplateBrick {

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private Point m_BrickPosition;

    private Shape m_brickFace;

    private int m_fullStrength;
    private int m_strength;

    private boolean m_broken;

    /**
     * This method returns the full strength of a brick
     * @return m_fullStrength int
     */
    public int getM_fullStrength ( ) {
        return this.m_fullStrength;
    }

    /**
     * This method sets the full strength of a brick
     * @param m_fullStrength int
     */
    public void setM_fullStrength (int m_fullStrength) {
        this.m_fullStrength = m_fullStrength;
    }

    /**
     * This method returns the strength of a brick
     * @return m_strength int
     */
    public int getM_strength ( ) {
        return this.m_strength;
    }

    /**
     * This method decrements the strength of a brick by 1
     */
    private void decM_strength ( ) {
        setM_strength(getM_strength()-1);
    }

    /**
     * This method sets the strength of a brick
     * @param m_strength int
     */
    public void setM_strength (int m_strength) {
        this.m_strength = m_strength;
    }

    /**
     * This method returns the boolean m_broken
     * <p>returns true if brick is broken</p>
     * <p>returns false if brick is not broken</p>
     * @return m_broken boolean
     */
    public final boolean isM_broken (){
        return this.m_broken;
    }

    /**
     * This method sets the boolean m_broken
     *
     * @param m_broken boolean
     */
    public void setM_broken (boolean m_broken) {
        this.m_broken = m_broken;
    }

    /**
     * This method calls method makeBrickFace
     * @param pos Point
     * @param size Dimension
     */
    public void setM_brickFace (Point pos, Dimension size) {
        this.m_brickFace = makeBrickFace(pos, size);
    }

    /**
     * This method sets the Brick Position
     * @param pos Point
     */
    public void setBrickPosition (Point pos) {
        this.m_BrickPosition = getBrickPosition(pos);
    }
    /**
     * Constructor of TemplateBrick
     * <p>Creates the brick, set the position of the bricks,</p>
     * <p>set the strength of the bricks</p>
     * @param pos
     * @param size
     * @param image
     * @param strength
     */
    public TemplateBrick (Point pos,
                          Dimension size, BufferedImage image,
                          int strength) {
        setM_broken(false);
        setBrickPosition(pos);
        setM_brickFace(pos, size);
        setM_strength(strength);
        setM_fullStrength(getM_strength());
    }
    /**
     * This abstract method returns the brick position
     * @param pos Point
     * @return Point
     */
    protected abstract Point getBrickPosition (Point pos);

    /**
     * This abstract method returns the Shape of the bricks
     * @param pos Point
     * @param size Dimension
     * @return Shape
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * This method returns the Brick Position
     * @return Point
     */
    public Point GetBrickPoint () {
        return this.m_BrickPosition;
    }

    /**
     * This method returns whether the ball hit the brick
     * <p>return True if ball hit the brick</p>
     * @param point Point2D
     * @return boolean
     */
    public  boolean setImpact(Point2D point){
        if(isM_broken())
            return false;
        impact();
        return isM_broken();
    }

    /**
     * This method returns integer bases on where the ball hit the bricks
     * @param b BallTemplate
     * @return int
     */
    public final int findImpact(BallTemplate b){
        if(isM_broken())
            return 0;
        int out  = 0;
        if(m_brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(m_brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(m_brickFace.contains(b.getup()))
            out = DOWN_IMPACT;
        else if(m_brickFace.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * This method repairs all broken bricks
     */
    public void repair() {
        setM_broken(false);
        setM_strength(getM_fullStrength());
    }

    /**
     * This method decrease strength of the bricks and set m_broken to true if the brick breaks
     */
    public void impact(){
        decM_strength();
        setM_broken(getM_strength()==0);
        update();
    }

    /**
     * This abstract method updates the bricks
     */
    protected abstract void update ( );

    /**
     * This abstract method returns a cropped BufferedImage
     * @return BufferedImage
     */
   public abstract BufferedImage getCroppedSheet ( );
}







