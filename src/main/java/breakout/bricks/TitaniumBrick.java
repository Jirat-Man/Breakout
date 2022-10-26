package breakout.bricks;

import breakout.Sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * This class extends from Template Brick and create brick that has strength of 3
 * @author Jirat Manpadungkit
 */
public class TitaniumBrick extends TemplateBrick {

    private static final int TITANIUM_STRENGTH = 3;
    private final SpriteSheet TitaniumBrick;

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
     * TitaniumBrick Constructor(probability of breaking is less than 40%)
     * @param point Point
     * @param size Dimension
     * @param image BufferedImage
     */
    public TitaniumBrick (Point point, Dimension size, BufferedImage image){
        super(point,size,image,TITANIUM_STRENGTH);
        TitaniumBrick = new SpriteSheet(image);
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
     * <p>Changes images according to the strength of the bricks</p>
     * @return BufferedImage
     */
    @Override
    public BufferedImage getCroppedSheet ( ) {
        if(getM_strength () == 1) {
            return TitaniumBrick.crop(96,112,80,16);
        }
        else if(getM_strength () == 2){
            return TitaniumBrick.crop(480, 112, 80, 16);
        }
        else{
            return TitaniumBrick.crop(96, 384, 80, 16);
        }
    }
}