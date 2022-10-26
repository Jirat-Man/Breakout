package breakout.paddles;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.*;

public class PaddleTest {
    Point point = new Point(20,100);
    BufferedImage SpriteSheetPNG = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);
    {
        try {
            SpriteSheetPNG = ImageIO.read(getClass().getResource("/Sprite/SpriteSheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Rectangle rectangle = new Rectangle(100,20,100,20);
    Paddle paddle = new Paddle(point, 100,20, rectangle,SpriteSheetPNG );

    @Test
    public void getM_moveSpeed ( ) {
        assertEquals(10,paddle.getM_moveSpeed());
    }

    @Test
    public void setM_moveSpeed ( ) {
        paddle.setM_moveSpeed(80);
        assertEquals(80, paddle.getM_moveSpeed());
        paddle.setM_moveSpeed(1000);
        assertEquals(1000, paddle.getM_moveSpeed());
    }

    @Test
    public void setM_ballPoint ( ) {
        Point tmp = new Point(20,90);
        paddle.setM_ballPoint(tmp);
        assertEquals(tmp, paddle.getM_ballPoint());
    }

    @Test
    public void getM_ballPoint ( ) {
        assertEquals(point, paddle.getM_ballPoint());
    }

}