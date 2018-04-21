package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Generic event for {@link Entity}.
 */
public interface EntityEvent {
    /**
     * 
     * @return The {@link Entity} which generated this event.
     */
    Entity getSource();
}
