package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.physics.BodyContact;

/**
 * Generated at the beginning of a Contact.
 */
public final class BeginContactEvent extends ContactEvent {

    /**
     * 
     * @param source
     *            Entity generating the event.
     * @param contact
     *            The @BodyContact
     */
    public BeginContactEvent(final Entity source, final BodyContact contact) {
        super(source, contact);
    }
}
