package it.unibo.oop17.ga_game.controller;

import java.util.Set;

import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.entities.Player;
import it.unibo.oop17.ga_game.view.GameWorldView;

public interface LoadLevel {

    Player getPlayer();

    GameWorld getGameWorld();

    GameWorldView getGameWorldView();

    Set<EntityController> getEntities();

}
