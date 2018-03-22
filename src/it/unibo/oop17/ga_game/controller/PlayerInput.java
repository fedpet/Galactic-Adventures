package it.unibo.oop17.ga_game.controller;

import javafx.geometry.Point2D;

/**
 * Interface for generic player input.
 */
public interface PlayerInput {
    /**
     * Sets a listener for player input.
     * 
     * @param listener
     *            The listener
     */
    void onInput(Listener listener);

    /**
     * Listener interface for player input.
     */
    interface Listener {
        /**
         * Called when the player wants to move towards a position.
         * 
         * @param direction
         *            The direction vector.
         */
        void move(Point2D direction);
    }
}
