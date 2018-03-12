package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.EventfullEntity;

/**
 * Models a generic EntityComponent.
 */
public interface EntityComponent {

    /**
     * Attaches the component to an @EventfullEntity.
     * 
     * @param owner
     *            The @EventfullEntity
     * @throws IllegalStateException
     *             Transplantating components is not supported
     */
    void attach(EventfullEntity owner) throws IllegalStateException;

    /**
     * 
     * @return The @Entity to which its attached to.
     */
    Optional<? extends Entity> getOwner();
}
