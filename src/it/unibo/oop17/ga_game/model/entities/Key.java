package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.entities.components.PickupableBrain;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models a key entity that the player can collect; it can be used to make disappear the locks with the same type.
 */
public class Key extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 0.8);

    /**
     * 
     * @param bodyFactory
     *            the @BodyFactory.
     * @param position
     *            Its position (relative to its center).
     * @param type
     *            Its key type (RED, BLUE, YELLOW or GREEN).
     */
    public Key(final BodyFactory bodyFactory, final Point2D position,
            final KeyLockType type) {
        super(bodyFactory.createItem(position, SIZE));
        add(new PickupableBrain(inv -> inv.add(type)));
    }

    @Override
    public String toString() {
        return "Key";
    }

}