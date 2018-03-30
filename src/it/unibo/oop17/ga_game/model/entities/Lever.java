package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.ContactTrigger;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models a lever entity that can trigger specific events at player contact.
 */
public class Lever extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 0.8);

    /**
     * 
     * @param bodyFactory
     *            the @BodyFactory.
     * @param position
     *            Its position (relative to its center).
     * @param password
     *            The related password used to trigger linked events.
     * @param activated
     *            The initial state of the lever, if activated or not.
     */
    public Lever(final BodyFactory bodyFactory, final Point2D position, final String password,
            final boolean activated) {
        super(bodyFactory.createItem(position, SIZE));
        add(new ContactTrigger(password, activated));
    }

    @Override
    public String toString() {
        return "Lever";
    }

}