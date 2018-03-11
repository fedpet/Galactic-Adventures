package it.unibo.oop17.ga_game.model.entities;


public interface EventfullEntity extends Entity {
    void post(EntityEvent event);
}
