package it.unibo.oop17.ga_game.model.physics;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import javafx.geometry.Point2D;

/**
 * Carries informations of a collision.
 */
public interface BodyContact {
    /**
     * 
     * @return The other @EntityBody involved in this collision.
     */
    EntityBody getOtherBody();

    /**
     * 
     * @return An aproximate Point in the middle of the colliding sides.
     */
    Point2D getPoint();

}
