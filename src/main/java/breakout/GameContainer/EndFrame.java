package breakout.GameContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * extends JFrame, creates the JFrame at the end of the game
 * @author Jirat Manpadungkit
 */
public class EndFrame extends JFrame implements ActionListener {

    private JLabel background;
    private JFrame endFrame;
    private JButton againButton;
    private JButton quitButton;
    private JLabel Scores;
    private JTextArea HighScoreText;
    private JScrollPane scrollPane;
    private int m_currentScore;
    private String m_highScoreList = "";
    private int[] m_scoreNum;
    private String[] m_savedHighScore;

    /**
     * This method returns the current score of the game
     * @return m_currentScore int
     */
    public int getM_currentScore ( ) {
        return m_currentScore;
    }

    /**
     * This method sets the current score of the game
     * @param m_currentScore int
     */
    public void setM_currentScore (int m_currentScore) {
        this.m_currentScore = m_currentScore;
    }

    /**
     * EndFrame Constructor
     * <p>Creates a JFrame Object that appears at the end of the game</p>
     * <p>Contains buttons and a HighScore List</p>
     * @param score int
     * @param win boolean
     */
    public EndFrame (int score, boolean win) {
        setM_currentScore(score);
        recordScore();
        setBackground();
        initialize();
        endFrame.setLocationRelativeTo(null);
        getHighScore();
        showScore();
        showHighScore();
        getScroll();

        background.add(youWin(win));
        background.add(Scores);
        background.add(playAgainButton());
        background.add(quitButton());
        background.add(scrollPane);
    }

    /**
     * This method creates a JScrollPane that contains a JTextArea showing the high score list
     */
    public void getScroll ( ) {
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy
                (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getViewport().add(HighScoreText);
        scrollPane.getViewport().setViewPosition(new Point(0,0));
        scrollPane.setBounds(180, 105, 270, 250);
    }

    /**
     * This method record the score into a .dat file in the project
     */
    public void recordScore ( ) {
        String name = JOptionPane.showInputDialog("What is your name?");
        String record = name + ":" + getM_currentScore() + "\n";
        FileWriter myWriter;
        try {
            myWriter = new FileWriter
                    ("src/main/java/breakout/highScore.dat"
                            , true);
            myWriter.write(record);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * This method creates a JTextArea that contains the High Score list
     */
    private void showHighScore ( ) {
        HighScoreText = new JTextArea();
        HighScoreText.setText(m_highScoreList);
        HighScoreText.setEditable(false);
        HighScoreText.setFont
                (new Font("SANS SERIF", Font.BOLD, 25));
    }

    /**
     * This method creates a JLabel that shows the current score of the player on the screen
     */
    private void showScore ( ) {
        Scores = new JLabel("Your score : " + getM_currentScore());
        Scores.setForeground(Color.RED);
        Scores.setFont(new Font("SANS SERIF", Font.BOLD, 32));
        Scores.setOpaque(false);
        Scores.setBounds(193, 40, 300, 60);
    }

    /**
     * This method reads the .dat file in the Project for all the score
     * <p>it then arrange all the scores in descending order then put it in a String Array</p>
     */
    public void getHighScore ( ) {
        File m_highScoreFile = new File
                ("src/main/java/breakout/highScore.dat");
        String[] m_score = new String[1000];
        try {
            Scanner scan = new Scanner(m_highScoreFile);
            int i = 0;
            while (scan.hasNextLine()) {
                m_score[i++] = scan.nextLine();
            }
    } catch(FileNotFoundException e)
    {
        e.printStackTrace();
    }
        m_scoreNum = new int[1000];
        m_savedHighScore = m_score;
        for(int n = 0; n < m_score.length ; n++)
        {
            if(m_score[n]==null)
            {
                break;
            }
            String scoreString;
            scoreString =
                    (m_score[n].replaceAll
                            ("[^0-9]", ""));
            if (scoreString.equals("")) {
                int i = n++;
                scoreString =
                        (m_score[i].replaceAll
                                ("[^0-9]", ""));
            }
            m_scoreNum[n] = Integer.parseInt(scoreString);
        }
        findHighestScore();
        for(int i = 0; i < m_scoreNum.length; i++){
            if (m_savedHighScore[i]==null){
                break;
            }
            m_highScoreList =
                    m_highScoreList + m_savedHighScore[i] + "\n";
        }

}

    /**
     * This method arranges an Int array in descending order
     */
    private void findHighestScore ( ) {
        for(int n = 0; n < m_scoreNum.length; n++){
            for(int j = n+1; j < m_scoreNum.length; j++){
                if (m_scoreNum[n]< m_scoreNum[j]){
                    int temp = m_scoreNum[j];
                    m_scoreNum[j] = m_scoreNum[n];
                    m_scoreNum[n] = temp;

                    String tempString = m_savedHighScore[j];
                    m_savedHighScore[j] = m_savedHighScore[n];
                    m_savedHighScore[n] = tempString;
                }
            }
        }
    }

    /**
     * This method creates a label
     * <p>Shows YOU WIN! if you win and shows YOU LOSE! if you lose</p>
     * @param win boolean
     * @return Component
     */
    public Component youWin (boolean win) {
        JLabel winLose;
        if (win) {
            winLose = new JLabel("YOU WIN!");
        }
        else{
            winLose = new JLabel("YOU LOSE!");
        }
        winLose.setForeground(Color.RED);
        winLose.setFont(new Font("SANS SERIF", Font.BOLD, 36));
        winLose.setOpaque(false);
        winLose.setBounds(217, 2, 300, 60);
        return winLose;
    }

    /**
     * This method returns playAgain JButton
     * @return playAgainButton Component
     */
    public Component playAgainButton ( ) {
        againButton = new JButton("Play Again");
        againButton.setFont
                (new Font("SANS SERIF", Font.BOLD, 30));
        againButton.setBounds(50, 370, 190, 40);
        againButton.addActionListener(this);
        return againButton;
    }
    /**
     * This method returns quit JButton
     * @return quitButton Component
     */
    public Component quitButton(){
        quitButton = new JButton("Quit");
        quitButton.setFont
                (new Font("SANS SERIF", Font.BOLD, 30));
        quitButton.setBounds(370, 370, 190, 40);
        quitButton.addActionListener(this);
        return quitButton;
    }

    /**
     * This method sets the backGround of the JFrame to a picture
     */
    public void setBackground(){
        background = new JLabel();
        try {
            background = new JLabel
                    (new ImageIcon(ImageIO.read
                            (Objects.requireNonNull(getClass().
                    getResource
                            ("/Images/BackgroundImage.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method initializes the JComponent to have the common JComponent functions
     * <p>For example: setTitle, setDefaultCloseOperation, setVisibility , setLayout and etc.</p>
     */
    public void initialize(){
        endFrame = new JFrame();
        endFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        endFrame.setPreferredSize(new Dimension(610,470));
        endFrame.add(background);
        endFrame.setTitle("Breakout");
        endFrame.pack();
        endFrame.setVisible(true);
    }

    /**
     * This method handles the actions received from the user pressing the buttons
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == againButton) {
            endFrame.dispose();
            this.dispose();
            SwingUtilities.invokeLater(GameFrame::GameFrameStart);
        }
        if (e.getSource() == quitButton){
            System.exit(1);
        }
    }
}
