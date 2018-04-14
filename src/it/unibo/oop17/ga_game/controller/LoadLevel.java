package it.unibo.oop17.ga_game.controller;

import java.util.Set;

import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.view.GameWorldView;

public interface LoadLevel {

    Entity getPlayer();

    GameWorld getGameWorld();

    GameWorldView getGameWorldView();

    Set<EntityController> getEntities();

}
