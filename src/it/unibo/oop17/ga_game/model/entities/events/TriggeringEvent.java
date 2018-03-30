package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Models a new triggering event.
 */
public class TriggeringEvent extends AbstractEntityEvent implements TriggerEvent {

    /**
     * 
     * @param source
     *            The @Entity source of this event.
     */
    public TriggeringEvent(final Entity source) {
        super(source);
    }


}
