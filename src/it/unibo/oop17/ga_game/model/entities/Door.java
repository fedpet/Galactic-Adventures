package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.TriggerableDoorComponent;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models a door entity.
 */
public class Door extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);

    /**
     * 
     * @param bodyBuilder
     *            the @BodyBuilder.
     * @param position
     *            Its position (relative to its center).
     * @param open
     *            The initial state of the door, if open or closed.
     */
    public Door(final BodyBuilder bodyBuilder, final Point2D position, final String password, final boolean open) {
        super(bodyBuilder
                .position(position)
                .size(SIZE)
                .solid(false)
                .moveable(false)
                .build());
        add(new TriggerableDoorComponent(password, open));
    }

    @Override
    public String toString() {
        return "Door";
    }

}