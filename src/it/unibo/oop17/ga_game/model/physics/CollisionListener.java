package it.unibo.oop17.ga_game.model.physics;

import it.unibo.oop17.ga_game.model.EntityBody;

/**
 * Interface for collision detection.
 */
public interface CollisionListener {

    /**
     * Will be called by the physics engine at the start of a contact with an
     * external {@link EntityBody}.
     * 
     * @param other
     *            {@link EntityBody}
     */
    void beginContact(EntityBody other);

    /**
     * Will be called by the physics engine at the end of a contact with an external
     * {@link EntityBody}.
     * 
     * @param other
     *            {@link EntityBody}
     */
    void endContact(EntityBody other);
}
