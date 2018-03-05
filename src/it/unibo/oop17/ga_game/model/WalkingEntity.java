package it.unibo.oop17.ga_game.model;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import it.unibo.oop17.ga_game.model.physics.FixtureBuilder;
import it.unibo.oop17.ga_game.model.physics.Sensor;
import it.unibo.oop17.ga_game.model.physics.SensorImpl;
import it.unibo.oop17.ga_game.utils.Box2DUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * Basic entity capable of walking and jumping.
 * TODO: refactor this class
 */
public abstract class WalkingEntity implements Entity {
    private static final double FEET_SENSOR_HEIGHT = Box2DUtils.EXTRA_SKIN_THICKNESS * 3 / 2;
    private final Body body;
    private final Sensor feet;
	private final Sensor front;
	private final Sensor front2;
    private final Vec2 desiredMovement = new Vec2();

    /**
     * 
     * @param world
     *            The world this entity lives in.
     * @param rect
     *            The shape of its Body
     */
    public WalkingEntity(final World world, final Point2D position, final Dimension2D dimension) {
        body = new BodyBuilder(world)
                .rotatable(false)
                .type(BodyType.DYNAMIC)
                .position(position)
                .build();
        new FixtureBuilder()
                .density(1)
                .rectangular(new Dimension2D(dimension.getWidth(), dimension.getHeight()))
                // .position(new Point2D(0, FEET_SENSOR_HEIGHT))
                .buildOn(body);


        // we put the feet sensor to the bottom of the body
        feet = new SensorImpl(body,
                new Rectangle2D(0, -dimension.getHeight() / 2, dimension.getWidth(),
                        FEET_SENSOR_HEIGHT));

		front = new SensorImpl(body,
				new Rectangle2D(0, 0, dimension.getWidth() / 2, dimension.getHeight() - FEET_SENSOR_HEIGHT));

		front2 = new SensorImpl(body,
				new Rectangle2D(0, dimension.getWidth() / 2, dimension.getWidth() / 2,
						dimension.getHeight() - FEET_SENSOR_HEIGHT));
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public void update(final double dt) {
        // if (feet.isTouching()) {
		Vec2 movement = desiredMovement.clone().sub(body.getLinearVelocity())
                    .mul(body.getMass());
        if (!feet.isTouching()) {
            movement = movement.mul(0.5f);
        }

            if (desiredMovement.y != 0) {
                desiredMovement.y = 0;
            } else {
                movement.y = 0;
            }

            if (movement.x != 0 || movement.y != 0) {
                body.applyLinearImpulse(movement, body.getWorldCenter(), true);
            }
        // } else {
        // System.out.println("flyiing");
        // }
    }

    public HorizontalDirection getMovingDirection() {
        if (desiredMovement.x < 0) {
            return HorizontalDirection.LEFT;
        }
        return HorizontalDirection.RIGHT;
    }

    public void move(final HorizontalDirection direction) {
        desiredMovement.x = direction == HorizontalDirection.RIGHT ? getWalkSpeed() : -getWalkSpeed();
    }

    public void stopWalking() {
        desiredMovement.x = 0;
    }

    public void jump() {
        if (feet.isTouching()) {
            desiredMovement.y = getJumpSpeed();
        }
    }

	public boolean againstWall() {
		return front.isTouching() || front2.isTouching();
	}

    protected abstract float getJumpSpeed();

    protected abstract float getWalkSpeed();
}
