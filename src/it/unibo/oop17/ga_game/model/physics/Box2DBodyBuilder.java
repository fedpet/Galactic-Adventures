package it.unibo.oop17.ga_game.model.physics;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

import javafx.geometry.Point2D;

/* package-protected */ final class Box2DBodyBuilder {
    private final BodyDef bodyDef = new BodyDef();
    private final Box2DPhysicsEngine engine;

    /* package-protected */ Box2DBodyBuilder(final Box2DPhysicsEngine engine) {
        this.engine = engine;
        bodyDef.setFixedRotation(true);
    }

    /**
     * STATIC - unmoveable.
     * DYNAMIC - moveable, subject to external forces.
     * KINEMATIC - moveable, not subject to external forces.
     * 
     * @param type
     *            The type
     * @return the builder
     */
    public Box2DBodyBuilder type(final BodyType type) {
        bodyDef.setType(type);
        return this;
    }

    public Box2DBodyBuilder position(final Point2D pos) {
        bodyDef.setPosition(Box2DUtils.pointToVec(pos));
        return this;
    }

    public Body build() {
        return engine.getB2DWorld().createBody(bodyDef);
    }
}
