package it.unibo.oop17.ga_game.model.entities.components;

import javafx.geometry.Point2D;

/**
 * Can't move.
 */
public final class DeadMovement extends AbstractMovementComponent {

    @Override
    public void move(final Point2D direction) {
        // does nothing
    }

    @Override
    public void update(final double dt) {

    }

    @Override
    public double getSpeed() {
        return 0;
    }

}
