package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.Brain;
import it.unibo.oop17.ga_game.model.entities.components.FeetComponent;
import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models our player.
 */
public final class Player extends AbstractEntity {
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
    public Player(final PhysicsEngine engine, final Point2D position) {
        super(engine.bodyFactory().createCreature(position, SIZE), Brain.EMPTY,
                new FeetComponent(WALK_SPEED, JUMP_SPEED));
    }

    @Override
    public String toString() {
        return "Player";
    }
}