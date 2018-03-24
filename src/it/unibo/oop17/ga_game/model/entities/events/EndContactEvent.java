package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.physics.BodyContact;

/**
 * Generated at the end of a contact.
 */
public final class EndContactEvent extends ContactEvent {
    /**
     * 
     * @param source
     *            Entity generating the event.
     * @param contact
     *            The @BodyContact
     */
    public EndContactEvent(final Entity source, final BodyContact contact) {
        super(source, contact);
    }
}
