package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.TriggerableLevelEnd;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models a door entity.
 */
public class Door extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(1, 2);

    /**
     * 
     * @param bodyBuilder
     *            the related {@link BodyBuilder} object.
     * @param position
     *            Its position (relative to its center).
     * @param password
     *            The password associated to the door.
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
        add(new TriggerableLevelEnd(password, open));
    }

    @Override
    public final String toString() {
        return "Door";
    }

}
