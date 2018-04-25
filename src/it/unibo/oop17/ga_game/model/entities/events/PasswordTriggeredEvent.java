package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Models a new triggered event with password.
 */
public class PasswordTriggeredEvent extends TriggeredEvent {

    private final String password;

    /**
     * 
     * @param source
     *            The {@link Entity} source of this event.
     * @param password
     *            the relative password
     */
    public PasswordTriggeredEvent(final Entity source, final String password) {
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
