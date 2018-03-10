package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

public class FlyingEnemy extends AbstractEntity implements Entity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);
    private static final float SPEED = 7f;
    private Point2D desiredMovement = Point2D.ZERO;
    private double time = 0;
    private Side dir = Side.BOTTOM;

    public FlyingEnemy(final PhysicsEngine engine, final Point2D position) {
        super(engine.bodyFactory().createCreature(position, SIZE), new FlyingEnemyBrain(), new PropellerComponent(10));
        getBody().setGravityScale(0);
    }

    @Override
    public void update(final double dt) {
        // TODO Auto-generated method stub
        final Point2D movement = desiredMovement.subtract(getBody().getLinearVelocity());

        if (!movement.equals(Point2D.ZERO)) {
            getBody().applyImpulse(movement);
        }
        
        this.time += dt;
        if (this.time >= 1) {
            if (getMovingDirection().equals(Side.RIGHT)) {
                move(Side.BOTTOM);
            } else if (getMovingDirection().equals(Side.BOTTOM)) {
                move(Side.LEFT);
            } else if (getMovingDirection().equals(Side.LEFT)) {
                move(Side.TOP);
            } else if (getMovingDirection().equals(Side.TOP)) {
                move(Side.RIGHT);
            }
            this.time = 0;
        }
    }

    public Side getMovingDirection() {
        return this.dir;
    }

    public void move(final Side direction) {
        this.dir = direction;
        if (direction.equals(Side.LEFT)) {
            desiredMovement = new Point2D(-getSpeed(), 0);
        } else if (direction.equals(Side.RIGHT)) {
            desiredMovement = new Point2D(getSpeed(), 0);
        } else if (direction.equals(Side.TOP)) {
            desiredMovement = new Point2D(0, getSpeed());
        } else {
            desiredMovement = new Point2D(0, -getSpeed());
        }
    }

    protected float getSpeed() {
        return SPEED;
    }

}
