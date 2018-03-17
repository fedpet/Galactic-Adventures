package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.FeetComponent;
import it.unibo.oop17.ga_game.model.entities.components.LinearLife;
import it.unibo.oop17.ga_game.model.entities.components.PlayerBrain;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models our player.
 */
public final class Player extends AbstractEntity {
    private static final int DEFAULT_LIFE = 5;
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);
    private static final double WALK_SPEED = 10;
    private static final double JUMP_SPEED = 22;

    /**
     * 
     * @param world
     *            The world in which to spawn the player.
     * @param position
     *            The position
     */
    public Player(final BodyFactory bodyFactory, final Point2D position) {
        super(bodyFactory.createCreature(position, SIZE), new PlayerBrain(),
                new FeetComponent(WALK_SPEED, JUMP_SPEED), new LinearLife(DEFAULT_LIFE));
    }

    @Override
    public String toString() {
        return "Player";
    }
}