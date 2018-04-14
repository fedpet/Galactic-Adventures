package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Models a new triggered event.
 */
public class TriggeredEvent extends AbstractEntityEvent implements TriggerEvent {

    /**
     * 
     * @param source
     *            The @Entity source of this event.
     */
    public TriggeredEvent(final Entity source) {
        super(source);
    }

}
