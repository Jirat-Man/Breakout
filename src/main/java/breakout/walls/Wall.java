package breakout.walls;

import breakout.balls.*;
import breakout.bricks.*;
import breakout.paddles.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

////////////////////////////////////////////////////////////////////////////////
/**
 * This class makes the wall and initialized the ball and level
 * @author Jirat Manpadungkit-modified
 */
public class Wall {

    private final int CLAY = 1;// set to final because they won't change
    private final int STEEL = 2;
    private final int CEMENT = 3;
    private final int TITANIUM = 4;
    private final int DIAMOND = 5;
    private final int BOSSBRICK = 6;
    public TemplateBrick[] bricks;
    public BallTemplate ball;

    private final TemplateBrick[][] levels;

    private Paddle m_paddle;//
    private Point m_startPoint;//
    private Rectangle area;//
    private Random m_rnd;//
    private int m_levelCount;//
    private int m_level;//
    private int m_brickCount;//
    private int m_ballCount;//
    private boolean m_ballLost;//
    private int m_score;
    private double m_brickHgt;
    private double m_brickLen;

    private int m_PaddleWidth;
    private int m_PaddleHeight;



    BufferedImage SpriteSheetPNG =
            new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);
    {
        try {
            SpriteSheetPNG = ImageIO.read
                    (Objects.requireNonNull(getClass().
                            getResource("/Sprite" +
                                    "/SpriteSheet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns Brick height
     * @return brickHgt double
     */
    public double getM_brickHgt ( ) {
        return m_brickHgt;
    }

    /**
     * sets Brick height
     * @param m_brickHgt double
     */
    public void setM_brickHgt (double m_brickHgt) {
        this.m_brickHgt = m_brickHgt;
    }

    /**
     * return Brick length
     * @return brickLen double
     */
    public double getM_brickLen ( ) {
        return m_brickLen;
    }

    /**
     * set brick length
     * @param m_brickLen double
     */
    public void setM_brickLen (double m_brickLen) {
        this.m_brickLen = m_brickLen;
    }

    /**
     * return Paddle width
     * @return m_PaddleWidth int
     */
    public int getM_PaddleWidth ( ) {
        return m_PaddleWidth;
    }

    /**
     * This method sets the width of the paddle
     * @param m_PaddleWidth int
     */
    public void setM_PaddleWidth (int m_PaddleWidth) {
        this.m_PaddleWidth = m_PaddleWidth;
    }

    /**
     * This method returns the Height of the Paddle
     * @return int m_PaddleHeight
     */
    public int getM_PaddleHeight ( ) {
        return m_PaddleHeight;
    }

    /**
     * This method sets the height of the Paddle
     * @param m_PaddleHeight int
     */
    public void setM_PaddleHeight (int m_PaddleHeight) {
        this.m_PaddleHeight = m_PaddleHeight;
    }

    /**
     * returns paddle object
     * @return m_paddle Paddle
     */
    public Paddle getM_paddle ( ) {
        return this.m_paddle;
    }

    /**
     * Instantiate Paddle Object
     * @param ballPos Point
     * @param width int
     * @param height int
     * @param drawArea Rectangle
     * @param image BufferedImage
     */
    public void setM_paddle (Point ballPos, int width,
                             int height, Rectangle drawArea,
                             BufferedImage image) {
        this.m_paddle = new Paddle(ballPos, width, height, drawArea, image);
    }

    /**
     * return Start Point of brick
     * @return m_startPoint Point
     */
    public Point getM_startPoint ( ) {
        return this.m_startPoint;
    }

    /**
     * set the Start Point of Brick
     * @param m_startPoint Point
     */
    public void setM_startPoint (Point m_startPoint) {
        this.m_startPoint = new Point( m_startPoint);
    }

    /**
     * return area of Rectangle
     * @return area Rectangle
     */
    public Rectangle getArea ( ) {
        return area;
    }

    /**
     * set Area of Rectangle
     * @param area Rectangle
     */
    public void setArea(Rectangle area){
        this.area = area;
    }

    /**
     * return number of Levels
     * @return m_levelCount int
     */
    public int getM_levelCount ( ) {
        return m_levelCount;
    }

    /**
     * set the levelCount
     * @param m_levelCount int
     */
    public void setM_levelCount (int m_levelCount) {
        this.m_levelCount = m_levelCount;
    }

    /**
     * return a Random object
     * @return m_rnd Random
     */
    public Random getM_rnd ( ) {
        return m_rnd;
    }

    /**
     * Set the random object
     */
    public void setRnd(){
        this.m_rnd = new Random();
    }

    /**
     * return the current level
     * @return m_level int
     */
    public int getM_level(){
        return this.m_level;
    }

    /**
     * set level of the game
     * @param m_level int
     */
    public void setM_level (int m_level) {
        this.m_level = m_level;
    }

    /**
     * return brick count
     * @return m_brickCount int
     */
    public int getM_brickCount ( ) {
        return m_brickCount;
    }

    /**
     * set the number of bricks
     * @param m_brickCount int
     */
    public void setM_brickCount(int m_brickCount){
        this.m_brickCount = m_brickCount;
    }

    /**
     * decrement the number of bricks by 1
     */
    public void decM_brickCount() {
        setM_brickCount(getM_brickCount()-1);
    }

    /**
     * return number of balls left
     * @return int
     */
    public int getM_ballCount ( ) {
        return m_ballCount;
    }

    /**
     * set the number of ball
     * @param m_ballCount int
     */
    public void setM_ballCount (int m_ballCount) {
        this.m_ballCount = m_ballCount;
    }

    /**
     * decrement the number of ball by 1
     */
    private void decM_ballCount ( ) {
        setM_ballCount(getM_ballCount()-1);
    }

    /**
     * get the boolean m_ballLost
     * @return m_ballLost boolean
     */
    public boolean isM_ballLost ( ) {
        return m_ballLost;
    }

    /**
     * set the boolean m_ballLost
     * @param m_ballLost boolean
     */
    public void setM_ballLost (boolean m_ballLost) {
        this.m_ballLost = m_ballLost;
    }

    /**
     * get the score
     * @return m_score int
     */
    public int getM_score(){
        return this.m_score;
    }

    /**
     * set the score
     * @param m_score int
     */
    public void setM_Score (int m_score) {
        this.m_score = m_score;
    }

    /**
     * increment score by 5 everytime it hits a brick
     */
    public void incM_score(){
        setM_Score(getM_score()+5);
    }

    /**
     * Wall Constructor
     *
     * <p>creates a wall of bricks, set level to zero, make ball and make the paddle</p>
     * <p>set the speed of the ball and draw the paddle</p>
     *
     * @param drawArea            the area of the wall
     * @param brickCount          the amount bricks
     * @param lineCount           the number of layers of bricks
     * @param brickDimensionRatio the Dimension of each brick
     * @param ballPos             ball starting coordinate
     */
    public Wall (Rectangle drawArea, int brickCount,
                 int lineCount, double brickDimensionRatio,
                 Point ballPos) {

        setM_startPoint(ballPos);//set startPoint at ballPos

        levels = makeLevels(drawArea, brickCount,
                lineCount, brickDimensionRatio); //make levels
        setM_level(0); //set level to 0

        setM_ballCount(3); //set number of ball to 3
        setM_ballLost(false); //set ballLost to false
        setM_Score(0);//set score to 0

        setRnd();

        makeRubberBall(ballPos); //creates rubber ball and set to position ballPos
        int speedX, speedY;
        do {
            speedX = getM_rnd().nextInt(5) - 2;
        } while (speedX == 0);
        do {
            speedY = -getM_rnd().nextInt(3);
        } while (speedY == 0);

        ball.setM_speedX(speedX);
        ball.setM_speedY(speedY);
        setM_PaddleWidth(150);
        setM_PaddleHeight(20);
        setM_paddle((Point) ballPos.clone(), getM_PaddleWidth(),
                getM_PaddleHeight(), drawArea, SpriteSheetPNG);
        setArea(drawArea);
    }

    /**
     * Creates a wall of bricks that contains 1 type of bricks
     *
     * @param drawArea    the area of the wall
     * @param brickCnt    the amount bricks
     * @param lineCnt     the number of layers of bricks
     * @param brickSizeRatio   the Dimension of each brick
     * @param type      type of brick
     * @return TemplateBrick[]
     */
    public TemplateBrick[] makeSingleTypeLevel
    (Rectangle drawArea, int brickCnt,
     int lineCnt, double brickSizeRatio, int type) {

        // if brickCount is not divisible by line count,brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        setM_brickLen(drawArea.getWidth() / brickOnLine);
        setM_brickHgt(m_brickLen / brickSizeRatio);


        brickCnt += lineCnt / 2;

        TemplateBrick[] tmp = new TemplateBrick[brickCnt];

        Dimension brickSize = new Dimension((int) getM_brickLen(),
                (int) getM_brickHgt());

        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * getM_brickLen();
            x = (line % 2 == 0) ? x : (x - (getM_brickLen() / 2));
            double y = (line) * getM_brickHgt();
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, type);
        }

        for (double y = getM_brickHgt();
             i < tmp.length; i++, y += 2 * getM_brickHgt()) {

            double x = (brickOnLine * getM_brickLen())
                    - (getM_brickLen() / 2);

            p.setLocation(x, y);
            tmp[i] = new ClayBrick(p, brickSize, SpriteSheetPNG);
        }
        return tmp;

    }
    /**
     * Creates a wall of bricks that contains 2 type of bricks
     *
     * @param drawArea    the area of the wall
     * @param brickCnt    the amount bricks
     * @param lineCnt     the number of layers of bricks
     * @param brickSizeRatio   the Dimension of each brick
     * @param typeA       type of brick
     * @param typeB       type of brick
     * @return TemplateBrick[]
     */
    public TemplateBrick[] makeChessboardLevel
    (Rectangle drawArea, int brickCnt, int lineCnt,
     double brickSizeRatio, int typeA, int typeB) {

        // if brickCount is not divisible by line count, brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        setM_brickLen(drawArea.getWidth() / brickOnLine);
        setM_brickHgt(getM_brickLen() / brickSizeRatio);

        brickCnt += lineCnt / 2;

        TemplateBrick[] tmp = new TemplateBrick[brickCnt];

        Dimension brickSize = new Dimension((int) getM_brickLen(),
                (int) getM_brickHgt());
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * getM_brickLen();
            x = (line % 2 == 0) ? x : (x - (getM_brickLen() / 2));
            double y = (line) * getM_brickHgt();
            p.setLocation(x, y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 &&
                    posX > centerLeft && posX <= centerRight));

            tmp[i] = b ? makeBrick(p, brickSize, typeA) :
                    makeBrick(p, brickSize, typeB);
        }

        for (double y = getM_brickHgt(); i < tmp.length;
             i++, y += 2 * getM_brickHgt()) {

            double x = (brickOnLine * getM_brickLen())
                    - (getM_brickLen()/ 2);
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, typeA);
        }


        return tmp;
    }

    /**
     * This method creates a rubber ball
     *
     * @param ballPos Point2D object which stores starting coordinate of the ball
     */
    public void makeRubberBall (Point2D ballPos) {
        int radius = 15;
        ball = new RubberBall(ballPos, radius, SpriteSheetPNG);
    }

    /**
     * This method creates and return different levels of the game
     *
     * @param drawArea            the area of the wall
     * @param brickCount          the amount bricks
     * @param lineCount           the number of layers of bricks
     * @param brickDimensionRatio the Dimension of each brick
     * @return the level of the game
     */
    public TemplateBrick[][] makeLevels
    (Rectangle drawArea, int brickCount,
     int lineCount, double brickDimensionRatio) {

        setM_levelCount(9);
        TemplateBrick[][] tmp = new TemplateBrick[getM_levelCount()][];
        tmp[0] = makeSingleTypeLevel(drawArea, brickCount,
                lineCount, brickDimensionRatio, CLAY);
        tmp[1] = makeChessboardLevel(drawArea, brickCount,
                lineCount, brickDimensionRatio, CLAY, CEMENT);
        tmp[2] = makeChessboardLevel(drawArea, brickCount,
                lineCount, brickDimensionRatio, CLAY, STEEL);
        tmp[3] = makeChessboardLevel(drawArea, brickCount,
                lineCount, brickDimensionRatio, STEEL, CEMENT);
        tmp[4] = makeChessboardLevel(drawArea, brickCount,
                lineCount, brickDimensionRatio, STEEL, STEEL);
        tmp[5] = makeChessboardLevel(drawArea, brickCount,
                lineCount, brickDimensionRatio, STEEL, TITANIUM);
        tmp[6] = makeChessboardLevel(drawArea, brickCount,
                lineCount, brickDimensionRatio, TITANIUM, TITANIUM);
        tmp[7] = makeChessboardLevel(drawArea, brickCount,
                lineCount, brickDimensionRatio, DIAMOND, TITANIUM);
        tmp[8] = makeChessboardLevel(drawArea, brickCount,
                lineCount, brickDimensionRatio, DIAMOND, BOSSBRICK);
        return tmp;
    }

    /**
     * This method moved the paddle and the ball
     */
    public void move ( ) {
        getM_paddle().move();
        ball.move();
    }

    /**
     * This method find the impact of the ball and other components
     */
    public void findImpacts ( ) {
        if (getM_paddle().impact(ball)) {
            ball.reverseY();
            ball.hitPaddleSound();
        } else if (impactWall()) {
            // for efficiency reverse is done into method impactWall because for every brick program checks for horizontal and vertical impacts
            decM_brickCount();
        } else if (impactBorder()) {
            ball.reverseX();
            ball.hitPaddleSound();
        } else if (ball.getStartBallPos().getY() < getArea().getY()) {
            ball.reverseY();
        } else if (ball.getStartBallPos().getY() >
                getArea().getY() + getArea().getHeight()) {
            decM_ballCount();
            setM_ballLost(true);
        }
    }

    /**
     * This method returns true if the ball hit a wall
     * @return impactWall boolean
     */
    public boolean impactWall ( ) {
        for (TemplateBrick b : bricks) {
            switch (b.findImpact(ball)) {
                //Vertical Impact
                case TemplateBrick.UP_IMPACT -> {
                    ball.reverseY();
                    incM_score();
                    ball.hitWallSound();
                    return b.setImpact(ball.getDown());
                }
                case TemplateBrick.DOWN_IMPACT -> {
                    ball.reverseY();
                    incM_score();
                    ball.hitWallSound();
                    return b.setImpact(ball.getup());
                }
                //Horizontal Impact
                case TemplateBrick.LEFT_IMPACT -> {
                    ball.reverseX();
                    incM_score();
                    ball.hitWallSound();
                    return b.setImpact(ball.getRight());
                }
                case TemplateBrick.RIGHT_IMPACT -> {
                    ball.reverseX();
                    incM_score();
                    ball.hitWallSound();
                    return b.setImpact(ball.getLeft());
                }
            }
        }
        return false;
    }

    /**
     * This method return boolean true if the ball hits the border of the window
     * @return impactBorder boolean
     */
    public boolean impactBorder ( ) {
        Point2D p = ball.getStartBallPos();
        return ((p.getX() < getArea().getX()) ||
                (p.getX() > (getArea().getX() + getArea().getWidth())));
    }

    /**
     * This method resets the ball
     */
    public void ballReset ( ) {
        getM_paddle().moveTo(getM_startPoint());
        ball.moveTo(getM_startPoint());
        int speedX, speedY;
        do {
            speedX = getM_rnd().nextInt(5) - 2;
        } while (speedX == 0);
        do {
            speedY = -getM_rnd().nextInt(3);
        } while (speedY == 0);

        ball.setM_speedX(speedX);
        ball.setM_speedY(speedY);

        setM_ballLost(false);
    }

    /**
     * This method resets the wall
     */
    public void wallReset ( ) {
        for (TemplateBrick b : bricks)
            b.repair();
        setM_brickCount(bricks.length);
    }

    /**
     * This method returns true if ball count is 0
     * @return ballEnd boolean
     */
    public boolean ballEnd ( ) {
        return getM_ballCount() == 0;
    }

    /**
     * This method returns true if brick count is 0
     * @return isDone boolean
     */
    public boolean isDone ( ) {
        return getM_brickCount() == 0;
    }

    /**
     * This method moves player to the next level
     */
    public void nextLevel ( ) {
        bricks = levels[m_level++];
        setM_brickCount(bricks.length);
    }

    /**
     * This method returns true if there is a next level
     * @return hasLevel boolean
     */
    public boolean hasLevel ( ) {
        return getM_level() < levels.length;
    }

    /**
     * This method sets the ball count to 3
     */
    public void resetBallCount ( ) {
        setM_ballCount(3);
    }

    /**
     * This method make bricks
     * @param point Point2D
     * @param size Dimension
     * @param type int
     * @return TemplateBrick
     */
    public TemplateBrick makeBrick (Point point, Dimension size, int type) {
        return switch (type) {
            case CLAY -> new ClayBrick(point, size, SpriteSheetPNG);
            case STEEL -> new SteelBrick(point, size, SpriteSheetPNG);
            case CEMENT -> new CementBrick(point, size, SpriteSheetPNG);
            case TITANIUM -> new TitaniumBrick(point, size, SpriteSheetPNG);
            case DIAMOND -> new DiamondBrick(point, size, SpriteSheetPNG);
            case BOSSBRICK -> new BossBrick(point, size, SpriteSheetPNG);
            default -> throw new IllegalArgumentException
                    (String.format("Unknown Type:%d\n", type));
        };
    }

}
