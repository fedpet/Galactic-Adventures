package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Models a new triggering event with password.
 */
public class PasswordTriggeringEvent extends TriggeringEvent {

    private final String password;

    /**
     * 
     * @param source
     *            The @Entity source of this event.
     * @param password
     *            the relative password
     */
    public PasswordTriggeringEvent(final Entity source, final String password) {
        super(source);
        this.password = password;
    }

    /**
     * 
     * @return The password associated to the event
     */
    public String getPassword() {
        return password;
    }
}
