package it.unibo.oop17.ga_game.utils;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * Utility class for JavaFX stuff.
 */
public final class FXUtils {
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
}
