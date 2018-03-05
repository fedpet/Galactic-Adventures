package it.unibo.oop17.ga_game.model.physics;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.ContactEdge;

import it.unibo.oop17.ga_game.model.GroundEntityBody;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/* package-protected */ final class B2DGroundEntityBody extends B2BodyFacade implements GroundEntityBody {
    private static final float FEET_SENSOR_HEIGHT = Box2DUtils.EXTRA_SKIN_THICKNESS * 3 / 2; // a bit more than the skin
    private final Fixture feetSensor;

    /* package-protected */ B2DGroundEntityBody(final Body body, final Dimension2D dimension) {
        super(body, dimension);

        feetSensor = new B2DFixtureBuilder()
                .density(1)
                .rectangular(new Dimension2D(getDimension().getWidth(), FEET_SENSOR_HEIGHT))
                .position(new Point2D(0, -getDimension().getHeight() / 2 + FEET_SENSOR_HEIGHT / 3))
                .buildOn(body);
    }

    @Override
    public boolean isOnGround() {
        for (ContactEdge c = feetSensor.getBody().getContactList(); c != null; c = c.next) {
            if (c.contact.isEnabled() && c.contact.isTouching() && Box2DUtils.contains(c, feetSensor)) {
                return true;
            }
        }
        return false;
    }

}
