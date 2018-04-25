package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.EntityEventPublisher;

/**
 * Models a generic entity component.
 */
public interface EntityComponent {

    /**
     * Attaches the component to an {@link EventfullEntity} object.
     * 
     * @param owner
     *            The {@link EventfullEntity} object owner
     * @throws IllegalStateException
     *             A component cannot be attached to multiple entities at the same time
     */
    void attach(EntityEventPublisher owner) throws IllegalStateException;

    /**
     * Detaches the component.
     */
    void detach();

    /**
     * Synchronizes the component.
     * 
     * @param dt
     *            Delta time since last call in seconds.
     */
    void update(double dt);

    /**
     * 
     * @return The entity to which its attached to.
     */
    Optional<? extends Entity> getOwner();
}
