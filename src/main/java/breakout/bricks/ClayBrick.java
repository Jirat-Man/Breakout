package breakout.bricks;

import breakout.Sprites.SpriteSheet;

import java.awt.*;
import java.awt.Point;
import java.awt.image.BufferedImage;
/**
 * This class extends from Template Brick and create brick that has strength of 1
 *  @author Jirat Manpadungkit-modified
 */
public class ClayBrick extends TemplateBrick {

    private static final int CLAY_STRENGTH = 1;
    private final SpriteSheet clay;

    /**
     * ClayBrick Constructor Strength == 1
     * @param point Point
     * @param size Dimension
     * @param image BufferedImage
     */
    public ClayBrick (Point point, Dimension size, BufferedImage image){
        super(point,size,image,CLAY_STRENGTH);
        clay = new SpriteSheet(image);
    }
    /**
     * This overridden method returns Position (Point) of the Bricks
     * @param pos Point
     * @return Point
     */
    @Override
    protected Point getBrickPosition (Point pos) {
        return new Point(pos);
    }
    /**
     * This method returns Shape of the bricks
     * @param pos Point
     * @param size Dimension
     * @return Shape
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }
    /**
     * this method updates the brick after impact
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
        return clay.crop(96,112,80,16);
    }

}
