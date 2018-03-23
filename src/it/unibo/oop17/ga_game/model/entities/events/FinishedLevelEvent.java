package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Models a new finished level event.
 */
public class FinishedLevelEvent extends AbstractEntityEvent {

    public FinishedLevelEvent(final Entity source) {
        super(source);
    }


}
