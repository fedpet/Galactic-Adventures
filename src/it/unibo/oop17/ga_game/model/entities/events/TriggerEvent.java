package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Models a new trigger event.
 */
public class TriggerEvent extends AbstractEntityEvent {

    /**
     * 
     * @param source
     *            The @Entity source of this event.
     */
    public TriggerEvent(final Entity source) {
        super(source);
    }


}
