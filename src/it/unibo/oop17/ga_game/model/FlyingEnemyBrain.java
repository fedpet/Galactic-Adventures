package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

public class FlyingEnemyBrain extends AbstractEntityComponent implements Brain {

    private static final float SPEED = 7f;
    private Point2D desiredMovement = Point2D.ZERO;
    private double time = 0;
    private Side dir = Side.BOTTOM;

    @Override
    public void beginContact(EntityBody other) {

        /*
         * in fase di movimento, in caso di collisione dovrebbe invertire direzione
         */

            if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.LEFT)) {
            execute(Command.MOVE_RIGHT);
            } else if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.RIGHT)) {
            execute(Command.MOVE_LEFT);
            } else if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.TOP)) {
            execute(Command.MOVE_DOWN);
            } else if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.BOTTOM)) {
            execute(Command.MOVE_UP);
        }
    }

    @Override
    public void endContact(EntityBody other) {

    }

    @Override
    public void update(double dt) {
        // TODO Auto-generated method stub
        final Point2D movement = desiredMovement.subtract(getEntity().getBody().getLinearVelocity());

        if (!movement.equals(Point2D.ZERO)) {
            getEntity().getBody().applyImpulse(movement);
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

    private void move(final Side direction) {
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

    @Override
    public void execute(Command command) {
        if (command.equals(Command.MOVE_RIGHT)) {
            move(Side.RIGHT);
        } else if (command.equals(Command.MOVE_LEFT)) {
            move(Side.LEFT);
        } else if (command.equals(Command.MOVE_UP)) {
            move(Side.TOP);
        } else if (command.equals(Command.MOVE_DOWN)) {
            move(Side.BOTTOM);
        }
    }

}