package it.unibo.oop17.ga_game.view;

import javafx.geometry.Point2D;

/**
 * Utility methods for the view.
 */
public final class ViewUtils {

    private static final int PIXELS_PER_METER = 50;

    private ViewUtils() {

    }

    /**
     * @param meters
     *          Meters to be converted.
     * @return meters in pixels.
     */
    public static double metersToPixels(final double meters) {
        return meters * PIXELS_PER_METER;
    }

    /**
     * @param pixels
     *          Pixels to be converted.
     * @return pixels in meters.
     */
    public static double pixelsToMeters(final int pixels) {
        return pixels / PIXELS_PER_METER;
    }

    /**
     * @param point
     *          Points to be converted in FX coordinates.
     * @return point in FX coordinates.
     */
    public static Point2D worldPointToFX(final Point2D point) {
        return invertY(point.multiply(PIXELS_PER_METER));
    }

    /**
     * @param point
     *          Point to be converted in world coordinates.
     * @return point in world coordinates.
     */
    public static Point2D jFXPointToWorld(final Point2D point) {
        return invertY(point.multiply(1 / PIXELS_PER_METER));
    }

    private static Point2D invertY(final Point2D p) {
        return new Point2D(p.getX(), -p.getY());
    }
}
