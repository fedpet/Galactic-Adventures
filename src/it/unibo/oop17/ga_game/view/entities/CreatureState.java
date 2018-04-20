package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.EntityState;

/**
 * Possible states of state for the @Entity objects.
 */
public enum CreatureState implements EntityState {
    /**
     * Possible states of state for the @Entity objects.
     */
    IDLE, WALKING, JUMPING, FLYING, DEAD, SUFFERING;

}
