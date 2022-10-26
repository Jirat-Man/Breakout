package breakout.Sprites;

import java.awt.image.BufferedImage;
////////////////////////////////////////////////////////////////////////////////
/**
 * This class crops a BufferedImage
 * @author Jirat Manpadungkit
 */
public class SpriteSheet {
    private BufferedImage sheet;

    /**
     * SpriteSheet constructor
     * load the BufferedImage into the local BufferedImage sheet
     * @param sheet BufferedImage
     */
    public SpriteSheet(BufferedImage sheet)
    {
        this.sheet = sheet;
    }

    /**
     * This method crops BufferedImage
     * <p>Coordinate and size can be specified</p>
     * @param x int
     * @param y int
     * @param width int
     * @param height int
     * @return BufferedImage
     */
    public BufferedImage crop (int x, int y, int width, int height)
    {
        return sheet.getSubimage(x,y,width,height);
    }
}
