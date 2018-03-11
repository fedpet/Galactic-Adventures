package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Models a generic EntityComponent.
 */
public interface EntityComponent {

    /**
     * Attaches the component to an {@link Entity}.
     * 
     * @param owner
     *            The {@link Entity}
     * @throws IllegalStateException
     *             Transplantating bodies is not supported
     */
    void attach(Entity owner) throws IllegalStateException;

    /**
     * 
     * @return The {@link Entity} to which its attached to.
     */
    Optional<Entity> getOwner();
}
