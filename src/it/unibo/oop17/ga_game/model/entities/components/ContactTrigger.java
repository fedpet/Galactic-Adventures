package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.events.TriggerEvent;

public class ContactTrigger extends AbstractContactAwareComponent implements TriggerComponent {

    private boolean triggered;
    private final String password;

    public ContactTrigger(final String password, final boolean triggered) {
        super();
        this.password = password;
        this.triggered = triggered;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean hasTriggered() {
        return triggered;
    }

    @Override
    protected void handleContact(final EntityBody other) {
        if (!hasTriggered()) {
            triggered = true;
            post(new TriggerEvent(getEntity(), password));
        }
    }
}
