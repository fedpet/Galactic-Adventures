package it.unibo.oop17.ga_game.model.physics;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Builder for @EntityBody.
 */
public interface BodyBuilder {
    /**
     * @param position
     *            The position of the center of the body
     * @return The BodyBuilder
     */
    BodyBuilder position(Point2D position);

    /**
     * @param size
     *            Size of the body bounding box.
     * @return The BodyBuilder
     */
    BodyBuilder size(Dimension2D size);

    /**
     * A body not subject to forces will not respond to forces nor impulses and won't be pushed by other bodies.
     * This includes the normal force so the body will be able to pass through others and it's especially
     * true for collisions with other bodies of the same type and / or unmoveable ones.
     * 
     * Defaults to true.
     * 
     * @param opt
     *            Set to false to disable forces for this body.
     * @return The BodyBuilder
     */
    BodyBuilder subjectToForces(boolean opt);

    /**
     * Defaults to true.
     * 
     * @param isSolid
     *            A non-solid body can pass through other bodies and vice-versa.
     * @return The BodyBuilder
     */
    BodyBuilder solid(boolean isSolid);

    /**
     * Defaults to true.
     * 
     * @param moveable
     *            If false the body won't move from its position.
     * @return The BodyBuilder
     */
    BodyBuilder moveable(boolean moveable);

    /**
     * @param friction
     *            The friction applied during contact with other bodies.
     * @return The BodyBuilder
     */
    BodyBuilder friction(double friction);

    /**
     * @return The built @EntityBody.
     */
    EntityBody build();
}
