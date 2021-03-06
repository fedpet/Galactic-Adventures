package it.unibo.oop17.ga_game.model.entities.components;

import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;

/**
 * An {@link EntityComponent} object for movements.
 */
public interface Movement extends EntityComponent {
    /**
     * 
     * @param direction
     *            The direction as a vector.
     *            If ZERO then it will stop.
     */
    void move(Point2D direction);

    /**
     * In our game entities can either face RIGHT or LEFT.
     * 
     * @return The direction.
     */
    HorizontalDirection getFaceDirection();

    /**
     * 
     * @return Movement state.
     */
    State getState();

    /**
     * Updates the component and applies the movement.
     * 
     * @param dt
     *            Time delta in seconds since the last update call.
     */
    @Override
    void update(double dt);

    /**
     * Possible states of movement.
     */
    enum State implements EntityState {
        IDLE, WALKING, JUMPING, FLYING, FALLING;
    }
}
