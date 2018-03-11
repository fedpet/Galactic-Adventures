package it.unibo.oop17.ga_game.model.physics;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import javafx.geometry.Point2D;

/**
 * Models a @BodyContact.
 */
public final class BodyContactImpl implements BodyContact {
    private final EntityBody other;
    private final Point2D point;

    /**
     * 
     * @param other
     *            The other @EntityBody
     * @param point
     *            The middle point.
     */
    public BodyContactImpl(final EntityBody other, final Point2D point) {
        this.other = other;
        this.point = point;
    }

    @Override
    public EntityBody getOtherBody() {
        return other;
    }

    @Override
    public Point2D getPoint() {
        return point;
    }

}
