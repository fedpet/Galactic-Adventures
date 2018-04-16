package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;

/**
 * Internal entity interface used to let entity components generate events.
 */
public interface EntityEventPublisher extends Entity {
    /**
     * Publishes a @EntityEvent.
     * 
     * @param event
     *            The event to publish
     */
    void post(EntityEvent event);
}
