package it.unibo.oop17.ga_game.controller;

import javafx.geometry.Point2D;

/**
 * Interface for player input.
 */
public interface PlayerInputListener {
    /**
     * Called when the player wants to move towards a position.
     * 
     * @param direction
     *            The direction vector.
     */
    void move(Point2D direction);
}
