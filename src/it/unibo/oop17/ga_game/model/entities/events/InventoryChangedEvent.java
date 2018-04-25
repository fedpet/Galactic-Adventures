package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Signals the change of an entity's {@link Inventory} object.
 */
public class InventoryChangedEvent extends AbstractEntityEvent {
    /**
     * @param source
     *            The entity generating the event
     */
    public InventoryChangedEvent(final Entity source) {
        super(source);
    }
}
