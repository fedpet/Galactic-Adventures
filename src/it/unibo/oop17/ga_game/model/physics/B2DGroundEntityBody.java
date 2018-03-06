package it.unibo.oop17.ga_game.model.physics;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

import it.unibo.oop17.ga_game.model.GroundEntityBody;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/* package-protected */ final class B2DGroundEntityBody extends B2DBodyFacade implements GroundEntityBody {
    private static final float FEET_SENSOR_HEIGHT = B2DUtils.EXTRA_SKIN_THICKNESS;
    private final Fixture feetSensor;

    /* package-protected */ B2DGroundEntityBody(final Body body, final Dimension2D dimension,
            final B2DPhysicsEngine engine) {
        super(body, dimension);

        // position the feet sensor to the bottom of the body
        feetSensor = new B2DFixtureBuilder()
                .isSensor(true)
                .rectangular(new Dimension2D(getDimension().getWidth(), FEET_SENSOR_HEIGHT))
                .position(new Point2D(0, -getDimension().getHeight() / 2 + FEET_SENSOR_HEIGHT / 2))
                .buildOn(body);

        // TODO migliora
        engine.setCollisionListener(feetSensor, this);
    }

    @Override
    public boolean isOnGround() {
        return B2DUtils.stream(getB2DBody().getContactList())
                .filter(c -> c.contact.isEnabled())
                .filter(c -> c.contact.isTouching())
                .filter(c -> B2DUtils.contains(c, feetSensor)
                        || c.contact.getManifold().localPoint.y >= getDimension().getHeight())
                .findAny()
                .isPresent();
    }

}
