package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;

public interface EventfullEntity extends Entity {
    void post(EntityEvent event);
}
