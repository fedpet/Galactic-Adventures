package it.unibo.oop17.ga_game.view;

import javafx.geometry.Point2D;

public final class ViewUtils {
    // TODO: eventually calculate this based on device monitor resolution
    private static final int PIXELS_PER_METER = 50;

    private ViewUtils() {

    }

    public static double metersToPixels(final double meters) {
        return meters * PIXELS_PER_METER;
    }

    public static double pixelsToMeters(final int pixels) {
        return pixels / PIXELS_PER_METER;
    }

    public static Point2D worldPointToFX(final Point2D point) {
        return invertY(point.multiply(PIXELS_PER_METER));
    }

    public static Point2D jFXPointToWorld(final Point2D point) {
        return invertY(point.multiply(1 / PIXELS_PER_METER));
    }

    private static Point2D invertY(final Point2D p) {
        return new Point2D(p.getX(), -p.getY());
    }
}
