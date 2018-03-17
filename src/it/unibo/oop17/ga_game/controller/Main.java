package it.unibo.oop17.ga_game.controller;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

import org.mapeditor.core.Map;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

import it.unibo.oop17.ga_game.model.CircleIterator;
import it.unibo.oop17.ga_game.model.CoinType;
import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.ModelSettings;
import it.unibo.oop17.ga_game.model.entities.Coin;
import it.unibo.oop17.ga_game.model.entities.FlyingEnemy;
import it.unibo.oop17.ga_game.model.entities.Key;
import it.unibo.oop17.ga_game.model.entities.Lock;
import it.unibo.oop17.ga_game.model.entities.MovingPlatform;
import it.unibo.oop17.ga_game.model.entities.Player;
import it.unibo.oop17.ga_game.model.entities.SlimeEnemy;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import it.unibo.oop17.ga_game.utils.InfiniteSequence;
import it.unibo.oop17.ga_game.utils.ShapePerimeterIterator;
import it.unibo.oop17.ga_game.utils.SimpleCollisionGrid;
import it.unibo.oop17.ga_game.view.ViewUtils;
import it.unibo.oop17.ga_game.view.entities.CoinView;
import it.unibo.oop17.ga_game.view.entities.EntityView;
import it.unibo.oop17.ga_game.view.entities.FlyingEnemyView;
import it.unibo.oop17.ga_game.view.entities.KeyView;
import it.unibo.oop17.ga_game.view.entities.LockView;
import it.unibo.oop17.ga_game.view.entities.PlayerView;
import it.unibo.oop17.ga_game.view.entities.SlimeEnemyView;
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

        final ImageView platformView = new ImageView(new Image("/tiles/base_pack/tiles/stone.png"));
        final ImageView platformView2 = new ImageView(new Image("/tiles/base_pack/tiles/stone.png"));
        final SlimeEnemy slimeEnemy = new SlimeEnemy(bodyFactory, new Point2D(4, -4));
        gameWorld.addEntity(slimeEnemy);
        final EntityView slimeEnemyView = new SlimeEnemyView(worldView);
        final FlyingEnemy flyingEnemy = new FlyingEnemy(bodyFactory, new Point2D(4, -4), InfiniteSequence
                .repeat(() -> new CircleIterator(new Point2D(4, -4), 5, 5)));
        gameWorld.addEntity(flyingEnemy);
        final EntityView flyingEnemyView = new FlyingEnemyView(worldView);
        final Coin coin = new Coin(bodyFactory, new Point2D(2, -28.5), 100);
        gameWorld.addEntity(coin);
        final EntityView coinView = new CoinView(worldView, CoinType.BRONZE);
        final Key blueKey = new Key(bodyFactory, new Point2D(10, -28), KeyLockType.BLUE);
        gameWorld.addEntity(blueKey);
        final EntityView blueKeyView = new KeyView(worldView, KeyLockType.BLUE);
        final Key redKey = new Key(bodyFactory, new Point2D(7, -25.5), KeyLockType.RED);
        gameWorld.addEntity(redKey);
        final EntityView redKeyView = new KeyView(worldView, KeyLockType.RED);
        final Lock lock = new Lock(bodyFactory, new Point2D(14, -28.5), KeyLockType.BLUE);
        gameWorld.addEntity(lock);
        final EntityView lockView = new LockView(worldView, KeyLockType.BLUE);

        platformView.setFitWidth(ViewUtils.metersToPixels(platform.getBody().getDimension().getWidth()));
        platformView.setFitHeight(ViewUtils.metersToPixels(platform.getBody().getDimension().getHeight()));
        platformView2.setFitWidth(ViewUtils.metersToPixels(platform2.getBody().getDimension().getWidth()));
        platformView2.setFitHeight(ViewUtils.metersToPixels(platform2.getBody().getDimension().getHeight()));
        worldView.getChildren().add(platformView);
        worldView.getChildren().add(platformView2);

        final PlayerView playerView = new PlayerView(worldView);
        final EntityController playerController = new PlayerController(new KeyboardInputController(scene), player,
                playerView);
        final EntityController basicEnemyController = new UnplayableEntityController(slimeEnemy, slimeEnemyView);
        final EntityController flyingEnemyController = new UnplayableEntityController(flyingEnemy, flyingEnemyView);
        final EntityController coinController = new UnplayableEntityController(coin, coinView);
        final EntityController blueKeyController = new UnplayableEntityController(blueKey, blueKeyView);
        final EntityController redKeyController = new UnplayableEntityController(redKey, redKeyView);
        final EntityController lockController = new UnplayableEntityController(lock, lockView);


        try {
            final Map map = loadLevel("res\\level1.tmx");
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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

    private Map loadLevel(final String path) throws Exception {
        final Map map = new TMXMapReader().readMap(path); // readMap throws a generic Exception :(


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
