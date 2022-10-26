package breakout.paddles;

import breakout.Sprites.SpriteSheet;
import breakout.balls.BallTemplate;

import java.awt.*;
import java.awt.image.BufferedImage;
////////////////////////////////////////////////////////////////////////////////
/**
 * This class creates a paddle
 *  @author Jirat Manpadungkit-modified
 */
public class Paddle {

    public static BufferedImage image;
    private final SpriteSheet sheet;

    private Rectangle m_paddleFace;
    private Point m_ballPoint;

    private int m_moveSpeed;
    private int m_moveAmount;
    private int m_min;
    private int m_max;

    /**
     * This method returns the speed fo the paddle
     * @return m_moveSpeed int
     */
    public int getM_moveSpeed ( ) {
        return m_moveSpeed;
    }

    /**
     * This method sets the speed of the paddle
     * @param m_moveSpeed int
     */
    public void setM_moveSpeed (int m_moveSpeed) {
        this.m_moveSpeed = m_moveSpeed;
    }

    /**
     * This method sets m_ballPoint
     * @param m_ballPoint Point
     */
    public void setM_ballPoint (Point m_ballPoint) {
        this.m_ballPoint = m_ballPoint;
    }

    /**
     * This method returns the Point of the Ball
     * @return m_ballPoint
     */
    public Point getM_ballPoint ( ) {
        return m_ballPoint;
    }

    /**
     * This method sets the Maximum distance the paddle can travel to
     * @param container Rectangle
     * @param width int
     */
    public void setM_max (Rectangle container, int width) {
        this.m_max = (int) (getM_min() + container.width - width/1.5);
    }

    /**
     * This method sets the move amount of the paddle
     * @param m_moveAmount int
     */
    public void setM_moveAmount (int m_moveAmount) {
        this.m_moveAmount = m_moveAmount;
    }

    /**
     * This method sets the paddle face of the paddle
     * @param width int
     * @param height int
     */
    public void setPaddleFace (int width, int height) {
        this.m_paddleFace = makeRectangle(width, height);
    }

    /**
     * This method returns m_min
     * @return m_min
     */
    public int getM_min ( ) {
        return m_min;
    }

    /**
     * This method sets the minimum distance the paddle can go
     * @param container Rectangle
     * @param width int
     */
    public void setM_min (Rectangle container, int width) {
        this.m_min = (int) (container.x + (width/2.5));
    }

    /**
     * This method returns a SpriteSheet object
     * @return SpriteSheet
     */
    public SpriteSheet getSheet ( ) {
        return sheet;
    }

    /**
     * Paddle Constructor
     * <p>Creates a paddle, set its max and min distance and set the paddle size</p>
     * @param m_ballPoint Point
     * @param width int
     * @param height int
     * @param container Rectangle
     * @param image BufferedImage
     */
    public Paddle (Point m_ballPoint,
                   int width, int height, Rectangle container,
                   BufferedImage image){
        setM_ballPoint(m_ballPoint);
        setM_moveAmount(0);
        setM_moveSpeed(10);
        Paddle.image = image;
        this.sheet = new SpriteSheet(image);
        setPaddleFace(width, height);
        setM_min(container,width);
        setM_max(container, width);
    }

    /**
     * This method creates the rectangle of the paddle and returns it
     * @param width int
     * @param height int
     * @return Rectangle
     */
    public Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(m_ballPoint.getX()-(width / 2)),
                (int) m_ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * This method returns the boolean True when the ball impact the paddle
     * @param b BallTemplate
     * @return boolean
     */
    public boolean impact(BallTemplate b){
        return m_paddleFace.contains(b.getStartBallPos()) &&
                            m_paddleFace.contains(b.getDown());
    }

    /**
     * This method sets the starting point of the paddle according to the start point of the ball
     */
    public void move(){
        double x = m_ballPoint.getX() + m_moveAmount;
        if(x < m_min || x > m_max)
            return;
        m_ballPoint.setLocation(x, m_ballPoint.getY());
        m_paddleFace.setLocation(m_ballPoint.x-
                (int) m_paddleFace.getWidth()/2, m_ballPoint.y);
    }

    /**
     * This method moves the paddle to the left
     */
    public void moveLeft(){
        m_moveAmount = -(getM_moveSpeed());
    }

    /**
     * This method moves the paddle to the right
     */
    public void moveRight (){
        m_moveAmount = getM_moveSpeed();
    }

    /**
     * This method stops the paddle
     */
    public void stop(){
        m_moveAmount = 0;
    }

    /**
     * This method returns the shape of the paddle
     * @return m_paddleFace Shape
     */
    public Shape getM_paddleFace (){
        return m_paddleFace;
    }

    /**
     * This method make the paddle move to the location of the ball
     * @param p Point
     */
    public void moveTo(Point p){
        m_ballPoint.setLocation(p);
        m_paddleFace.setLocation(m_ballPoint.x-
                (int) m_paddleFace.getWidth()/2, m_ballPoint.y);
    }
}
