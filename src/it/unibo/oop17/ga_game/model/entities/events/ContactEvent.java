package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;

/**
 * Event for entity collisions.
 */
public class ContactEvent extends AbstractEntityEvent {
    private final EntityBody other;

    /**
     * 
     * @param source
     *            Entity generating the event.
     * @param other
     *            The @EntityBody
     */
    public ContactEvent(final Entity source, final EntityBody other) {
        super(source);
        this.other = other;
    }

    /**
     * @return The @EntityBody of the other entity.
     */
    public final EntityBody getOtherBody() {
        return other;
    }
}
