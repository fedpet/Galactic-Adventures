package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.EntityStatistic;

/**
 * Interface for controlling game.
 */
public interface GameController {

    /**
     * Stops game.
     */
    void stop();

    /**
     * @return the player tracker.
     */
    EntityStatistic getPlayerStatistic();

}
