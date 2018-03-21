package it.unibo.oop17.ga_game.model.entities.components;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.BeginContactEvent;
import it.unibo.oop17.ga_game.model.entities.events.LevelFinishedEvent;

public class TriggerableDoorComponent extends OneTimeTriggerable {

    public TriggerableDoorComponent(final String password, final boolean triggered) {
        super(password, triggered);
    }

    @Subscribe
    public void beginContact(final BeginContactEvent contact) {
        if (isTriggered()) {
            post(new LevelFinishedEvent(getEntity()));
        }
    }

}
