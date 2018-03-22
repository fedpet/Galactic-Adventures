package it.unibo.oop17.ga_game.model.physics;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.utils.CollisionGrid;
import it.unibo.oop17.ga_game.utils.FXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/* package-private */ final class B2DBodyFactory implements BodyFactory {
    private final B2DPhysicsEngine engine;

    /* package-private */ B2DBodyFactory(final B2DPhysicsEngine box2dPhysicsEngine) {
        engine = Objects.requireNonNull(box2dPhysicsEngine);
    }

    @Override
    public EntityBody createCreature(final Point2D position, final Dimension2D size) {
        final Body body = new B2DBodyBuilder(engine)
                .position(position)
                .type(BodyType.DYNAMIC)
                .build();
        new B2DFixtureBuilder()
                .rectangular(size)
                .buildOn(body);

        return spawnBody(body, size);
    }

    @Override
    public EntityBody createMovingPlatform(final Point2D position, final Dimension2D size) {
        final Body body = new B2DBodyBuilder(engine)
                .position(position)
                .type(BodyType.KINEMATIC)
                .build();
        new B2DFixtureBuilder()
                .rectangular(size)
                .buildOn(body);

        return spawnBody(body, size);
    }

    @Override
    public List<EntityBody> createTerrainFromGrid(final Point2D topLeft, final Dimension2D cellSize,
            final CollisionGrid grid) {
        return new RectanglesExtractor().rectangles(topLeft, grid, cellSize).stream()
                .map(rect -> createTerrain(FXUtils.invertY(FXUtils.center(rect)), FXUtils.dimension(rect)))
                .collect(Collectors.toList());
    }

    @Override
    public EntityBody createTerrain(final Point2D position, final Dimension2D size) {
        final Body body = new B2DBodyBuilder(engine)
                .position(position)
                .type(BodyType.STATIC)
                .build();
        new B2DFixtureBuilder()
                .density(1)
                .friction(0)
                .rectangular(size)
                .buildOn(body);

        return spawnBody(body, size);
    }

    @Override
    public EntityBody createItem(final Point2D position, final Dimension2D size) {
        final Body body = new B2DBodyBuilder(engine)
                .position(position)
                .type(BodyType.STATIC)
                .build();
        new B2DFixtureBuilder()
                .isSensor(true)
                .friction(0)
                .rectangular(size)
                .buildOn(body);

        return spawnBody(body, size);
    }

    private EntityBody spawnBody(final Body body, final Dimension2D size) {
        final B2DEntityBody entity = new B2DBodyFacade(body, size, engine.getBodiesMap());
        engine.map(body, entity);
        return entity;
    }

}
