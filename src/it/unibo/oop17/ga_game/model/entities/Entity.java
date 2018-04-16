package it.unibo.oop17.ga_game.model.entities;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.components.EntityComponent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventSubscriber;

/**
 * Models a generic Entity in our game.
 */
public interface Entity {
    /**
     * @return the @EntityBody
     */
    EntityBody getBody();

    /**
     * Gets a component by its type.
     * Remember to work with interfaces and not concrete types!
     * 
     * @param <C>
     *            Type of components
     * @param component
     *            Interface of the desired component to get
     * @return the component
     */
    <C extends EntityComponent> Optional<C> get(Class<C> component);

    /**
     * Removes a component calling {@link EntityComponent#detach} on it.
     * 
     * @param component
     *            The component to remove.
     */
    void remove(EntityComponent component);

    /**
     * Adds a @EntityComponent to the entity.
     * 
     * @param component
     *            The component to add
     */
    void add(EntityComponent component);

    /**
     * Used to synchronize the entities.
     * 
     * @param dt
     *            The time delta (in seconds) since the last call
     */
    void update(double dt);

    /**
     * Destroy this entity.
     */
    void destroy();

    /**
     * Registers a listener for @EntityEvent.
     * 
     * @param listener
     *            the listener
     */
    void register(EntityEventSubscriber listener);

    /**
     * Unregisters the listener for @EntityEvent.
     * 
     * @param listener
     *            the listener
     */
    void unregister(EntityEventSubscriber listener);
}
