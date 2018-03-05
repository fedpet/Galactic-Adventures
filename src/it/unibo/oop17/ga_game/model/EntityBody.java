package it.unibo.oop17.ga_game.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models our entities body.
 */
public interface EntityBody {
    /**
     * 
     * @return The position
     */
    Point2D getPosition();

    /**
     * 
     * @return The size of the body. Entity bodies are all approximated as rectangles.
     */
    Dimension2D getDimension();

    /**
     * 
     * @param velocity
     *            The velocity vector
     */
    void setLinearVelocity(Point2D velocity);

    /**
     * 
     * @return The velocity vector
     */
    Point2D getLinearVelocity();

    /**
     * 
     * @param impulse
     *            The impulse vector.
     */
    void applyImpulse(Point2D impulse);
}
