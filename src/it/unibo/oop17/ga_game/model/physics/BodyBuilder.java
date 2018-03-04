package it.unibo.oop17.ga_game.model.physics;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import it.unibo.oop17.ga_game.utils.Box2DUtils;
import javafx.geometry.Point2D;

public final class BodyBuilder {
    private final BodyDef bodyDef = new BodyDef();
    private final World world;

    public BodyBuilder(final World world) {
        this.world = world;
    }

    public BodyBuilder type(final BodyType type) {
        bodyDef.setType(type);
        return this;
    }

    public BodyBuilder position(final Point2D pos) {
        bodyDef.setPosition(Box2DUtils.pointToVec(pos));
        return this;
    }

    public BodyBuilder rotatable(final boolean rotatable) {
        bodyDef.setFixedRotation(!rotatable);
        return this;
    }

    public Body build() {
        // TODO: sistema
        // body.setUserData(body);
        return world.createBody(bodyDef);
    }
}
