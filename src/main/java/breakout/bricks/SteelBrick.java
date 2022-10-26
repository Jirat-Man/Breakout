package breakout.bricks;

import breakout.Sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * This class extends from Template Brick breaks at the probability of less than 0.4
 *  @author Jirat Manpadungkit-modified
 */
public class SteelBrick extends TemplateBrick {

    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;
    private final SpriteSheet SteelBrick;
    private final Random rnd;

    /**
     * SteelBrick Constructor(probability of breaking is less than 40%)
     * @param point Point
     * @param size Dimension
     * @param image BufferedImage
     */
    public SteelBrick (Point point, Dimension size, BufferedImage image){
        super(point,size,image,STEEL_STRENGTH);
        rnd = new Random();
        SteelBrick = new SpriteSheet(image);
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
     * This method sets the probability of breaking the Steel brick to less than 40%
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
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
        return SteelBrick.crop(0,480,80,16);
    }

}
