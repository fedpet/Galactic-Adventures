package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.physics.BodyContact;
import javafx.geometry.Point2D;

/**
 * Event for entity collisions.
 */
public class ContactEvent extends AbstractEntityEvent {
    private final BodyContact contact;

    /**
     * 
     * @param source
     *            Entity generating the event.
     * @param contact
     *            The @BodyContact
     */
    public ContactEvent(final Entity source, final BodyContact contact) {
        super(source);
        this.contact = contact;
    }

    /**
     * @return The @EntityBody of the other entity.
     */
    public final EntityBody getOtherBody() {
        return contact.getOtherBody();
    }

    /**
     * @return The Point (relative to the @Entity) in the middle of the collision surface.
     */
    public final Point2D getPoint() {
        return contact.getPoint();
    }
}
