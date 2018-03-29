package it.unibo.oop17.ga_game.model.entities.components;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.BeginContactEvent;
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

    /**
     * When we touch the ground we update the state to change from JUMPING to IDLE.
     * 
     * @param event
     *            The @BeginContactEvent
     */
    @Subscribe
    public void beginContact(final BeginContactEvent event) {
        updateState();
    }

    @Override
    protected Point2D computeMovement(final double dt) {
        Point2D movement = getDesiredMovement().subtract(getEntity().getBody().getLinearVelocity());
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
        }
    }
}
