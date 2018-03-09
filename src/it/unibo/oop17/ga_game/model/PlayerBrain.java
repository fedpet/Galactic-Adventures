package it.unibo.oop17.ga_game.model;

import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;

public class PlayerBrain extends AbstractEntityComponent implements Brain {

    private Point2D desiredMovement = Point2D.ZERO;
    private static final float WALK_SPEED = 10f;
    private static final float JUMP_SPEED = 20f;

    @Override
    public void beginContact(EntityBody other) {

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
        } else if (command.equals(Command.JUMP)) {
            jump();
        } else if (command.equals(Command.STOP_MOVING)) {
            stopWalking();
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

    @Override
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

    private void stopWalking() {
        desiredMovement = new Point2D(0, desiredMovement.getY());
    }

    private void jump() {
        if (getEntity().getBody().isOnGround()) {
            desiredMovement = new Point2D(desiredMovement.getX(), getJumpSpeed());
        }
    }

    protected float getJumpSpeed() {
        return JUMP_SPEED;
    }

    protected float getWalkSpeed() {
        return WALK_SPEED;
    }

}
