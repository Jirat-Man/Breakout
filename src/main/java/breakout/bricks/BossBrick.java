package breakout.bricks;

import breakout.Sprites.SpriteSheet;
import java.awt.*;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * This class extends from Template Brick and create brick that has strength of 5
 * @author Jirat Manpadungkit
 */
public class BossBrick extends TemplateBrick {

    private static final int BOSS_STRENGTH = 5;
    private final SpriteSheet BossBrick;

    /**
     * Constructor of BossBrick Strength == 5
     * @param point Point
     * @param size Dimension
     * @param image BufferedImage
     */
    public BossBrick (Point point, Dimension size, BufferedImage image) {
        super(point, size, image, BOSS_STRENGTH);
        BossBrick = new SpriteSheet(image);
    }

    /**
     * The overridden method returns the position of the Bricks
     * @param pos Point
     * @return Point
     */
    @Override
    protected Point getBrickPosition (Point pos) {
        return new Point(pos);
    }

    /**
     * This overridden method returns the Shape of the bricks
     * @param pos Point
     * @param size Dimension
     * @return Shape
     */
    @Override
    protected Shape makeBrickFace (Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    /**
     * This method updates the ball after impact
     */
    @Override
    protected void update ( ) {
    }

    /**
     * This method returns cropped image from the Sprite Sheet
     * <p>Changes images according to the strength remaining of the brick</p>
     * @return BufferedImage
     */
    @Override
    public BufferedImage getCroppedSheet ( ) {
        if (getM_strength() == 1) {
            return BossBrick.crop(96, 112, 80, 16);
        } else if (getM_strength() == 2) {
            return BossBrick.crop(480, 112, 80, 16);
        } else if (getM_strength() == 3) {
            return BossBrick.crop(96, 384, 80, 16);
        } else if (getM_strength() == 4) {
            return BossBrick.crop(480, 384, 80, 16);
        } else {
            return BossBrick.crop(960, 592, 80, 16);
        }
    }
}

