package it.unibo.oop17.ga_game.model.entities.components;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models our entities body.
 */
public interface EntityBody extends EntityComponent {
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

    /**
     * The gravity scale will be multiplied by the gravity to decide how much it should influence this body.
     * 
     * @param scale
     *            The factor.
     */
    void setGravityScale(double scale);
    
    /**
     * 
     * @return true if the body's bottom part is touching a solid body.
     */
    boolean isOnGround();
}
