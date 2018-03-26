package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.events.TriggerEvent;

public class OneTimeTriggerable extends AbstractEntityComponent implements TriggerableComponent {

    private final String password;
    private boolean triggered;

    public OneTimeTriggerable(final String password, final boolean triggered) {
        super();
        this.password = password;
        this.triggered = triggered;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void trigger() {
        if (!isTriggered()) {
            post(new TriggerEvent(getEntity(), password));
            triggered = true;
        }
    }

    @Override
    public boolean isTriggered() {
        return triggered;
    }

}