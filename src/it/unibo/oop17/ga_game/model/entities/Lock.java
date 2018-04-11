package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.entities.components.LockBrain;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models a lock entity that the player can make disappear, if it owns a key of the same type in the inventory.
 */
public class Lock extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(1, 1);

    /**
     * 
     * @param bodyFactory
     *            the @BodyFactory.
     * @param position
     *            Its position (relative to its center).
     * @param type
     *            Its lock type (RED, BLUE, YELLOW or GREEN).
     */
    public Lock(final BodyFactory bodyFactory, final Point2D position,
            final KeyLockType type) {
        super(bodyFactory.createStatic(position, SIZE));
        add(new LockBrain(type));
    }

    @Override
    public String toString() {
        return "Lock";
    }

}