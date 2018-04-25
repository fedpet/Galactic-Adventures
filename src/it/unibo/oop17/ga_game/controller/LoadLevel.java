package it.unibo.oop17.ga_game.controller;

import java.util.Set;

import it.unibo.oop17.ga_game.model.EntityStatistic;
import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.view.GameWorldView;

/**
 * Interface for controlling level loading.
 */
public interface LoadLevel {

    /**
     * @return the player.
     */
    Entity getPlayer();

    /**
     * @return the world model.
     */
    GameWorld getGameWorld();

    /**
     * @return the world view.
     */
    GameWorldView getGameWorldView();

    /**
     * @return the entities set.
     */
    Set<EntityController> getEntities();

    /**
     * @return the player tracker.
     */
    EntityStatistic getPlayerStatistic();

}
