package it.unibo.oop17.ga_game.model.entities.components;

import javafx.geometry.Point2D;

/**
 * A weapon can be used toward a direction and hopefully deal damage.
 */
public interface Weapon extends EntityComponent {
    /**
     * @param direction
     *            Direction relative to the user.
     */
    void use(Point2D direction);
}
