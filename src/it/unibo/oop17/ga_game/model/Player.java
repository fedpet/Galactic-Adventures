package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models our player.
 */
public final class Player extends WalkingEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);
    private static final float WALK_SPEED = 10f;
    private static final float JUMP_SPEED = 20f;

    /**
     * 
     * @param world
     *            The world in which to spawn the player.
     * @param position
     *            The position
     */
    public Player(final PhysicsEngine engine, final Point2D position) {
        super(engine.bodyFactory().createGroundCreature(position, SIZE), Brain.EMPTY);
    }

    @Override
    protected float getJumpSpeed() {
        return JUMP_SPEED;
    }

    @Override
    protected float getWalkSpeed() {
        return WALK_SPEED;
    }

    @Override
    public String toString() {
        return "Player";
    }

}
