package breakout.GameContainer;

import breakout.SoundPlayer.EffectPlayer;
import breakout.SoundPlayer.MusicPlayer;
import breakout.walls.Wall;
import breakout.balls.BallTemplate;
import breakout.bricks.TemplateBrick;
import breakout.debuggers.DebugConsole;
import breakout.paddles.Paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;

////////////////////////////////////////////////////////////////////////////////
/**
 * This class displays all the graphics of the game
 *  @author Jirat Manpadungkit-modified
 */
public class GameBoard extends JComponent implements
        KeyListener, MouseListener,MouseMotionListener {

    private final Color MENU_COLOR = new Color(0,255,0);

    private final int DEF_WIDTH = 600;
    private final int DEF_HEIGHT = 450;

    private Timer gameTimer;

    private final Wall wall;

    private String message;

    private boolean m_showPauseMenu;

    private final Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int m_strLen;

    private final DebugConsole debugConsole;

    private final MusicPlayer music;

    /**
     * GameBoard Constructor
     * <p>Responsible for drawing all the graphics of the game and playing the Background Music</p>
     * @param owner JFrame
     */
    public GameBoard(JFrame owner){
        music = new
                MusicPlayer
                ("src/main/resources/SoundEffects/GetUp.wav");
        m_showPauseMenu = false;
        int TEXT_SIZE = 40;
        menuFont = new Font("Monospaced",Font.PLAIN, TEXT_SIZE); //set font

        this.initialize();
        message = "Press SPACE to start";

        wall = new Wall(new Rectangle(0,0,
                DEF_WIDTH,DEF_HEIGHT), 30,
                5,6/2,new Point(300,420));

        debugConsole = new DebugConsole(owner,wall,this);
        //initialize the first level
        wall.nextLevel();

        gameTimer = new Timer(10,e ->{
            wall.move();
            wall.findImpacts();
            message = String.format("Level:%d Bricks:%d Balls:%d Score:%d",
                    wall.getM_level(),wall.getM_brickCount(),
                    wall.getM_ballCount(), wall.getM_score()) ;

            if(wall.isM_ballLost()){//if ball lost
                if(wall.ballEnd()){//if no more ball
                    wall.wallReset();//reset wall
                    message = "Game over";
                    music.stop();
                    EffectPlayer lose =
                            new EffectPlayer
                                    ("src/main/resources/SoundEffects/lose.wav");
                    EndFrame end = new EndFrame(wall.getM_score(), false);
                    owner.dispose();
                }

                wall.ballReset(); //reset ball
                gameTimer.stop(); //stop game
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                    EffectPlayer lose = new EffectPlayer
                            ("src/main/resources/SoundEffects/nextLevel.wav");
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                    music.stop();
                    EffectPlayer lose = new EffectPlayer
                            ("src/main/resources/SoundEffects/win.wav");
                    EndFrame end = new EndFrame(wall.getM_score(), true);
                }
            }
            repaint();
        });
    }
    /**
     * This method initializes the JComponent to have the common JComponent functions
     * <p>For example: setTitle, setDefaultCloseOperation, setVisibility , setLayout and etc.</p>
     */
    public void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * This method draws the Ball, paddle, wall and menu
     * @param g Graphics
     */
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK); //set colour of text black
        g2d.drawString(message,210,225); //display message at coordinate x y
        drawBall(wall.ball,g2d); //draw ball
        for(TemplateBrick b : wall.bricks)
            if(!b.isM_broken())
                drawBrick(b,g2d);
        drawPaddle(wall.getM_paddle(),g2d); //draws the paddle
        if(m_showPauseMenu)
            drawMenu(g2d);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * This method draws the image of the bricks
     * @param brick TemplateBrick
     * @param g2d Graphics2D
     */
    public void drawBrick(TemplateBrick brick, Graphics2D g2d){
        g2d.drawImage(brick.getCroppedSheet(),
                (int) brick.GetBrickPoint().getX(),
                (int) brick.GetBrickPoint().getY(),
                (int)wall.getM_brickLen(),
                (int)wall.getM_brickHgt(),null);
    }

    /**
     * This method draws the image of the balls
     * @param ball ballTemplate
     * @param g2d Graphics2D
     */
    public void drawBall(BallTemplate ball, Graphics2D g2d){
        g2d.drawImage(ball.getSheet().crop(400,128,16,16),
                (int)ball.getStartBallPos().getX()- 5,
                (int)ball.getStartBallPos().getY() - 5,
                16,16,null);
    }

    /**
     * This method draws the image of the paddle
     * @param p Paddle
     * @param g2d Graphics2D
     */
    public void drawPaddle (Paddle p, Graphics2D g2d){
        g2d.drawImage(p.getSheet().crop(288, 144, 80, 16),
                (int) p.getM_ballPoint().getX()-
                        (p.getM_paddleFace().getBounds().width / 2),
                (int) p.getM_ballPoint().getY(),
                p.getM_paddleFace().getBounds().width,
                p.getM_paddleFace().getBounds().height,
                null);
    }

    /**
     * This method draws the menu
     * @param g2d Graphics2D
     */
    public void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * This method draws the transparent dark background of the menu
     * @param g2d Graphics2D
     */
    public void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac;
        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * This method draws the menu
     * @param g2d Graphics2D
     */
    public void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        String PAUSE = "Pause Menu";
        if(m_strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            m_strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - m_strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;

        String CONTINUE = "Continue";
        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect =
                    menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        String RESTART = "Restart";
        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        String EXIT = "Exit";
        g2d.drawString(EXIT,x,y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * This method handles the events of Key Pressed
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_LEFT){
            wall.getM_paddle().moveLeft();
        }
        if(code==KeyEvent.VK_RIGHT){
            wall.getM_paddle().moveRight();
        }
        if(code==KeyEvent.VK_SPACE){
            if(!m_showPauseMenu)
                if(gameTimer.isRunning())
                    gameTimer.stop();
                else
                    gameTimer.start();
        }
        if(code==KeyEvent.VK_ESCAPE){
            m_showPauseMenu = !m_showPauseMenu;
            repaint();
            gameTimer.stop();
        }
        if(code==KeyEvent.VK_P){
                debugConsole.setVisible(true);
        }
    }

    /**
     * This method makes the paddle stop when any key is released
     * @param keyEvent KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.getM_paddle().stop();
    }

    /**
     * This method handles the mouse Events
     * @param mouseEvent MouseEvents
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!m_showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            m_showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            wall.ballReset();
            wall.setM_ballCount(3);
            wall.wallReset();
            m_showPauseMenu = false;
            repaint();
        }
        else if(exitButtonRect.contains(p)){
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    }

    /**
     * This method handles the Mouse Moved Events
     * @param mouseEvent MouseEvent
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButtonRect != null && m_showPauseMenu) {
            if (exitButtonRect.contains(p)
                    || continueButtonRect.contains(p)
                    || restartButtonRect.contains(p))
                this.setCursor
                        (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * This method makes the timer stop when the window is not in focus
     */
    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }



}
