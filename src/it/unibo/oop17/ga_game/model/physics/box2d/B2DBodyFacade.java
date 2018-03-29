package it.unibo.oop17.ga_game.model.physics.box2d;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.jbox2d.dynamics.Body;

import it.unibo.oop17.ga_game.model.entities.components.AbstractEntityComponent;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.events.BeginContactEvent;
import it.unibo.oop17.ga_game.model.entities.events.EndContactEvent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Facade class used to hide Box2D details and simplify use of its API.
 */
/* package-private */ class B2DBodyFacade extends AbstractEntityComponent implements B2DEntityBody {
    private final Body body;
    private final Dimension2D boundingBoxDimension;
    private final Map<Body, B2DEntityBody> bodyMap;

    /* package-private */ B2DBodyFacade(final Body body, final Dimension2D dimension,
            final Map<Body, B2DEntityBody> bodyMap) {
        super();
        this.body = Objects.requireNonNull(body);
        boundingBoxDimension = Objects.requireNonNull(dimension);
        this.bodyMap = Objects.requireNonNull(bodyMap);
    }

    @Override
    public Point2D getPosition() {
        return B2DUtils.vecToPoint(body.getPosition());
    }

    @Override
    public Dimension2D getDimension() {
        return boundingBoxDimension;
    }

    @Override
    public void setLinearVelocity(final Point2D velocity) {
        body.setLinearVelocity(B2DUtils.pointToVec(velocity));
    }

    @Override
    public Point2D getLinearVelocity() {
        return B2DUtils.vecToPoint(body.getLinearVelocity());
    }

    @Override
    public void applyImpulse(final Point2D impulse) {
        body.applyLinearImpulse(B2DUtils.pointToVec(impulse).mul(body.getMass()), body.getWorldCenter(), true);
    }

    @Override
    public Body getB2DBody() {
        return body;
    }

    @Override
    public void setGravityScale(final double scale) {
        body.setGravityScale((float) scale);
    }

    @Override
    public Stream<EntityBody> getContacts() {
        return B2DUtils.stream(getB2DBody().getContactList())
                .filter(c -> c.contact.isEnabled())
                .filter(c -> c.contact.isTouching())
                .filter(c -> bodyMap.containsKey(c.other))
                .map(c -> bodyMap.get(c.other));
    }

    @Override
    public void beginContact(final EntityBody other) {
        getOwner().ifPresent(entity -> {
            post(new BeginContactEvent(entity, other));
        });
    }

    @Override
    public void endContact(final EntityBody other) {
        getOwner().ifPresent(entity -> {
            post(new EndContactEvent(entity, other));
        });
    }

    @Override
    public boolean isSolid() {
        // we assume one non-sensor fixture is enough to treat the body as a solid.
        return B2DUtils.stream(body.getFixtureList())
                .filter(f -> !f.isSensor())
                .findAny()
                .isPresent();
    }
}
