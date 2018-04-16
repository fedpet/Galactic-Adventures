package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.Movement.State;

/**
 * Models a new movement event.
 */
public final class MovementEvent extends AbstractEntityEvent {
    private final State state;

    /**
     * 
     * @param source
     *            The @Entity source of this event.
     * @param state
     *            new state
     */
    public MovementEvent(final Entity source, final State state) {
        super(source);
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
