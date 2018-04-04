package it.unibo.oop17.ga_game.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UnknownFormatConversionException;

import org.mapeditor.core.Map;
import org.mapeditor.core.MapObject;
import org.mapeditor.core.ObjectGroup;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

import com.google.common.io.Files;

import it.unibo.oop17.ga_game.model.CoinType;
//import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.ModelSettings;
import it.unibo.oop17.ga_game.model.entities.Coin;
import it.unibo.oop17.ga_game.model.entities.Player;
import it.unibo.oop17.ga_game.model.entities.components.Inventory;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import it.unibo.oop17.ga_game.utils.FXUtils;
import it.unibo.oop17.ga_game.utils.SimpleCollisionGrid;
import it.unibo.oop17.ga_game.utils.ZipUtils;
import it.unibo.oop17.ga_game.view.GameWorldView;
import it.unibo.oop17.ga_game.view.HudView;
import it.unibo.oop17.ga_game.view.Music;
import it.unibo.oop17.ga_game.view.entities.CoinView;
import it.unibo.oop17.ga_game.view.entities.LifelessEntityView;
import it.unibo.oop17.ga_game.view.entities.PlayerView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameController {
    private static final double FRAMERATE = 1.0 / 60;
    private final GameWorld model;
    private final GameWorldView view;
    private final HudView hudView;
    private final Group worldView = new Group();
    private final Set<EntityController> entities = new LinkedHashSet<>();
    
    private Player player;
    private Set<Coin> coins = new HashSet<Coin>();
    
//    private final GameData save;

    public GameController(final GameWorld world, final GameWorldView view, final HudView hudView) {
        this.model = world;
        this.view = view;
        this.hudView = hudView;
//        this.save = (GameData)LoadSaveManager.load("gamedata.dat");
        this.whichLevel();
    }
    
    private void whichLevel() {
        
        Map level;
        
        final File tempDir = Files.createTempDir();
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream("levels.zip"))) {
            ZipUtils.extract(is, tempDir);
            level = loadMap(new File(tempDir, "level0.tmx"));
        } catch (final IOException ex) {
            // TODO: add error message
            ex.printStackTrace();
            Platform.exit();
            level = null; // unreachable
        }
        
        this.run(level);
    }
    
    private Map loadMap(final File path) throws IOException {
        try {
            return new TMXMapReader().readMap(path.getAbsolutePath());
            // readMap throws a generic Exception :(
        } catch (final IOException e) {
            // this print is to prevent "A catch statement that catches an exception only to rethrow it should be
            // avoided...
            System.out.println("map read error");
            throw e;
        } catch (final Exception e) {
            // we assume map reading can only fail because of an IOException or a bad file format
            throw new UnknownFormatConversionException(e.getMessage());
        }
    }

    private void run(final Map level) {
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

        

    }

    private void loadEntities(final ObjectGroup layer) {
        final BodyFactory bodyFactory = model.bodyFactory();
        layer.forEach(mapObj -> {
            final Point2D position = FXUtils.invertY(new Point2D(mapObj.getX() / 70, mapObj.getY() / 70));
            if (mapObj.getType().equals("player")) {
                player = new Player(bodyFactory, position);
                model.addEntity(player);
                entities.add(new PlayerController(view.getPlayerInput(), player,
                        view.entityFactory().createPlayer()));
            }
            if (mapObj.getType().equals("gCoin")) {
                final Coin coin = new Coin(bodyFactory, position, 100);
                coins.add(coin);
                model.addEntity(coin);
                entities.add(new LifelessEntityController(coin, new CoinView(worldView, CoinType.GOLD)));
                
            }
            if (mapObj.getType().equals("sCoin")) {
                final Coin coin = new Coin(bodyFactory, position, 50);
                model.addEntity(coin);
                entities.add(new LifelessEntityController(coin, new CoinView(worldView, CoinType.SILVER)));
                
            }
            if (mapObj.getType().equals("bCoin")) {
                final Coin coin = new Coin(bodyFactory, position, 25);
                model.addEntity(coin);
                entities.add(new LifelessEntityController(coin, new CoinView(worldView, CoinType.BRONZE)));
                
            }
        });
    }

    private void loadTerrain(final TileLayer layer) {
        view.showTerrain(layer, Point2D.ZERO,
                new Dimension2D(ModelSettings.TILE_SIZE, ModelSettings.TILE_SIZE));

        if (layer.getName().equals("ground")) {
            model.bodyFactory()
            .createTerrainFromGrid(Point2D.ZERO,
                    new Dimension2D(ModelSettings.TILE_SIZE, ModelSettings.TILE_SIZE),
                    new SimpleCollisionGrid(layer.getMap().getHeight(), layer.getMap().getWidth(),
                            (row, column) -> {
                                return doesTileExists(layer.getTileAt(column, row));
                            }));
        }
        
    }

    private boolean doesTileExists(final Tile tile) {
        return tile != null && tile.getImage() != null;
    }
}
