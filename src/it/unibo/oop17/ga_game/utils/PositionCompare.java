package it.unibo.oop17.ga_game.utils;

import it.unibo.oop17.ga_game.model.EntityBody;
import javafx.geometry.Point2D;

public final class PositionCompare {



    private PositionCompare() {

    }

    public static boolean contact(EntityBody owner, EntityBody other) {
        return contactRight(owner, other) || contactLeft(owner, other) || contactUp(owner, other)
                || contactDown(owner, other);
    }

    // i derivati di contact considerano la collisione in alto, in centro e in basso di ogni lato

    public static boolean contactRight(final EntityBody owner, final EntityBody other) {
        final Point2D relativePosition = other.getPosition().subtract(owner.getPosition());
        return relativePosition.getX() > 0 && relativePosition.getY() < other.getDimension().getHeight() / 2;
    }

    public static boolean contactLeft(final EntityBody owner, final EntityBody other) {
        final Point2D relativePosition = other.getPosition().subtract(owner.getPosition());
        return relativePosition.getX() < 0 && relativePosition.getY() < other.getDimension().getHeight() / 2;
    }

    public static boolean contactUp(final EntityBody owner, final EntityBody other) {
        final Point2D relativePosition = other.getPosition().subtract(owner.getPosition());
        return relativePosition.getY() > 0 && relativePosition.getX() < other.getDimension().getWidth() / 2;
    }

    public static boolean contactDown(final EntityBody owner, final EntityBody other) {
        final Point2D relativePosition = other.getPosition().subtract(owner.getPosition());
        return relativePosition.getY() < 0 && relativePosition.getX() < other.getDimension().getWidth() / 2;
    }

}
