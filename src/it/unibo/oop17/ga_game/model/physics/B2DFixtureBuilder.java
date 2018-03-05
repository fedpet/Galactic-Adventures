package it.unibo.oop17.ga_game.model.physics;

import java.util.Optional;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;

import it.unibo.oop17.ga_game.utils.FXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * Builder for Fixtures.
 */
public final class B2DFixtureBuilder {
    private final FixtureDef fixtureDef = new FixtureDef();
    private final PolygonShape shape = new PolygonShape();
    private Optional<Point2D> pos = Optional.empty();
    private Optional<Dimension2D> dimension = Optional.empty();

    /**
     * @param density
     *            the density
     * @return This instance
     */
    public B2DFixtureBuilder density(final float density) {
        fixtureDef.setDensity(density);
        return this;
    }

    /**
     * @param friction
     *            the friction
     * @return This instance
     */
    public B2DFixtureBuilder friction(final float friction) {
        fixtureDef.setFriction(friction);
        return this;
    }

    /**
     * @param dimension
     *            The rectangle's width and height (in meters)
     * @return This instance
     */
    public B2DFixtureBuilder rectangular(final Dimension2D dimension) {
        this.dimension = Optional.of(dimension);
        return this;
    }

    /**
     * @param rect
     *            The Rectangle. Its center will be the Fixture's position
     * @return This instance
     */
    public B2DFixtureBuilder rectangular(final Rectangle2D rect) {
        this.dimension = Optional.of(FXUtils.dimension(rect));
        pos = Optional.of(FXUtils.center(rect));
        return this;
    }

    /**
     * @param width
     *            The rectangle's width
     * @param height
     *            The rectangle's height
     * @return This instance
     */
    public B2DFixtureBuilder rectangular(final double width, final double height) {
        return rectangular(new Dimension2D(width, height));
    }

    /**
     * A sensor is a non-solid fixture used to detect other bodies in its area.
     * 
     * @param sensor
     *            true if you want this fixture to be a sensor
     * @return This instance
     */
    public B2DFixtureBuilder isSensor(final boolean sensor) {
        fixtureDef.setSensor(sensor);
        return this;
    }

    /**
     * 
     * @param position
     *            the position
     * @return This instance
     */
    public B2DFixtureBuilder position(final Point2D position) {
        pos = Optional.of(position);
        return this;
    }

    /**
     * Builds the Fixture attaching it to the body passed as an argument.
     * 
     * @param owner
     *            The body to which attach the fixture to.
     * @return The Fixture
     */
    public Fixture buildOn(final Body owner) {
        if (!dimension.isPresent()) {
            throw new IllegalStateException("The fixture must have a dimension!");
        }
        // setAsBox wants HALF width and height
        shape.setAsBox((float) dimension.get().getWidth() / 2, (float) dimension.get().getHeight() / 2,
                Box2DUtils.pointToVec(pos.orElse(Point2D.ZERO)), 0);
        fixtureDef.setShape(shape);
        final Fixture fix = owner.createFixture(fixtureDef);
        owner.resetMassData();
        return fix;
    }
}
