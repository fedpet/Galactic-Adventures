package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.events.TriggeredEvent;

/**
 * A {@link Triggerable} component for triggerable entities that can be triggered only once.
 */
public class OneTimeTriggerable extends AbstractEntityComponent implements Triggerable {

    private final String password;
    private boolean triggered;

    /**
     * 
     * @param password
     *            A {@link Trigger} object can trigger this component if they have the same password.
     * @param triggered
     *            The initial state of the {@link OneTimeTriggerable} object (if triggered or not).
     */
    public OneTimeTriggerable(final String password, final boolean triggered) {
        super();
        this.password = password;
        this.triggered = triggered;
    }

    @Override
    public final String getPassword() {
        return password;
    }

    @Override
    public final void trigger() {
        if (!isTriggered()) {
            post(new TriggeredEvent(getEntity()));
            triggered = true;
        }
    }

    @Override
    public final boolean isTriggered() {
        return triggered;
    }

}
