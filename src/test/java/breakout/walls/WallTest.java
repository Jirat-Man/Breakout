package breakout.walls;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class WallTest {
    Wall wall = new Wall(new Rectangle(0,0,
            60,20), 30,
            5,6/2,new Point(300,420));

    @Test
    public void getM_PaddleWidth ( ) {
        assertEquals(150, wall.getM_PaddleWidth());
    }

    @Test
    public void getM_PaddleHeight ( ) {
        assertEquals(20, wall.getM_PaddleHeight());
    }


    @Test
    public void getM_levelCount ( ) {
        assertEquals(9, wall.getM_levelCount());
    }

    @Test
    public void getM_level ( ) {
        assertEquals(0, wall.getM_level());
    }

    @Test
    public void getM_ballCount ( ) {
        assertEquals(3, wall.getM_ballCount());
    }

    @Test
    public void getM_score ( ) {
        assertEquals(0, wall.getM_score());
    }

    @Test
    public void nextLevel ( ) {
        wall.nextLevel();
        assertEquals(1,wall.getM_level());
        wall.nextLevel();
        assertEquals(2,wall.getM_level());
        wall.nextLevel();
        assertEquals(3,wall.getM_level());
    }

    @Test
    public void getArea ( ) {
        Rectangle tmp = new Rectangle(0,0,
                60,20);
        assertEquals(wall.getArea(), tmp);
    }

    @Test
    public void isM_ballLost ( ) {
        assertEquals(false, wall.isM_ballLost());
    }

    @Test
    public void setM_level ( ) {
        wall.setM_level(5);
        assertEquals(5, wall.getM_level());
        wall.setM_level(8);
        assertEquals(8, wall.getM_level());
    }

    @Test
    public void decM_brickCount ( ) {
        wall.setM_brickCount(30);
        assertEquals(30, wall.getM_brickCount());
        wall.decM_brickCount();
        assertEquals(29, wall.getM_brickCount());
        wall.decM_brickCount();
        wall.decM_brickCount();
        assertEquals(27, wall.getM_brickCount());
    }

    @Test
    public void setM_ballCount ( ) {
        wall.setM_ballCount(10);
        assertEquals(10, wall.getM_ballCount());
        wall.setM_ballCount(17);
        assertEquals(17, wall.getM_ballCount());
    }

    @Test
    public void setM_ballLost ( ) {
        wall.setM_ballLost(true);
        assertEquals(true, wall.isM_ballLost());
        wall.setM_ballLost(false);
        assertEquals(false, wall.isM_ballLost());
    }

    @Test
    public void setM_Score ( ) {
        wall.setM_Score(90);
        assertEquals(90, wall.getM_score());
        wall.setM_Score(9);
        assertEquals(9, wall.getM_score());
    }

    @Test
    public void incM_score ( ) {
        wall.setM_Score(90);
        assertEquals(90, wall.getM_score());
        wall.incM_score();
        assertEquals(95, wall.getM_score());
        wall.incM_score();
        wall.incM_score();
        assertEquals(105, wall.getM_score());
    }
}