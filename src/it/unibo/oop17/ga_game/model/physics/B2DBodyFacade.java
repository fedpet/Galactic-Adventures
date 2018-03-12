package it.unibo.oop17.ga_game.model.physics;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.jbox2d.dynamics.Body;

import it.unibo.oop17.ga_game.model.entities.components.AbstractEntityComponent;
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
    public Optional<CollisionListener> getCollisionListener() {
        if (!getOwner().isPresent()) {
            return Optional.empty();
        }
        return Optional.of(getOwner().get().getBrain());
    }

    @Override
    public void setGravityScale(final double scale) {
        body.setGravityScale((float) scale);
    }

    @Override
    public Stream<BodyContact> getContacts() {
        return B2DUtils.stream(getB2DBody().getContactList())
                .filter(c -> c.contact.isEnabled())
                .filter(c -> c.contact.isTouching())
                .filter(c -> bodyMap.containsKey(c.other))
                .map(c -> {
                    return new BodyContactImpl(bodyMap.get(c.other),
                            B2DUtils.vecToPoint(c.contact.getManifold().localPoint));
                });
    }

}
