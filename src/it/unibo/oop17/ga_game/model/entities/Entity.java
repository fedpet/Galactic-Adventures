package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.Brain;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;

/**
 * Models a generic Entity in our game.
 */
public interface Entity {
    /**
     * @return the @EntityBody
     */
    EntityBody getBody();

    /**
     * The Brain controls the Entity.
     * 
     * @return The entity's @Brain
     */
    Brain getBrain();

    /**
     * Manages the entity's movement.
     * 
     * @return The entity's @MovementComponent
     */
    MovementComponent getMovement();

    /**
     * Used to synchronize the entities.
     * 
     * @param dt
     *            The time delta (in seconds) since the last call
     */
    void update(double dt);

    /**
     * Registers a listener for @EntityEvent.
     * 
     * @param listener
     *            the listener
     */
    void register(EntityEventListener listener);

    /**
     * Unregisters the listener for @EntityEvent.
     * 
     * @param listener
     *            the listener
     */
    void unregister(EntityEventListener listener);
}