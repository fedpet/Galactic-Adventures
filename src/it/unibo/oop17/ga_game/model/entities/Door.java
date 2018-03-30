package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.TriggerableDoorComponent;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models a door entity.
 */
public class Door extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);

    /**
     * 
     * @param bodyFactory
     *            the @BodyFactory.
     * @param position
     *            Its position (relative to its center).
     * @param open
     *            The initial state of the door, if open or closed.
     */
    public Door(final BodyFactory bodyFactory, final Point2D position, final String password, final boolean open) {
        super(bodyFactory.createItem(position, SIZE));
        add(new TriggerableDoorComponent(password, open));
    }

    @Override
    public String toString() {
        return "Door";
    }

}