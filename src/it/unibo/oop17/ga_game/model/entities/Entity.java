package it.unibo.oop17.ga_game.model.entities;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.components.EntityComponent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;

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
     * @param component
     *            type
     * @return the component
     */
    <C extends EntityComponent> Optional<C> get(Class<C> component);

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
    void register(EntityEventListener listener);

    /**
     * Unregisters the listener for @EntityEvent.
     * 
     * @param listener
     *            the listener
     */
    void unregister(EntityEventListener listener);
}
