package it.unibo.oop17.ga_game.utils;

import it.unibo.oop17.ga_game.model.EntityBody;
import javafx.scene.shape.Rectangle;

public final class PositionCompare {

    private static double ownerX;
    private static double ownerY;
    private static double ownerW;
    private static double ownerH;
    private static double otherX;
    private static double otherY;
    private static double otherW;
    private static double otherH;

    private static Rectangle otherR;

    private PositionCompare() {

    }

    public static boolean contact(EntityBody owner, EntityBody other) {
        makeRectangles(owner, other);
        return otherR.intersects(ownerX, ownerY, ownerW, ownerH);
    }

    // i derivati di contact considerano la collisione in alto, in centro e in basso di ogni lato

    public static boolean contactRight(EntityBody owner, EntityBody other) {
        makeRectangles(owner, other);
        return otherR.contains(ownerX + ownerW + 0.1, ownerY - ownerH + 0.1)
                || otherR.contains(ownerX + ownerW + 0.1, ownerY)
                || otherR.contains(ownerX + ownerW + 0.1, ownerY - ownerH / 2);
    }

    public static boolean contactLeft(EntityBody owner, EntityBody other) {
        makeRectangles(owner, other);
        return otherR.contains(ownerX - 0.1, ownerY - ownerH + 0.1)
                || otherR.contains(ownerX - 0.1, ownerY)
                || otherR.contains(ownerX - 0.1, ownerY - ownerH / 2);
    }

    public static boolean contactUp(EntityBody owner, EntityBody other) {
        makeRectangles(owner, other);
        return otherR.contains(ownerX, ownerY + 0.1)
                || otherR.contains(ownerX + ownerW, ownerY + 0.1)
                || otherR.contains(ownerX + ownerW / 2, ownerY + 0.1);
    }

    public static boolean contactDown(EntityBody owner, EntityBody other) {
        makeRectangles(owner, other);
        return otherR.contains(ownerX, ownerY - ownerH - 0.1)
                || otherR.contains(ownerX + ownerW, ownerY - ownerH - 0.1)
                || otherR.contains(ownerX + ownerW / 2, ownerY - ownerH - 0.);
    }

    private static void makeRectangles(final EntityBody owner, final EntityBody other) {
        ownerX = owner.getPosition().getX()
                - owner.getDimension().getWidth() / 2;
        ownerY = owner.getPosition().getY()
                + owner.getDimension().getHeight() / 2;
        ownerW = owner.getDimension().getWidth();
        ownerH = owner.getDimension().getHeight();
        otherX = other.getPosition().getX() - other.getDimension().getWidth() / 2;
        otherY = other.getPosition().getY() + other.getDimension().getHeight() / 2;
        otherW = other.getDimension().getWidth();
        otherH = other.getDimension().getHeight();

        otherR = new Rectangle(otherX, otherY, otherW, otherH);
    }

}
