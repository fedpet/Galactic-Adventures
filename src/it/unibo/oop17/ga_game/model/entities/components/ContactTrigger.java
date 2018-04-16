package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.events.PasswordTriggeredEvent;

/**
 * A @TriggerComponent that activates on contact only once.
 */
public class ContactTrigger extends AbstractContactAwareComponent implements Trigger {

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
    public final boolean hasTriggered() {
        return triggered;
    }

    @Override
    public final void trigger() {
        if (!hasTriggered()) {
            triggered = true;
            post(new PasswordTriggeredEvent(getEntity(), password));
        }
    }

    @Override
    protected final void handleContact(final EntityBody other) {
        trigger();
    }
}
