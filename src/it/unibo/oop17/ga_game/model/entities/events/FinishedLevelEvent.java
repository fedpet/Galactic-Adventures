package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Models a new finished level event.
 */
public class FinishedLevelEvent extends AbstractEntityEvent {

    /**
     * Generated when a game level is completed.
     * 
     * @param source
     *            The entity that reaches the end of the level.
     */
    public FinishedLevelEvent(final Entity source) {
        super(source);
    }


}
