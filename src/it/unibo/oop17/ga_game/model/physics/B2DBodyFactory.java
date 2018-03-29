package it.unibo.oop17.ga_game.model.physics;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.utils.CollisionGrid;
import it.unibo.oop17.ga_game.utils.FXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/* package-private */ final class B2DBodyFactory implements BodyFactory {
    private final B2DBodySpawner spawner;

    /* package-private */ B2DBodyFactory(final B2DBodySpawner spawner) {
        this.spawner = Objects.requireNonNull(spawner);
    }

    @Override
    public EntityBody createCreature(final Point2D position, final Dimension2D size) {
        return custom()
                .position(position)
                .size(size)
                .build();
    }

    @Override
    public List<EntityBody> createTerrainFromGrid(final Point2D topLeft, final Dimension2D cellSize,
            final CollisionGrid grid) {
        return new RectanglesExtractor().rectangles(topLeft, grid, cellSize).stream()
                .map(rect -> createStatic(FXUtils.invertY(FXUtils.center(rect)), FXUtils.dimension(rect)))
                .collect(Collectors.toList());
    }

    @Override
    public EntityBody createStatic(final Point2D position, final Dimension2D size) {
        return custom()
                .position(position)
                .size(size)
                .moveable(false)
                .friction(0)
                .build();
    }

    @Override
    public EntityBody createItem(final Point2D position, final Dimension2D size) {
        return custom()
                .position(position)
                .size(size)
                .moveable(false)
                .solid(false)
                .friction(0)
                .build();
    }

    @Override
    public BodyBuilder custom() {
        return new B2DBodyBuilder(spawner);
    }
}
