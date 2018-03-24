package it.unibo.oop17.ga_game.view;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

import org.mapeditor.core.Map;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

import it.unibo.oop17.ga_game.controller.DeadEntityController;
import it.unibo.oop17.ga_game.controller.EntityController;
import it.unibo.oop17.ga_game.controller.PlayerController;
import it.unibo.oop17.ga_game.controller.UnplayableLivingEntityController;
import it.unibo.oop17.ga_game.model.CircleIterator;
import it.unibo.oop17.ga_game.model.CoinType;
import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.ModelSettings;
import it.unibo.oop17.ga_game.model.entities.Coin;
import it.unibo.oop17.ga_game.model.entities.Door;
import it.unibo.oop17.ga_game.model.entities.FlyingEnemy;
import it.unibo.oop17.ga_game.model.entities.Key;
import it.unibo.oop17.ga_game.model.entities.Lever;
import it.unibo.oop17.ga_game.model.entities.Lock;
import it.unibo.oop17.ga_game.model.entities.MovingPlatform;
import it.unibo.oop17.ga_game.model.entities.Player;
import it.unibo.oop17.ga_game.model.entities.SlimeEnemy;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import it.unibo.oop17.ga_game.utils.InfiniteSequence;
import it.unibo.oop17.ga_game.utils.ShapePerimeterIterator;
import it.unibo.oop17.ga_game.utils.SimpleCollisionGrid;
import it.unibo.oop17.ga_game.view.entities.CoinView;
import it.unibo.oop17.ga_game.view.entities.DeadEntityView;
import it.unibo.oop17.ga_game.view.entities.DoorView;
import it.unibo.oop17.ga_game.view.entities.FlyingEnemyView;
import it.unibo.oop17.ga_game.view.entities.KeyView;
import it.unibo.oop17.ga_game.view.entities.LeverView;
import it.unibo.oop17.ga_game.view.entities.LivingEntityView;
import it.unibo.oop17.ga_game.view.entities.LockView;
import it.unibo.oop17.ga_game.view.entities.PlayerView;
import it.unibo.oop17.ga_game.view.entities.SlimeEnemyView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GameScreenTest extends Parent {
    
    private static final double FRAMERATE = 1.0 / 60;
    private final GameWorld gameWorld;
    private final Group worldView;
    
    public GameScreenTest(final Scene scene) {
        
        this.gameWorld = new GameWorld();
        this.worldView = new Group();
        
//        CARICA MAPPA ED ENTITA'
        
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
        final LivingEntityView slimeEnemyView = new SlimeEnemyView(worldView);
        final FlyingEnemy flyingEnemy = new FlyingEnemy(bodyFactory, new Point2D(4, -4), InfiniteSequence
                .repeat(() -> new CircleIterator(new Point2D(4, -4), 5, 5)));
        gameWorld.addEntity(flyingEnemy);
        final LivingEntityView flyingEnemyView = new FlyingEnemyView(worldView);
        final Coin coin = new Coin(bodyFactory, new Point2D(2, -28.5), 100);
        gameWorld.addEntity(coin);
        final DeadEntityView coinView = new CoinView(worldView, CoinType.BRONZE);
        final Key blueKey = new Key(bodyFactory, new Point2D(10, -28), KeyLockType.BLUE);
        gameWorld.addEntity(blueKey);
        final DeadEntityView blueKeyView = new KeyView(worldView, KeyLockType.BLUE);
        final Key redKey = new Key(bodyFactory, new Point2D(7, -25.5), KeyLockType.RED);
        gameWorld.addEntity(redKey);
        final DeadEntityView redKeyView = new KeyView(worldView, KeyLockType.RED);
        final Lock lock = new Lock(bodyFactory, new Point2D(14, -28.5), KeyLockType.BLUE);
        gameWorld.addEntity(lock);
        final DeadEntityView lockView = new LockView(worldView, KeyLockType.BLUE);
        final Lever lever = new Lever(bodyFactory, new Point2D(18, -25.5), "Door", false);
        gameWorld.addEntity(lever);
        final DeadEntityView leverView = new LeverView(worldView, false);
        final Door door = new Door(bodyFactory, new Point2D(12, -25.5), "Door", false);
        gameWorld.addEntity(door);
        final DeadEntityView doorView = new DoorView(worldView, false);

        platformView.setFitWidth(ViewUtils.metersToPixels(platform.getBody().getDimension().getWidth()));
        platformView.setFitHeight(ViewUtils.metersToPixels(platform.getBody().getDimension().getHeight()));
        platformView2.setFitWidth(ViewUtils.metersToPixels(platform2.getBody().getDimension().getWidth()));
        platformView2.setFitHeight(ViewUtils.metersToPixels(platform2.getBody().getDimension().getHeight()));
        worldView.getChildren().add(platformView);
        worldView.getChildren().add(platformView2);

        final PlayerView playerView = new PlayerView(worldView);
        final EntityController playerController = new PlayerController(new PlayerKeyboardInput(scene), player,
                playerView);
        final EntityController basicEnemyController = new UnplayableLivingEntityController(slimeEnemy, slimeEnemyView);
        final EntityController flyingEnemyController = new UnplayableLivingEntityController(flyingEnemy,
                flyingEnemyView);
        final EntityController coinController = new DeadEntityController(coin, coinView);
        final EntityController blueKeyController = new DeadEntityController(blueKey, blueKeyView);
        final EntityController redKeyController = new DeadEntityController(redKey, redKeyView);
        final EntityController lockController = new DeadEntityController(lock, lockView);
        final EntityController leverController = new DeadEntityController(lever, leverView);
        final EntityController doorController = new DeadEntityController(door, doorView);


        try {
            loadLevel("res\\level1.tmx");
        } catch (final Exception e) {
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
                    leverController.update();
                    doorController.update();

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

private Map loadLevel(final String path) {
        
        
        try {
            final Map map = new TMXMapReader().readMap(path);
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    private boolean doesTileExists(final Tile tile) {
        return tile != null && tile.getImage() != null;
    }
    
    public Group getWorldView() {
        return this.worldView;
    }

}
