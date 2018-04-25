package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.EntityState;

/**
 * Possible states of state for the entity objects.
 */
public enum CreatureState implements EntityState {
    /**
     * Possible states of state for the entity objects.
     */
    IDLE, WALKING, JUMPING, FLYING, DEAD, SUFFERING;

}
