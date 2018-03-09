package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

public class BasicEnemyBrain extends AbstractEntityComponent implements Brain {

    private Point2D desiredMovement = Point2D.ZERO;
    private static final float WALK_SPEED = 5f;
    private static final float JUMP_SPEED = 0f;

    @Override
    public void beginContact(EntityBody other) {

        /*
         * in teoria dovrebbe cambiare direzione se il rettangolo di other contiene:
         * un punto leggermente distante dal vertice più in basso a destra dell'owner
         * un punto leggermente distante dal vertice più in basso a sinistra dell'owner
         */

        if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.LEFT)) {
            execute(Command.MOVE_RIGHT);
        } else if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.RIGHT)) {
            execute(Command.MOVE_LEFT);
        }

    }

    @Override
    public void endContact(EntityBody other) {

    }

    @Override
    public void execute(Command command) {
        if (command.equals(Command.MOVE_LEFT)) {
            move(HorizontalDirection.LEFT);
        } else if (command.equals(Command.MOVE_RIGHT)) {
            move(HorizontalDirection.RIGHT);
        }
    }

    @Override
    public void update(final double dt) {
        Point2D movement = desiredMovement.subtract(getEntity().getBody().getLinearVelocity());
        if (!getEntity().getBody().isOnGround()) {
            movement = movement.multiply(0.5);
        }

        if (desiredMovement.getY() != 0) {
            desiredMovement = new Point2D(desiredMovement.getX(), 0);
        } else {
            movement = new Point2D(movement.getX(), 0);
        }

        if (!movement.equals(Point2D.ZERO)) {
            getEntity().getBody().applyImpulse(movement);
        }

    }

    public HorizontalDirection getMovingDirection() {
        if (desiredMovement.getX() < 0) {
            return HorizontalDirection.LEFT;
        }
        return HorizontalDirection.RIGHT;
    }

    private void move(final HorizontalDirection direction) {
        desiredMovement = new Point2D(direction == HorizontalDirection.RIGHT ? getWalkSpeed() : -getWalkSpeed(),
                desiredMovement.getY());
    }

    protected float getJumpSpeed() {
        return JUMP_SPEED;
    }

    protected float getWalkSpeed() {
        return WALK_SPEED;
    }

}
