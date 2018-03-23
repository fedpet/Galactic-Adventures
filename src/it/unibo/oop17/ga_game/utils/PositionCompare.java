package it.unibo.oop17.ga_game.utils;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * Helper class to compare and check positions and @Side.
 */
public final class PositionCompare {

    private PositionCompare() {

    }
    /**
     * Compares the position of an other body compared to the owner.
     */
    public static Side contact(final EntityBody owner, final EntityBody other) {
        final Point2D relativePosition = other.getPosition().subtract(owner.getPosition());
        if (relativePosition.getX() > 0 && relativePosition.getY() < other.getDimension().getHeight() / 2) {
            return Side.RIGHT;
        } else if (relativePosition.getX() < 0 && relativePosition.getY() < other.getDimension().getHeight() / 2) {
            return Side.LEFT;
        } else if (other.getPosition().getY() > owner.getPosition().getY()) {
            return Side.TOP;
        } else {
            return Side.BOTTOM;
        }
    }

    /**
     * Translates a position relative to the center of a box of the given size to a @Side.
     * 
     * @param size
     *            The size of the box
     * @param relativePosition
     *            The relative position
     * @return the relative @Side
     */
    public static Side relativeSide(final Dimension2D size, final Point2D relativePosition) {
        if (Math.abs(relativePosition.getX()) >= size.getWidth() / 2) {
            return relativePosition.getX() > 0 ? Side.RIGHT : Side.LEFT;
        } else {
            return relativePosition.getY() > 0 ? Side.TOP : Side.BOTTOM;
        }
    }

    /**
     * Checks if a given position, relative to the center of a box of the given size, is at the given @Side.
     * 
     * @param relativePosition
     *            Relative to the center of a box of the given size
     * @param side
     *            The @Side
     * @param size
     *            The size
     * @return true if the relativePosition is at the given @Side relatively to the size
     */
    public static boolean atSide(final Dimension2D size, final Point2D relativePosition, final Side side) {
        return relativeSide(size, relativePosition) == side;
    }

    /**
     * Helper method. Really just a shortcut for atSide(size, relativePosition, Side.BOTTOM)
     * 
     * @param size
     *            The size
     * @param relativePosition
     *            Relative to the center of a box of the given size
     * @return true if the relativePosition is at the bottom (relatively to the size)
     */
    public static boolean atBottom(final Dimension2D size, final Point2D relativePosition) {
        return atSide(size, relativePosition, Side.BOTTOM);
    }

    /**
     * Converts a @Side to a direction vector.
     * 
     * @param side
     *            The @Side
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

    /**
     * Checks if a given position, relative to the center of a box of the given size, is at LEFT or RIGHT side.
     * 
     * @param size
     *            The size
     * @param relativePosition
     *            Relative to the center of a box of the given size
     * @return true if the relativePosition is at the bottom (relatively to the size)
     */
    public static boolean atHorizontalSides(final Dimension2D size, final Point2D relativePosition) {
        return atSide(size, relativePosition, Side.LEFT) || atSide(size, relativePosition, Side.RIGHT);
    }
}
