package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;

/**
 * Generated at the beginning of a Contact.
 */
public final class BeginContactEvent extends ContactEvent {
    /**
     * 
     * @param source
     *            This body
     * @param other
     *            The other body
     */
    public BeginContactEvent(final Entity source, final EntityBody other) {
        super(source, other);
    }
}
