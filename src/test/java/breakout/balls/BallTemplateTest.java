package breakout.balls;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.*;

public class BallTemplateTest {
    Point point = new Point();
    BufferedImage SpriteSheetPNG = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);
    {
        try {
            SpriteSheetPNG = ImageIO.read(getClass().getResource("/Sprite/SpriteSheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    RubberBall ball = new RubberBall(point, 20, SpriteSheetPNG);

    @Test
    public void getM_speedX ( ) {
        ball.setM_speedX(20);
        assertEquals(20, ball.getM_speedX());
    }

    @Test
    public void setM_speedX ( ) {
        ball.setM_speedX(100);
        assertEquals(100, ball.getM_speedX());
        ball.setM_speedX(150);
        assertEquals(150, ball.getM_speedX());
    }

    @Test
    public void getM_speedY ( ) {
        ball.setM_speedY(20);
        assertEquals(20, ball.getM_speedY());
    }

    @Test
    public void setM_speedY ( ) {
        ball.setM_speedY(100);
        assertEquals(100, ball.getM_speedY());
        ball.setM_speedY(150);
        assertEquals(150, ball.getM_speedY());
    }

    @Test
    public void getStartBallPos ( ) {
        assertEquals(point, ball.getStartBallPos());
    }

    @Test
    public void setStartBallPos ( ) {
        Point tmp = new Point(80, 20);
        ball.setStartBallPos(tmp);
        assertEquals(tmp, ball.getStartBallPos());
    }

    @Test
    public void reverseX ( ) {
        ball.setM_speedX(30);
        assertEquals(30, ball.getM_speedX());
        ball.reverseX();
        assertEquals(-30, ball.getM_speedX());
        ball.reverseX();
        assertEquals(30, ball.getM_speedX());
    }

    @Test
    public void reverseY ( ) {
        ball.setM_speedY(30);
        assertEquals(30, ball.getM_speedY());
        ball.reverseY();
        assertEquals(-30, ball.getM_speedY());
        ball.reverseY();
        assertEquals(30, ball.getM_speedY());
    }

}