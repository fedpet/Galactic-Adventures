package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.Entity;
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
     * An EMPTY Brain does nothing.
     */
    Brain EMPTY = new AbstractBrain() {
        @Override
        public void update(final double dt) {
            // does nothing
        }

        @Override
        public void beginContact(final EntityBody other) {
            // does nothing
        }

        @Override
        public void endContact(final EntityBody other) {
            // does nothing
        }
    };
}
