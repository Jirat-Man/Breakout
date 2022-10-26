package breakout.bricks;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.*;

public class TemplateBrickTest {
    Point point = new Point(40, 100);
    Dimension dimension = new Dimension(70,20);
    BufferedImage SpriteSheetPNG = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);
    {
        try {
            SpriteSheetPNG = ImageIO.read(getClass().getResource("/Sprite/SpriteSheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ClayBrick clay = new ClayBrick(point, dimension, SpriteSheetPNG);
    CementBrick cement = new CementBrick(point, dimension, SpriteSheetPNG);
    SteelBrick steel = new SteelBrick(point, dimension, SpriteSheetPNG);
    TitaniumBrick titanium = new TitaniumBrick(point, dimension, SpriteSheetPNG);
    DiamondBrick diamond = new DiamondBrick(point, dimension, SpriteSheetPNG);
    BossBrick boss = new BossBrick(point, dimension, SpriteSheetPNG);

    @Test
    public void getM_fullStrength ( ) {
        assertEquals(1, clay.getM_fullStrength());
        assertEquals(2, cement.getM_fullStrength());
        assertEquals(1, steel.getM_fullStrength());
        assertEquals(3, titanium.getM_fullStrength());
        assertEquals(4, diamond.getM_fullStrength());
        assertEquals(5, boss.getM_fullStrength());
    }

    @Test
    public void setM_fullStrength ( ) {
        clay.setM_fullStrength(10);
        cement.setM_fullStrength(11);
        steel.setM_fullStrength(12);
        titanium.setM_fullStrength(13);
        diamond.setM_fullStrength(14);
        boss.setM_fullStrength(15);
        assertEquals(10, clay.getM_fullStrength());
        assertEquals(11, cement.getM_fullStrength());
        assertEquals(12, steel.getM_fullStrength());
        assertEquals(13, titanium.getM_fullStrength());
        assertEquals(14, diamond.getM_fullStrength());
        assertEquals(15, boss.getM_fullStrength());
    }

    @Test
    public void getM_strength ( ) {
        assertEquals(1, clay.getM_strength());
        assertEquals(2, cement.getM_strength());
        assertEquals(1, steel.getM_strength());
        assertEquals(3, titanium.getM_strength());
        assertEquals(4, diamond.getM_strength());
        assertEquals(5, boss.getM_strength());
    }

    @Test
    public void setM_strength ( ) {
        clay.setM_strength(10);
        cement.setM_strength(11);
        steel.setM_strength(12);
        titanium.setM_strength(13);
        diamond.setM_strength(14);
        boss.setM_strength(15);

        assertEquals(10, clay.getM_strength());
        assertEquals(11, cement.getM_strength());
        assertEquals(12, steel.getM_strength());
        assertEquals(13, titanium.getM_strength());
        assertEquals(14, diamond.getM_strength());
        assertEquals(15, boss.getM_strength());

    }

    @Test
    public void isM_broken ( ) {
        assertEquals(false, clay.isM_broken());
        assertEquals(false, cement.isM_broken());
        assertEquals(false, steel.isM_broken());
        assertEquals(false, titanium.isM_broken());
        assertEquals(false, diamond.isM_broken());
        assertEquals(false, boss.isM_broken());
    }

    @Test
    public void setM_broken ( ) {
        clay.setM_broken(true);
        cement.setM_broken(true);
        steel.setM_broken(true);
        titanium.setM_broken(true);
        diamond.setM_broken(true);
        boss.setM_broken(true);
        assertEquals(true, clay.isM_broken());
        assertEquals(true, cement.isM_broken());
        assertEquals(true, steel.isM_broken());
        assertEquals(true, titanium.isM_broken());
        assertEquals(true, diamond.isM_broken());
        assertEquals(true, boss.isM_broken());
    }



}