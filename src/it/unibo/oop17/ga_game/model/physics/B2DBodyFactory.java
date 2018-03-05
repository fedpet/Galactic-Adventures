package it.unibo.oop17.ga_game.model.physics;

import java.util.Objects;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;

import it.unibo.oop17.ga_game.model.EntityBody;
import it.unibo.oop17.ga_game.model.GroundEntityBody;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/* package-private */ final class B2DBodyFactory implements BodyFactory {
    private final B2DPhysicsEngine engine;

    /* package-private */ B2DBodyFactory(final B2DPhysicsEngine box2dPhysicsEngine) {
        engine = Objects.requireNonNull(box2dPhysicsEngine);
    }

    @Override
    public GroundEntityBody createGroundCreature(final Point2D position, final Dimension2D size) {
        final Body body = new B2DBodyBuilder(engine)
                .position(position)
                .type(BodyType.DYNAMIC)
                .build();
        final Fixture fixture = new B2DFixtureBuilder()
                // .density(1)
                .rectangular(size)
                .buildOn(body);
        final B2DGroundEntityBody entity = new B2DGroundEntityBody(body, size, engine);
        connectListener(entity, fixture);
        return entity;
    }

    @Override
    public EntityBody createTerrain(final Point2D position, final Dimension2D size) {
        final Body body = new B2DBodyBuilder(engine)
                .position(position)
                .type(BodyType.STATIC)
                .build();
        final Fixture fixture = new B2DFixtureBuilder()
                .density(1)
                .friction(0)
                .rectangular(size)
                .buildOn(body);

        final B2DEntityBody entity = new B2DBodyFacade(body, size);
        connectListener(entity, fixture);
        return entity;
    }

    private void connectListener(final B2DEntityBody body, final Fixture fixture) {
        engine.setCollisionListener(fixture, body);
    }

}