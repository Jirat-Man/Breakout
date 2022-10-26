package breakout.bricks;

import breakout.Sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * This class extends from Template Brick and create brick that has strength of 4
 * @author Jirat Manpadungkit
 */
public class DiamondBrick extends TemplateBrick {

    private static final int DIAMOND_STRENGTH = 4;
    private final SpriteSheet DiamondBrick;

    /**
     * DiamondBrick Constructor Strength == 4
     * @param point Point
     * @param size Dimension
     * @param image BufferedImage
     */
    public DiamondBrick (Point point, Dimension size, BufferedImage image){
        super(point,size,image,DIAMOND_STRENGTH);
        DiamondBrick = new SpriteSheet(image);
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
        if(getM_strength () == 1) {
            return DiamondBrick.crop(96,112,80,16);
        }
        else if(getM_strength () == 2){
            return DiamondBrick.crop(480, 112, 80, 16);
        }
        else if(getM_strength() == 3){
            return DiamondBrick.crop(96, 384, 80, 16);
        }
        else{
            return DiamondBrick.crop(480, 384, 80, 16);
        }
    }

}


