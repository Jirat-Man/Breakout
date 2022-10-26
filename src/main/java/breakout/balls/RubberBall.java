package breakout.balls;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * This Class extends from BallTemplate and used to create ball
 *  @author Jirat Manpadungkit-modified
 */
public class RubberBall extends BallTemplate {

    /**
     * Creates a RubberBall
     * <p>
     * RubberBall will have startBallPos as the it's starting position and radius of radius
     *
     * </p>
     * @param startBallPos Point2D
     * @param radius int
     * @param ball_type BufferedImage
     */
    public RubberBall (Point2D startBallPos, int radius,
                       BufferedImage ball_type){

        super(startBallPos,radius,ball_type);
    }

    /**
     * This method returns an Ellipse
     * @param startBallPos Point2D
     * @param radiusA int
     * @param radiusB int
     * @return Shape
     */
    @Override
    protected Shape makeBall(Point2D startBallPos,
                             int radiusA, int radiusB) {

        double x = startBallPos.getX() - ((radiusA / 2));
        double y = startBallPos.getY() - ((radiusB / 2));

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}
