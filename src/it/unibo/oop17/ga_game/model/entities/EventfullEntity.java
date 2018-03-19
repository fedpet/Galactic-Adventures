package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.EntityComponent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;

public interface EventfullEntity extends Entity {
    void post(EntityEvent event);

    <C extends EntityComponent> void remove(Class<C> component);

    void remove(EntityComponent component);
}