package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.utils.FXUtils;
import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Point2D;
import javafx.geometry.Side;


/**
 * Feet for ground creatures unable to fly!
 */
public final class FeetComponent extends AbstractMovementComponent {
    private static final double AIR_FRICTION_FACTOR = 0.07;
    private final double walkingSpeed, jumpingSpeed;

    /**
     * 
     * @param walkingSpeed
     *            Horizontal speed while on ground
     * @param jumpingSpeed
     *            Vertical jump speed
     */
    public FeetComponent(final double walkingSpeed, final double jumpingSpeed) {
        super();
        this.walkingSpeed = walkingSpeed;
        this.jumpingSpeed = jumpingSpeed;
    }

    @Override
    public void move(final Point2D directionVector) {
        final double hf = directionVector.getX() > 0 ? 1 : directionVector.getX() < 0 ? -1 : 0;
        final double vf = directionVector.getY() > 0 ? 1 : 0; // can't move toward bottom
        setDesiredMovement(new Point2D(walkingSpeed * hf, jumpingSpeed * vf));
        updateState();
    }

    @Override
    protected void handleContact(final EntityBody other) {
        updateState();
    }

    @Override
    protected Point2D computeMovement(final double dt) {
        Point2D movement = getDesiredMovement().subtract(getEntity().getBody().getLinearVelocity());
        if (movement.getY() < 0) {
            // we cannot go under ground nor try to avoid such vertical impulses.
            movement = new Point2D(movement.getX(), 0);
        }
        movement = FXUtils.absCap(movement, walkingSpeed, jumpingSpeed);
        if (!isOnGround()) {
            movement = new Point2D(movement.getX(), 0);
            // no jump if in air
            // but we can still move a little
            movement = movement.multiply(AIR_FRICTION_FACTOR);
        }
        return movement;
    }

    /**
     * 
     * @return true if the owner's @EntityBody is on another one.
     */
    private boolean isOnGround() {
        return getEntity().getBody().getContacts()
                .filter(body -> PositionCompare.relativeSide(getEntity().getBody(), body) == Side.BOTTOM)
                .filter(EntityBody::isSolid)
                .findAny()
                .isPresent();
    }

    private void updateState() {
        if (isOnGround()) {
            setState(getDesiredMovement().getY() > 0 ? State.JUMPING
                    : getDesiredMovement().getX() != 0 ? State.WALKING : State.IDLE);
        } else {
            if (getState() != State.JUMPING) {
                setState(State.FALLING);
            }
        }
    }
}
