package it.unibo.oop17.ga_game.model.physics;

import java.util.Objects;
import java.util.Optional;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/* package-protected */ final class B2DBodyBuilder implements BodyBuilder {
    private final B2DBodySpawner spawner;
    private Optional<Point2D> pos = Optional.empty();
    private Optional<Dimension2D> dimension = Optional.empty();
    private Optional<Float> surfaceFriction = Optional.empty();
    private boolean isSubjectToForces = true;
    private boolean isSolid = true;
    private boolean isMoveable = true;

    /* package-private */ B2DBodyBuilder(final B2DBodySpawner spawner) {
        this.spawner = Objects.requireNonNull(spawner);
    }

    @Override
    public BodyBuilder subjectToForces(final boolean opt) {
        isSubjectToForces = opt;
        return this;
    }

    @Override
    public BodyBuilder position(final Point2D position) {
        pos = Optional.of(position);
        return this;
    }

    @Override
    public BodyBuilder size(final Dimension2D size) {
        dimension = Optional.of(size);
        return this;
    }

    @Override
    public BodyBuilder solid(final boolean solid) {
        isSolid = solid;
        return this;
    }

    @Override
    public BodyBuilder moveable(final boolean moveable) {
        isMoveable = moveable;
        return this;
    }

    @Override
    public BodyBuilder friction(final double friction) {
        // we accept precision loss because Box2D works this way.
        surfaceFriction = Optional.of((float) friction);
        return this;
    }

    @Override
    public EntityBody build() {
        checkDataValidity();

        final BodyDef bodyDef = new BodyDef();
        bodyDef.setFixedRotation(true);
        bodyDef.setType(makeType());
        bodyDef.setPosition(B2DUtils.pointToVec(pos.get()));
        final B2DBodyFacade body = spawner.spawn(bodyDef, dimension.get());
        final FixtureDef fixtureDef = new FixtureDef();
        surfaceFriction.ifPresent(fixtureDef::setFriction);
        fixtureDef.setSensor(!isSolid);
        final PolygonShape shape = new PolygonShape();
        shape.setAsBox((float) dimension.get().getWidth() / 2, (float) dimension.get().getHeight() / 2);
        fixtureDef.setShape(shape);
        body.getB2DBody().createFixture(fixtureDef);
        body.getB2DBody().resetMassData();

        return body;
    }

    private BodyType makeType() {
        if (!isMoveable) {
            return BodyType.STATIC;
        }
        if (!isSubjectToForces) {
            return BodyType.KINEMATIC;
        }
        return BodyType.DYNAMIC;
    }

    private void checkDataValidity() {
        assertPresent(pos, "Position");
        assertPresent(dimension, "Size");
    }

    private void assertPresent(final Optional<?> opt, final String varName) {
        if (!opt.isPresent()) {
            throw new IllegalStateException(varName + " must be specified");
        }
    }
}
