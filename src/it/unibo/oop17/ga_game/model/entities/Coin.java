package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.PickupableBrain;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models a coin entity that the player can collect.
 */
public class Coin extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 0.8);

    /**
     * 
     * @param bodyFactory
     *            the @BodyFactory.
     * @param position
     *            Its position (relative to its center).
     * @param value
     *            The coin value
     */
    public Coin(final BodyFactory bodyFactory, final Point2D position,
            final int value) {
        super(bodyFactory.createItem(position, SIZE));
        add(new PickupableBrain(inv -> inv.addMoney(value)));
    }

    @Override
    public String toString() {
        return "Coin";
    }

}
