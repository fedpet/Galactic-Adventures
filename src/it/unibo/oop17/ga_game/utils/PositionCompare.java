package it.unibo.oop17.ga_game.utils;

import java.util.stream.Stream;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

public final class PositionCompare {

    private PositionCompare() {

    }
    /**
     * Compares the position of an other body compared to the owner.
     */
    public static Side contact(EntityBody owner, EntityBody other) {
        final Point2D relativePosition = other.getPosition().subtract(owner.getPosition());
        if (relativePosition.getX() > 0 && relativePosition.getY() < other.getDimension().getHeight() / 2) {
            return Side.RIGHT;
        } else if (relativePosition.getX() < 0 && relativePosition.getY() < other.getDimension().getHeight() / 2) {
            return Side.LEFT;
        } else if (relativePosition.getY() > 0 && relativePosition.getX() < other.getDimension().getWidth() / 2) {
            return Side.TOP;
        } else {
            return Side.BOTTOM;
        }
    }

}
