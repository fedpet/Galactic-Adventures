package it.unibo.oop17.ga_game.model.entities.components;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.BeginContactEvent;
import it.unibo.oop17.ga_game.model.entities.events.PasswordTriggerEvent;

public class ContactTrigger extends AbstractEntityComponent implements TriggerComponent {

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

    @Subscribe
    public void beginContact(final BeginContactEvent contact) {
        if (!hasTriggered()) {
            triggered = true;
            post(new PasswordTriggerEvent(getEntity(), password));
        }
    }

    @Override
    public boolean hasTriggered() {
        return triggered;
    }
}
