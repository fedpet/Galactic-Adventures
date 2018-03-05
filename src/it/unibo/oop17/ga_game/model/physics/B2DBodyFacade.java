package it.unibo.oop17.ga_game.model.physics;

import java.util.Objects;

import org.jbox2d.dynamics.Body;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Facade class used to hide Box2D details and simplify use of its API.
 */
/* package-private */ class B2DBodyFacade implements B2DEntityBody {
    private final Body body;
    // private final Fixture mainFixture;
    private final Dimension2D boundingBoxDimension;

    /* package-private */ B2DBodyFacade(final Body body, final Dimension2D dimension) {
        this.body = Objects.requireNonNull(body);
        // boundingBoxDimension = Box2DUtils.boundingBox(body); // it could change relatively to body's angle so we
                                                             // calculate it right now.
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

}
