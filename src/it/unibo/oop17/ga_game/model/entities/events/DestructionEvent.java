package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Generated when an @Entity is being removed from the game.
 * Every listener should unregister.
 */
public final class DestructionEvent extends AbstractEntityEvent {

    /**
     * Generated when an @Entity is being removed from the game.
     * 
     * @param source
     *            The entity being removed.
     */
    public DestructionEvent(final Entity source) {
        super(source);
    }
}
