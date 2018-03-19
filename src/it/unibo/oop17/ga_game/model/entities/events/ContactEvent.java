package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.physics.BodyContact;
import javafx.geometry.Point2D;

public class ContactEvent extends AbstractEntityEvent {
    private final BodyContact contact;

    public ContactEvent(final Entity source, final BodyContact contact) {
        super(source);
        this.contact = contact;
    }

    public final EntityBody getOtherBody() {
        return contact.getOtherBody();
    }

    public final Point2D getPoint() {
        return contact.getPoint();
    }
}
