package it.unibo.oop17.ga_game.model.physics;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;

/**
 * Interface for collision detection.
 */
public interface CollisionListener {

    /**
     * Will be called by the physics engine at the start of a contact with an external @EntityBody.
     * 
     * @param other
     *            The other {@link EntityBody} object
     */
    void beginContact(EntityBody other);

    /**
     * Will be called by the physics engine at the end of a contact with an external @EntityBody.
     * 
     * @param other
     *            The other {@link EntityBody} object
     */
    void endContact(EntityBody other);
}
