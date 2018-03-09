package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models our player.
 */
public final class Player extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);

    /**
     * 
     * @param world
     *            The world in which to spawn the player.
     * @param position
     *            The position
     */
    public Player(final PhysicsEngine engine, final Point2D position) {
        super(engine.bodyFactory().createCreature(position, SIZE, 1), new PlayerBrain());
    }

    @Override
    public String toString() {
        return "Player";
    }

    @Override
    public void update(double dt) {
        getBrain().update(dt);
    }

}