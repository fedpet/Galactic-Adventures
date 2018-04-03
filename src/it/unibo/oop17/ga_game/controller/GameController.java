package it.unibo.oop17.ga_game.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import org.mapeditor.core.Map;
import org.mapeditor.core.ObjectGroup;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;

import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.ModelSettings;
import it.unibo.oop17.ga_game.model.entities.Player;
import it.unibo.oop17.ga_game.model.entities.components.Inventory;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import it.unibo.oop17.ga_game.utils.SimpleCollisionGrid;
import it.unibo.oop17.ga_game.view.GameWorldView;
import it.unibo.oop17.ga_game.view.HudView;
import javafx.animation.AnimationTimer;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class GameController {
    private static final double FRAMERATE = 1.0 / 60;
    private final GameWorld model;
    private final GameWorldView view;
    private final HudView hudView;
    private final Set<EntityController> entities = new LinkedHashSet<>();
    private Player player;

    public GameController(final GameWorld world, final GameWorldView view, final HudView hudView) {
        this.model = world;
        this.view = view;
        this.hudView = hudView;
    }

    public void run(final Map level) {
        loadLevel(level);
        hudView.addHud();

        new AnimationTimer() {
            @Override
            public void handle(final long now) {
                update();
            }
        }.start();
    }

    private void update() {
        entities.forEach(EntityController::update);
        model.update(FRAMERATE);
        if (player != null) {
            hudView.update(player.get(Life.class).get().getHealthPoints(),
                    player.get(Inventory.class).get().getMoney());
        }
    }

    private void loadLevel(final Map map) {
        map.forEach(layer -> {
            if (layer instanceof TileLayer) {
                loadTerrain((TileLayer) layer);
            } else if (layer instanceof ObjectGroup) {
                loadEntities((ObjectGroup) layer);
            }
        });

        final BodyFactory body = model.bodyFactory();
        player = new Player(body, new Point2D(4, -4));
        model.addEntity(player);
        entities.add(new PlayerController(view.getPlayerInput(), player,
                view.entityFactory().createPlayer()));

    }

    private void loadEntities(final ObjectGroup layer) {
        layer.forEach(mapObj -> {
            final Point2D position = new Point2D(mapObj.getX(), mapObj.getY());
            // TODO: fai..
        });
    }

    private void loadTerrain(final TileLayer layer) {
        view.showTerrain(layer, Point2D.ZERO,
                new Dimension2D(ModelSettings.TILE_SIZE, ModelSettings.TILE_SIZE));

        model.bodyFactory()
                .createTerrainFromGrid(Point2D.ZERO,
                        new Dimension2D(ModelSettings.TILE_SIZE, ModelSettings.TILE_SIZE),
                        new SimpleCollisionGrid(layer.getMap().getHeight(), layer.getMap().getWidth(),
                                (row, column) -> {
                                    return doesTileExists(layer.getTileAt(column, row));
                                }));
    }

    private boolean doesTileExists(final Tile tile) {
        return tile != null && tile.getImage() != null;
    }
}
