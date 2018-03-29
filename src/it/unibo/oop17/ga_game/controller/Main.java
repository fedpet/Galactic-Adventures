package it.unibo.oop17.ga_game.controller;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UnknownFormatConversionException;

import org.mapeditor.core.Map;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

import com.google.common.io.Files;

import it.unibo.oop17.ga_game.model.CircleIterator;
import it.unibo.oop17.ga_game.model.CoinType;
import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.ModelSettings;
import it.unibo.oop17.ga_game.model.entities.Coin;
import it.unibo.oop17.ga_game.model.entities.Door;
import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.FlyingEnemy;
import it.unibo.oop17.ga_game.model.entities.JumpingPlatform;
import it.unibo.oop17.ga_game.model.entities.Key;
import it.unibo.oop17.ga_game.model.entities.Lever;
import it.unibo.oop17.ga_game.model.entities.Lock;
import it.unibo.oop17.ga_game.model.entities.MovingPlatform;
import it.unibo.oop17.ga_game.model.entities.Player;
import it.unibo.oop17.ga_game.model.entities.SlimeEnemy;
import it.unibo.oop17.ga_game.model.entities.Spikes;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import it.unibo.oop17.ga_game.utils.InfiniteSequence;
import it.unibo.oop17.ga_game.utils.ShapePerimeterIterator;
import it.unibo.oop17.ga_game.utils.SimpleCollisionGrid;
import it.unibo.oop17.ga_game.utils.ZipUtils;
import it.unibo.oop17.ga_game.view.PlayerKeyboardInput;
import it.unibo.oop17.ga_game.view.ViewUtils;
import it.unibo.oop17.ga_game.view.entities.CoinView;
import it.unibo.oop17.ga_game.view.entities.DoorView;
import it.unibo.oop17.ga_game.view.entities.FlyingEnemyView;
import it.unibo.oop17.ga_game.view.entities.JumpingPlatformView;
import it.unibo.oop17.ga_game.view.entities.KeyView;
import it.unibo.oop17.ga_game.view.entities.LeverView;
import it.unibo.oop17.ga_game.view.entities.LifelessEntityView;
import it.unibo.oop17.ga_game.view.entities.LivingEntityView;
import it.unibo.oop17.ga_game.view.entities.LockView;
import it.unibo.oop17.ga_game.view.entities.PlayerView;
import it.unibo.oop17.ga_game.view.entities.SlimeEnemyView;
import it.unibo.oop17.ga_game.view.entities.SpikesView;
import it.unibo.oop17.ga_game.view.entities.TriggerEntityView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Entry point.
 */
public class Main extends Application {
    private static final double FRAMERATE = 1.0 / 60;
    private static final double SCALE = 1; // TODO: gestire diversa scale
    private final GameWorld gameWorld = new GameWorld();
    private final Group root = new Group();
    private final Group worldView = new Group();

    /**
     * Entry point.
     * 
     * @param args
     *            Command-line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public final void start(final Stage primaryStage) {
        final Scene scene = new Scene(root);
        worldView.setScaleX(SCALE);
        worldView.setScaleY(SCALE);
        root.getChildren().add(worldView);
        primaryStage.setScene(scene);
        primaryStage.show();

        final BodyFactory bodyFactory = gameWorld.bodyFactory();

        final Player player = new Player(bodyFactory, new Point2D(4, -4));
        gameWorld.addEntity(player);
        final MovingPlatform platform = new MovingPlatform(bodyFactory, new Point2D(4, -10),
                new Dimension2D(4, 1),
                InfiniteSequence.backAndForth(Arrays.asList(new Point2D(4, -5), new Point2D(4, -25))));
        gameWorld.addEntity(platform);

        final Shape shape = new Ellipse2D.Double(8, -22, 5, 5);
        final InfiniteSequence<Point2D> circlePattern = InfiniteSequence
                .repeat(() -> new ShapePerimeterIterator(shape));
        final MovingPlatform platform2 = new MovingPlatform(bodyFactory, new Point2D(4, -26),
                new Dimension2D(4, 1),
                circlePattern);
        gameWorld.addEntity(platform2);

        final ImageView platformView = new ImageView(new Image("/stone.png"));
        final ImageView platformView2 = new ImageView(new Image("/stone.png"));
        final SlimeEnemy slimeEnemy = new SlimeEnemy(bodyFactory, new Point2D(20, -20));
        gameWorld.addEntity(slimeEnemy);
        final LivingEntityView slimeEnemyView = new SlimeEnemyView(worldView);
        final FlyingEnemy flyingEnemy = new FlyingEnemy(bodyFactory, new Point2D(4, -4), InfiniteSequence
                .repeat(() -> new CircleIterator(new Point2D(4, -4), 5, 5)));
        gameWorld.addEntity(flyingEnemy);
        final LivingEntityView flyingEnemyView = new FlyingEnemyView(worldView);
        final Coin coin = new Coin(bodyFactory, new Point2D(2, -28.5), 100);
        gameWorld.addEntity(coin);
        final LifelessEntityView coinView = new CoinView(worldView, CoinType.BRONZE);
        final Key blueKey = new Key(bodyFactory, new Point2D(10, -28), KeyLockType.BLUE);
        gameWorld.addEntity(blueKey);
        final LifelessEntityView blueKeyView = new KeyView(worldView, KeyLockType.BLUE);
        final Key redKey = new Key(bodyFactory, new Point2D(7, -25.5), KeyLockType.RED);
        gameWorld.addEntity(redKey);
        final LifelessEntityView redKeyView = new KeyView(worldView, KeyLockType.RED);
        final Lock lock = new Lock(bodyFactory, new Point2D(14, -28.5), KeyLockType.BLUE);
        gameWorld.addEntity(lock);
        final LifelessEntityView lockView = new LockView(worldView, KeyLockType.BLUE);
        final Lever lever = new Lever(bodyFactory, new Point2D(18, -25.5), "Door", false);
        gameWorld.addEntity(lever);
        final TriggerEntityView leverView = new LeverView(worldView, false);
        final Door door = new Door(bodyFactory, new Point2D(12, -25.5), "Door", false);
        gameWorld.addEntity(door);
        final TriggerEntityView doorView = new DoorView(worldView, false);
        final Spikes spike = new Spikes(bodyFactory, new Point2D(26, -28.5));
        gameWorld.addEntity(spike);
        final LifelessEntityView spikeView = new SpikesView(worldView);

        final Entity jumper = new JumpingPlatform(bodyFactory, new Point2D(25, -28.5));
        gameWorld.addEntity(jumper);
        final EntityController jumperController = new TriggerEntityController(jumper,
                new JumpingPlatformView(worldView));

        platformView.setFitWidth(ViewUtils.metersToPixels(platform.getBody().getDimension().getWidth()));
        platformView.setFitHeight(ViewUtils.metersToPixels(platform.getBody().getDimension().getHeight()));
        platformView2.setFitWidth(ViewUtils.metersToPixels(platform2.getBody().getDimension().getWidth()));
        platformView2.setFitHeight(ViewUtils.metersToPixels(platform2.getBody().getDimension().getHeight()));
        worldView.getChildren().add(platformView);
        worldView.getChildren().add(platformView2);

        final PlayerView playerView = new PlayerView(worldView);
        final EntityController playerController = new PlayerController(new PlayerKeyboardInput(scene), player,
                playerView);
        final EntityController basicEnemyController = new LivingEntityController(slimeEnemy, slimeEnemyView);
        final EntityController flyingEnemyController = new LivingEntityController(flyingEnemy,
                flyingEnemyView);
        final EntityController coinController = new LifelessEntityController(coin, coinView);
        final EntityController blueKeyController = new LifelessEntityController(blueKey, blueKeyView);
        final EntityController redKeyController = new LifelessEntityController(redKey, redKeyView);
        final EntityController lockController = new LifelessEntityController(lock, lockView);
        final EntityController leverController = new TriggerEntityController(lever, leverView);
        final EntityController doorController = new TriggerEntityController(door, doorView);
        final EntityController spikeController = new LifelessEntityController(spike, spikeView);


        final File tempDir = Files.createTempDir();
        try (InputStream is = getClass().getResourceAsStream("/levels.zip")) {
            ZipUtils.extract(is, tempDir);
            final Map map = loadLevel(new File(tempDir, "level1.tmx"));
        } catch (final IOException ex) {
            // TODO: show an error message
            ex.printStackTrace();
            Platform.exit();
        }

        final Timeline timer = new Timeline(new KeyFrame(
                Duration.seconds(FRAMERATE),
                event -> {
                    gameWorld.update(FRAMERATE);

                    basicEnemyController.update();
                    flyingEnemyController.update();
                    coinController.update();
                    blueKeyController.update();
                    redKeyController.update();
                    lockController.update();
                    leverController.update();
                    doorController.update();
                    spikeController.update();
                    jumperController.update();

                    playerController.update();

                    Point2D pt = ViewUtils.worldPointToFX(platform.getBody().getPosition());
                    platformView.setTranslateX(pt.getX() - platformView.getBoundsInLocal().getWidth() / 2);
                    platformView.setTranslateY(pt.getY() - platformView.getBoundsInLocal().getHeight() / 2);
                    pt = ViewUtils.worldPointToFX(platform2.getBody().getPosition());
                    platformView2.setTranslateX(pt.getX() - platformView2.getBoundsInLocal().getWidth() / 2);
                    platformView2.setTranslateY(pt.getY() - platformView2.getBoundsInLocal().getHeight() / 2);

                    worldView.setTranslateX(-playerView.getPosition().getX() + scene.getWidth() / 2);
                    worldView.setTranslateY(-playerView.getPosition().getY() + scene.getHeight() / 2);
                }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private Map loadLevel(final File file) throws IOException {
        Map map;
        try {
            map = new TMXMapReader().readMap(file.getAbsolutePath());
            // readMap throws a generic Exception :(
        } catch (final IOException e) {
            // this is to prevent "A catch statement that catches an exception only to rethrow it should be avoided...
            System.out.println("map read error");
            throw e;
        } catch (final Exception e) {
            // we assume map reading can only fail because of an IOException or a bad file format
            throw new UnknownFormatConversionException(e.getMessage());
        }


        map.forEach(layer -> {
            if (layer instanceof TileLayer) {
                final TileLayer tileLayer = (TileLayer) layer;

                for (int x = 0; x < map.getWidth(); x++) {
                    for (int y = 0; y < map.getHeight(); y++) {
                        final Tile tile = tileLayer.getTileAt(x, y);
                        if (doesTileExists(tile)) {
                            final Image tileImage = SwingFXUtils.toFXImage(tile.getImage(), null);


                            final ImageView tileView = new ImageView(tileImage);
                            tileView.setFitWidth(ViewUtils.metersToPixels(ModelSettings.TILE_SIZE));
                            tileView.setFitHeight(ViewUtils.metersToPixels(ModelSettings.TILE_SIZE));
                            tileView.setTranslateX(x * ViewUtils.metersToPixels(ModelSettings.TILE_SIZE));
                            tileView.setTranslateY(y * ViewUtils.metersToPixels(ModelSettings.TILE_SIZE));
                            worldView.getChildren().add(tileView);
                        }
                    }
                }

                gameWorld.bodyFactory()
                        .createTerrainFromGrid(Point2D.ZERO,
                                new Dimension2D(ModelSettings.TILE_SIZE, ModelSettings.TILE_SIZE),
                                new SimpleCollisionGrid(map.getHeight(), map.getWidth(), (row, column) ->  {
                                        return doesTileExists(tileLayer.getTileAt(column, row));
                                })
                         );
            }
        });

        return map;
    }

    private boolean doesTileExists(final Tile tile) {
        return tile != null && tile.getImage() != null;
    }

    private Point2D tilePositionToWorld(final int x, final int y) {
        return new Point2D(x * ModelSettings.TILE_SIZE + ModelSettings.TILE_SIZE / 2,
                -(y * ModelSettings.TILE_SIZE + ModelSettings.TILE_SIZE / 2));
    }
}
