package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.physics.BodyContact;

public final class BeginContactEvent extends ContactEvent {
    public BeginContactEvent(final Entity source, final BodyContact contact) {
        super(source, contact);
    }
}
