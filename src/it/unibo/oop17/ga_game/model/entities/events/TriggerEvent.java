package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

public class TriggerEvent extends AbstractEntityEvent {

    String password;

    public TriggerEvent(Entity source, String password) {
        super(source);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
