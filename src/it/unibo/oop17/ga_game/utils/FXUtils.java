package it.unibo.oop17.ga_game.utils;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * Utility class for JavaFX stuff.
 */
public final class FXUtils {
    private static final Point2D X_AXIS = new Point2D(1, 0);

    private FXUtils() {

    }

    /**
     * 
     * @param rect
     *            The Rectangle
     * @return Its center
     */
    public static Point2D center(final Rectangle2D rect) {
        return new Point2D(rect.getMinX() + rect.getWidth() / 2, rect.getMinY() + rect.getHeight() / 2);
    }

    /**
     * 
     * @param rect
     *            The Rectangle
     * @return Its size
     */
    public static Dimension2D dimension(final Rectangle2D rect) {
        return new Dimension2D(rect.getWidth(), rect.getHeight());
    }

    /**
     * 
     * @param pt
     *            Point
     * @return Point with inverted y
     */
    public static Point2D invertY(final Point2D pt) {
        return new Point2D(pt.getX(), -pt.getY());
    }

    /**
     * 
     * @param vector
     *            vector
     * @return The angle in radians between the X axis and the vector.
     */
    public static double radians(final Point2D vector) {
        // https://stackoverflow.com/questions/17530169/get-angle-between-point-and-origin
        return -(Math.atan2(X_AXIS.getY(), X_AXIS.getX())
                - Math.atan2(vector.getY(), vector.getX()));
    }

    /**
     * Caps the absolute coordinates of the supplied vector to the given values mantaining the original vector
     * coordinates signs.
     * 
     * @param vector
     *            The vector
     * @param maxAbsX
     *            The maximum value for the X (it'll be treated as an unsigned value)
     * @param maxAbsY
     *            The maximum value for the Y (it'll be treated as an unsigned value)
     * @return The capped vector
     */
    public static Point2D absCap(final Point2D vector, final double maxAbsX, final double maxAbsY) {
        return new Point2D(absCap(vector.getX(), maxAbsX), absCap(vector.getY(), maxAbsY));
    }

    private static double absCap(final double value, final double cap) {
        return Math.abs(value) > Math.abs(cap) ? Math.copySign(Math.abs(cap), value) : value;
    }
}
