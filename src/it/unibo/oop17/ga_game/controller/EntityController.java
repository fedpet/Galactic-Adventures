package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;

/**
 * Translates view input to model input and updates the view.
 */
public interface EntityController extends EntityEventListener {

    /**
     * Used to synchronize the entity controller.
     */
    void update();

}
