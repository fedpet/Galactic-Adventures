package it.unibo.oop17.ga_game.view;

import org.mapeditor.core.Map;
import org.mapeditor.core.ObjectGroup;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

import it.unibo.oop17.ga_game.controller.ConfigData;
import it.unibo.oop17.ga_game.controller.EntityController;
import it.unibo.oop17.ga_game.model.CoinType;
import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.Level;
import it.unibo.oop17.ga_game.model.ModelSettings;
import it.unibo.oop17.ga_game.model.entities.Coin;
import it.unibo.oop17.ga_game.model.entities.Player;
import it.unibo.oop17.ga_game.model.entities.SlimeEnemy;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import it.unibo.oop17.ga_game.utils.SimpleCollisionGrid;
import it.unibo.oop17.ga_game.view.entities.CoinView;
import it.unibo.oop17.ga_game.view.entities.LivingEntityView;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class GameScreen extends Parent {
    
    private static final double FRAMERATE = 1.0 / 60;
    private final GameWorld gameWorld;
    private final Group worldView;
    private final BodyFactory bodyFactory;
//    private PlayerView playerView;
//    private EntityController playerController;
//    private EntityController coinController;
//    private EntityController basicEnemyController;
    
    public GameScreen(final Scene scene, final Level level, final ConfigData data) {
        
        this.gameWorld = new GameWorld();
        this.worldView = new Group();
        
        
        // PER ORA SI
//        this.playerView = null;
//        this.playerController = null;
//        this.coinController = null;
//        this.basicEnemyController = null;
        
        
        
        this.bodyFactory = gameWorld.bodyFactory();
        
        final MediaPlayer mediaPlayer = new MediaPlayer(new Media(level.getMusic()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(data.getMusicVol().getVolume());
        mediaPlayer.play();
        
        try {
            loadLevel(level.getPath());
        } catch (final Exception e) {
            System.err.println("ERROR: RESOURCES NOT FOUND!");
            Platform.exit();
        }
        
        final Timeline timer = new Timeline(new KeyFrame(
                Duration.seconds(FRAMERATE),
                event -> {
                    gameWorld.update(FRAMERATE);

//                    basicEnemyController.update();
//                    coinController.update();
//                    playerController.update();

//                    worldView.setTranslateX(-playerView.getPosition().getX() + scene.getWidth() / 2);
//                    worldView.setTranslateY(-playerView.getPosition().getY() + scene.getHeight() / 2);
                }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }
    
    private Map loadLevel(final String path) {
        
        try {
            final Map map = new TMXMapReader().readMap(path);
            map.forEach(layer -> {
                if (layer instanceof ObjectGroup) {
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
                    if (layer.getName().equals("ground")) {
                        gameWorld.bodyFactory()
                                .createTerrainFromGrid(Point2D.ZERO,
                                        new Dimension2D(ModelSettings.TILE_SIZE, ModelSettings.TILE_SIZE),
                                        new SimpleCollisionGrid(map.getHeight(), map.getWidth(), (row, column) ->  {
                                                return doesTileExists(tileLayer.getTileAt(column, row));
                                        })
                                 );
                    }
                }
                 else if (layer.getName().equals("entities")) {
                    final ObjectGroup objGroup = (ObjectGroup) layer;
                    objGroup.forEach(obj -> {
                        final Tile tile = obj.getTile();
                        if (tile.getType().equals("player")) {
                            final Player player = new Player(bodyFactory, new Point2D(obj.getX(), obj.getY()));
//                            this.playerView = new PlayerView(worldView);
//                            this.playerController = new PlayerController(new KeyboardInputController(scene), player,
//                                    playerView);
                            gameWorld.addEntity(player);
                        }
                        if (tile.getType().equals("goldCoin")) {
                            final Coin coin = new Coin(bodyFactory, new Point2D(obj.getX(), obj.getY()), 100);
                            final CoinView coinView = new CoinView(worldView, CoinType.BRONZE);
//                            this.coinController = new DeadEntityController(coin, coinView);
                            gameWorld.addEntity(coin);
                            
                        }
                        if (tile.getType().equals("slimeEnemy")) {
                            final SlimeEnemy slimeEnemy = new SlimeEnemy(bodyFactory, new Point2D(obj.getX(), obj.getY()));
                            final LivingEntityView slimeEnemyView = new SlimeEnemyView(worldView);
//                            this.basicEnemyController = new UnplayableLivingEntityController(slimeEnemy, slimeEnemyView);
                            gameWorld.addEntity(slimeEnemy);
                        }
                    });
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
