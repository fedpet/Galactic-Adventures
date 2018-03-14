package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;

/**
 * Models a new movement event.
 */
public final class MovementEvent implements EntityEvent {
    private final State state;

    /**
     * 
     * @param state
     *            new state
     */
    public MovementEvent(final State state) {
        this.state = state;
    }

    /**
     * 
     * @return The entity's new movement state.
     */
    public State getState() {
        return state;
    }
}
