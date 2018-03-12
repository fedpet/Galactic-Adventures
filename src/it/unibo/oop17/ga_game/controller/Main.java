package it.unibo.oop17.ga_game.controller;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

import org.mapeditor.core.Map;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

import it.unibo.oop17.ga_game.model.InfiniteSequence;
import it.unibo.oop17.ga_game.model.ModelSettings;
import it.unibo.oop17.ga_game.model.ShapePerimeterIterator;
import it.unibo.oop17.ga_game.model.entities.BasicEnemy;
import it.unibo.oop17.ga_game.model.entities.FlyingEnemy;
import it.unibo.oop17.ga_game.model.entities.MovingPlatform;
import it.unibo.oop17.ga_game.model.entities.Player;
import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import it.unibo.oop17.ga_game.utils.SimpleCollisionGrid;
import it.unibo.oop17.ga_game.view.BasicEnemyView;
import it.unibo.oop17.ga_game.view.FlyingEnemyView;
import it.unibo.oop17.ga_game.view.ViewUtils;
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
    private final PhysicsEngine physics = PhysicsEngine.create(new Point2D(0, -60));
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

        final Player player = new Player(physics, new Point2D(4, -4));
        final ImageView playerView = new ImageView(new Image("/p1_stand.png"));
        final MovingPlatform platform = new MovingPlatform(physics, new Point2D(4, -10), new Dimension2D(4, 1),
                InfiniteSequence.backAndForth(Arrays.asList(new Point2D(4, -5), new Point2D(4, -25))));

        final Shape shape = new Ellipse2D.Double(8, -22, 5, 5);
        final InfiniteSequence<Point2D> circlePattern = InfiniteSequence
                .repeat(() -> new ShapePerimeterIterator(shape));
        final MovingPlatform platform2 = new MovingPlatform(physics, new Point2D(4, -26), new Dimension2D(4, 1),
                circlePattern);

        final ImageView platformView = new ImageView(new Image("/tiles/base_pack/tiles/stone.png"));
        final ImageView platformView2 = new ImageView(new Image("/tiles/base_pack/tiles/stone.png"));
        final BasicEnemy basicEnemy = new BasicEnemy(physics, new Point2D(4, -4));
        final BasicEnemyView basicEnemyView = new BasicEnemyView(basicEnemy);
        final FlyingEnemy flyingEnemy = new FlyingEnemy(physics, new Point2D(4, -4));
        final FlyingEnemyView flyingEnemyView = new FlyingEnemyView(flyingEnemy);

        playerView.setFitWidth(ViewUtils.metersToPixels(player.getBody().getDimension().getWidth()));
        playerView.setFitHeight(ViewUtils.metersToPixels(player.getBody().getDimension().getHeight()));
        platformView.setFitWidth(ViewUtils.metersToPixels(platform.getBody().getDimension().getWidth()));
        platformView.setFitHeight(ViewUtils.metersToPixels(platform.getBody().getDimension().getHeight()));
        platformView2.setFitWidth(ViewUtils.metersToPixels(platform2.getBody().getDimension().getWidth()));
        platformView2.setFitHeight(ViewUtils.metersToPixels(platform2.getBody().getDimension().getHeight()));
        basicEnemyView.setFitWidth(ViewUtils.metersToPixels(basicEnemy.getBody().getDimension().getWidth()));
        basicEnemyView.setFitHeight(ViewUtils.metersToPixels(basicEnemy.getBody().getDimension().getHeight()));
        flyingEnemyView.setFitWidth(ViewUtils.metersToPixels(flyingEnemy.getBody().getDimension().getWidth()));
        flyingEnemyView.setFitHeight(ViewUtils.metersToPixels(flyingEnemy.getBody().getDimension().getHeight()));
        worldView.getChildren().add(playerView);
        worldView.getChildren().add(platformView);
        worldView.getChildren().add(platformView2);
        worldView.getChildren().add(basicEnemyView);
        worldView.getChildren().add(flyingEnemyView);

        new PlayerController(new KeyboardInputController(scene), player);
        final BasicEnemyController basicEnemyController = new BasicEnemyController(basicEnemy, basicEnemyView);
        final FlyingEnemyController flyingEnemyController = new FlyingEnemyController(flyingEnemy, flyingEnemyView);


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

                    player.update(FRAMERATE);
                    platform.update(FRAMERATE);
                    platform2.update(FRAMERATE);
                    basicEnemy.update(FRAMERATE);
                    flyingEnemy.update(FRAMERATE);
                    physics.update(FRAMERATE);

                    basicEnemyController.updateView();
                    flyingEnemyController.updateView();

                    Point2D pt = ViewUtils.worldPointToFX(player.getBody().getPosition());
                    playerView.setTranslateX(pt.getX() - playerView.getBoundsInLocal().getWidth() / 2);
                    playerView.setTranslateY(pt.getY() - playerView.getBoundsInLocal().getHeight() / 2);

                    pt = ViewUtils.worldPointToFX(platform.getBody().getPosition());
                    platformView.setTranslateX(pt.getX() - platformView.getBoundsInLocal().getWidth() / 2);
                    platformView.setTranslateY(pt.getY() - platformView.getBoundsInLocal().getHeight() / 2);
                    pt = ViewUtils.worldPointToFX(platform2.getBody().getPosition());
                    platformView2.setTranslateX(pt.getX() - platformView2.getBoundsInLocal().getWidth() / 2);
                    platformView2.setTranslateY(pt.getY() - platformView2.getBoundsInLocal().getHeight() / 2);
                    pt = ViewUtils.worldPointToFX(basicEnemy.getBody().getPosition());
                    basicEnemyView.setTranslateX(pt.getX() - basicEnemyView.getBoundsInLocal().getWidth() / 2);
                    basicEnemyView.setTranslateY(pt.getY() - basicEnemyView.getBoundsInLocal().getHeight() / 2);
                    pt = ViewUtils.worldPointToFX(flyingEnemy.getBody().getPosition());
                    flyingEnemyView.setTranslateX(pt.getX() - flyingEnemyView.getBoundsInLocal().getWidth() / 2);
                    flyingEnemyView.setTranslateY(pt.getY() - flyingEnemyView.getBoundsInLocal().getHeight() / 2);

                    System.out.println("player " + playerView.getTranslateX() + "," + playerView.getTranslateY());
                    System.out.println("playerWinLocal " + playerView.getBoundsInLocal());
                    System.out.println("scnee " + scene.getWidth() + "," + scene.getHeight());
                    System.out.println("worldView " + worldView.getTranslateX() + "," + worldView.getTranslateY());

                    worldView.setTranslateX(-playerView.getTranslateX() + scene.getWidth() / 2);
                    worldView.setTranslateY(-playerView.getTranslateY() + scene.getHeight() / 2);

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

                physics.bodyFactory()
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
