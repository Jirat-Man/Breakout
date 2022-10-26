package breakout.balls;

import breakout.SoundPlayer.EffectPlayer;
import breakout.Sprites.SpriteSheet;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;

/**
 * This class is a template class for creating balls
 *  @author Jirat Manpadungkit-modified
 */
abstract public class BallTemplate {

    private SpriteSheet sheet;
    private Shape ballFace;
    private Point2D StartBallPos;
    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;
    private int m_speedX;
    private int m_speedY;

    /**
     * This method return the speed of the ball (X-Coordinate)
     * @return speed of ball
     */
    public int getM_speedX (){
        return this.m_speedX;
    }
    /**
     * This set the speed of the ball (X-Coordinate)
     * @param m_speedX
     */
    public void setM_speedX (int m_speedX){
        if (m_speedX >= 0){
            this.m_speedX = m_speedX;
        }
        else
            this.m_speedX = 5;
    }
    /**
     * This method returns the speed of the ball (Y-Coordinate)
     * @return m_speedY
     */
    public int getM_speedY (){
        return this.m_speedY;
    }
    /**
     * This set the speed of the ball (Y-Coordinate)
     * @param m_speedY
     */
    public void setM_speedY (int m_speedY){
        if (m_speedY >= 0) {
            this.m_speedY = m_speedY;
        }
        else
            this.m_speedY = -5;
    }

    /**
     * return ball start position
     * @return Point2D
     */
    public Point2D getStartBallPos (){
        return this.StartBallPos;
    }
    /**
     * This method set the starting position of the ball
     * @param startBallPos
     */
    public void setStartBallPos (Point2D startBallPos){
        this.StartBallPos = startBallPos;
    }
    /**
     * This method sets the Ball Face (ball).
     * @param startBallPos Point2D
     * @param radiusA int
     * @param radiusB int
     */
    public void setBallFace(Point2D startBallPos,int radiusA,int radiusB){
        this.ballFace = makeBall(startBallPos,radiusA,radiusB);
    }

    /**
     * This method returns a SpriteSheet object
     * @return sheet SpriteSheet
     */
    public SpriteSheet getSheet ( ) {
        return sheet;
    }

    /**
     * This method creates a SpriteSheet object with the BufferedImage as parameter
     * @param sheet BufferedImage
     */
    public void setSheet(BufferedImage sheet){
        this.sheet = new SpriteSheet(sheet);
    }

    /**
     * This method returns the Point2D of up
     * @return up Point2D
     */
    public Point2D getup(){
        return this.up;
    }

    /**
     * This method sets the Point2D of up
     * @param startBallPos Point2D
     * @param radius int
     */
    public void setUp(Point2D startBallPos, int radius)
    {
        up = new Point2D.Double();
        up.setLocation(startBallPos.getX(),
                startBallPos.getY()-(radius / 2));
    }
    /**
     * This method returns the Point2D of down
     * @return down Point2D
     */
    public Point2D getDown()
    {
        return this.down;
    }
    /**
     * This method sets the Point2D of down
     * @param startBallPos Point2D
     * @param radius int
     */
    public void setDown (Point2D startBallPos, int radius)
    {
        down = new Point2D.Double();
        down.setLocation(startBallPos.getX(),
                startBallPos.getY()+(radius/ 2));
    }

    /**
     * This method returns the Point2D of left
     * @return left Point2D
     */
    public Point2D getLeft(){
        return this.left;
    }

    /**
     * This method sets the Point2D of left
     * @param startBallPos Point2D
     * @param radiusA int
     */
    public void setLeft (Point2D startBallPos, int radiusA)
    {
        left = new Point2D.Double();
        left.setLocation(startBallPos.getX()-(radiusA /2),
                startBallPos.getY());
    }

    /**
     * This method returns the Point2D of right
     * @return right Point2D
     */
    public Point2D getRight(){
        return this.right;
    }

    /**
     * This method
     * @param startBallPos
     * @param radiusA
     */
    public void setRight (Point2D startBallPos, int radiusA)
    {
        right = new Point2D.Double();
        right.setLocation(startBallPos.getX()+(radiusA /2),
                startBallPos.getY());
    }

    /**
     * BallTemplate Constructor
     * <p>
     *     This constructor sets the ball position at startBallPos,
     *     create ball face, create SpriteSheet with image, and
     *     setup, setDown, setLeft and setRight
     * </p>
     * @param startBallPos Point2D
     * @param radius int
     * @param image BufferedImage
     */

    public BallTemplate (Point2D startBallPos, int radius,
                         BufferedImage image){
        setStartBallPos(startBallPos);
        setBallFace(startBallPos,radius,radius);
        setSheet(image);
        setUp(startBallPos, radius);
        setDown(startBallPos, radius);
        setLeft(startBallPos, radius);
        setRight(startBallPos, radius);
    }

    /**
     * This abstract method returns a ball
     * @param ballPos Point2D
     * @param radiusA int
     * @param radiusB int
     * @return Shape
     */
    protected abstract Shape makeBall(Point2D ballPos,int radiusA,int radiusB);

    /**
     * This method is in charge of moving the ball
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        StartBallPos.setLocation((StartBallPos.getX() + m_speedX),
                (StartBallPos.getY() + m_speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((StartBallPos.getX() -(w / 2)),
                (StartBallPos.getY() - (h / 2)),w,h);
        setPoints(w,h);

        ballFace = tmp;
    }

    /**
     * This method reverses the ball X-axis speed
     */
    public void reverseX(){
        m_speedX *= -1;
    }
    /**
     * This method reverses the ball Y-axis speed
     */
    public void reverseY(){
        m_speedY *= -1;
    }

    /**
     *This method moves the ball to the middle of the screen
     * @param p Point
     */
    public void moveTo(Point p){
        StartBallPos.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((StartBallPos.getX() -(w / 2)),
                (StartBallPos.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * This method set the Point of up down left and right
     * @param width double
     * @param height double
     */
    public void setPoints(double width,double height){
        up.setLocation(StartBallPos.getX(),
                StartBallPos.getY()-(height / 2));
        down.setLocation(StartBallPos.getX(),
                StartBallPos.getY()+(height / 2));

        left.setLocation(StartBallPos.getX()-(width / 2),
                StartBallPos.getY());
        right.setLocation(StartBallPos.getX()+(width / 2),
                StartBallPos.getY());
    }

    /**
     * creates an EffectPlayer object which plays hitWall.wav
     */
    public void hitWallSound (){
        EffectPlayer hitWall = new EffectPlayer
                ("src/main/resources/SoundEffects/hitWall.wav");
    }
    /**
     * creates an EffectPlayer object which plays hitPaddle.wav
     */
    public void hitPaddleSound (){
        EffectPlayer hitPaddle = new EffectPlayer
                ("src/main/resources/SoundEffects/hitPaddle.wav");
    }

}
