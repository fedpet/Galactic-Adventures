package it.unibo.oop17.ga_game.model;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.physics.CollisionListener;

/**
 * Models the {@link Entity} brain which controls it and decides what to do in case of collisions.
 */
public interface Brain extends EntityComponent, CollisionListener {

    /**
     * An EMPTY Brain does nothing.
     */
    Brain EMPTY = new Brain() {
        @Override
        public void beginContact(final EntityBody other) {
            // does nothing
        }

        @Override
        public void endContact(final EntityBody other) {
            // does nothing
        }

        @Override
        public void attach(final Entity owner) throws IllegalStateException {
            // TODO Auto-generated method stub

        }

        // TODO: metti a posto
        @Override
        public Optional<Entity> getOwner() {
            // TODO Auto-generated method stub
            return Optional.empty();
        }

        @Override
        public void update(final double dt) {
            // does nothing

        }
    };

    void update(double dt);
}
