package it.unibo.oop17.ga_game.utils;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * Helper class to compare and check positions and side.
 */
public final class PositionCompare {

    private PositionCompare() {

    }

    /**
     * Returns the {@link Side} instance of a Body relative to the other.
     * 
     * @param owner
     *            Reference a {@link EntityBody} object.
     * @param other
     *            Reference a second {@link EntityBody} object.
     * @return The relative side
     */
    public static Side relativeSide(final EntityBody owner, final EntityBody other) {
        final Point2D relativePosition = other.getPosition().subtract(owner.getPosition());

        final double xDiff = Math.abs(
                Math.abs(relativePosition.getX()) - (owner.getDimension().getWidth() + other.getDimension().getWidth())
                        / 2);
        final double yDiff = Math.abs(Math.abs(relativePosition.getY())
                - (owner.getDimension().getHeight() + other.getDimension().getHeight())
                        / 2);

        if (xDiff < yDiff) {
            return relativePosition.getX() > 0 ? Side.RIGHT : Side.LEFT;
        } else {
            return relativePosition.getY() > 0 ? Side.TOP : Side.BOTTOM;
        }
    }

    /**
     * Converts a {@link Side} instance to a direction vector.
     * 
     * @param side
     *            The side
     * @return The direction vector
     */
    public static Point2D sideToDirection(final Side side) {
        switch (side) {
        case LEFT:
            return new Point2D(-1, 0);
        case RIGHT:
            return new Point2D(1, 0);
        case TOP:
            return new Point2D(0, 1);
        case BOTTOM:
            return new Point2D(0, -1);
        default:
            throw new IllegalArgumentException("Unknown side " + side);
        }
    }
}
