package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.events.PasswordTriggeringEvent;

/**
 * A @TriggerComponent that activates on contact only once.
 */
public class ContactTrigger extends AbstractContactAwareComponent implements TriggerComponent {

    private boolean triggered;
    private final String password;

    /**
     * 
     * @param password
     *            The password linked to the related @PasswordTriggeringEvent .
     * @param triggered
     *            The initial state of the contact trigger (if triggered or not).
     */
    public ContactTrigger(final String password, final boolean triggered) {
        super();
        this.password = password;
        this.triggered = triggered;
    }

    @Override
    public boolean hasTriggered() {
        return triggered;
    }

    @Override
    protected void handleContact(final EntityBody other) {
        if (!hasTriggered()) {
            triggered = true;
            post(new PasswordTriggeringEvent(getEntity(), password));
        }
    }
}
