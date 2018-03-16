package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.physics.CollisionListener;

/**
 * Models the {@link Entity} brain which controls it and decides what to do in case of collisions.
 */
public interface Brain extends EntityComponent, CollisionListener {

    /**
     * To make the brain think.
     * 
     * @param dt
     *            Time delta in seconds since the last call
     */
    void update(double dt);

    /**
     * Brains have personality.
     * 
     * @return the @Personality.
     */
    Personality getPersonality();
}
