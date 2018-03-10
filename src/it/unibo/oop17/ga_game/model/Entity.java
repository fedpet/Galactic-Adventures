package it.unibo.oop17.ga_game.model;

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
}
