package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.PickupableBrain;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models a coin entity that the player can collect.
 */
public class Coin extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(1, 1);

    /**
     * 
     * @param bodyBuilder
     *            the related {@link BodyBuilder} object.
     * @param position
     *            Its position (relative to its center).
     * @param value
     *            The coin value
     */
    public Coin(final BodyBuilder bodyBuilder, final Point2D position,
            final int value) {
        super(bodyBuilder
                .position(position)
                .size(SIZE)
                .solid(false)
                .moveable(false)
                .build());
        add(new PickupableBrain(inv -> inv.addMoney(value)));
    }

    @Override
    public final String toString() {
        return "Coin";
    }

}
