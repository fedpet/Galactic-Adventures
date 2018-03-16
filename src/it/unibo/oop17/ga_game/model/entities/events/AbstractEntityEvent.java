package it.unibo.oop17.ga_game.model.entities.events;

import java.util.Objects;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Base class for @EntityEvent.
 */
public abstract class AbstractEntityEvent implements EntityEvent {
    private final Entity source;

    /**
     * 
     * @param source
     *            The @Entity source of this event.
     */
    public AbstractEntityEvent(final Entity source) {
        this.source = Objects.requireNonNull(source);
    }

    @Override
    public final Entity getSource() {
        return source;
    }
}
