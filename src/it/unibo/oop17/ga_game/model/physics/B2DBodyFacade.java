package it.unibo.oop17.ga_game.model.physics;

import java.util.Objects;
import java.util.Optional;

import org.jbox2d.dynamics.Body;

import it.unibo.oop17.ga_game.model.AbstractEntityComponent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Facade class used to hide Box2D details and simplify use of its API.
 */
/* package-private */ class B2DBodyFacade extends AbstractEntityComponent implements B2DEntityBody {
    private final Body body;
    private final Dimension2D boundingBoxDimension;

    /* package-private */ B2DBodyFacade(final Body body, final Dimension2D dimension) {
        this.body = Objects.requireNonNull(body);
        boundingBoxDimension = Objects.requireNonNull(dimension);
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
    public Body getB2Body() {
        return body;
    }

    @Override
    public Optional<CollisionListener> getCollisionListener() {
        if (!getOwner().isPresent()) {
            return Optional.empty();
        }
        return Optional.of(getOwner().get().getBrain());
    }

}