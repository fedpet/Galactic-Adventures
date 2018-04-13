package it.unibo.oop17.ga_game.controller;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import org.mapeditor.core.Map;
import org.mapeditor.core.ObjectGroup;
import org.mapeditor.core.TileLayer;

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
import it.unibo.oop17.ga_game.model.entities.Player;
import it.unibo.oop17.ga_game.model.entities.SlimeEnemy;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import it.unibo.oop17.ga_game.utils.FXUtils;
import it.unibo.oop17.ga_game.utils.InfiniteSequence;
import it.unibo.oop17.ga_game.view.GameWorldView;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Pair;

public class LoadLevelImpl implements LoadLevel {
    
    private Player player;
    private final Map map;
    private final GameWorld model;
    private final GameWorldView view;
    private final Set<EntityController> entities;
    private final MainController mainController;
//    private final Image background;

    public LoadLevelImpl(final Map map, final GameWorld model, final GameWorldView view, final MainController mainController) {
        
        this.map = map;
        this.model = model;
        this.view = view;
        this.mainController = mainController;
        entities = new LinkedHashSet<>();
        this.map.forEach(layer -> {
            if (layer instanceof TileLayer) {
                loadTiles((TileLayer) layer);
            } else if (layer instanceof ObjectGroup) {
                loadObjects((ObjectGroup) layer);
            }
        });
        
    }

    private void loadObjects(final ObjectGroup layer) {
        final ObjectGroup objLayer = layer;
        if (objLayer.getName().trim().toLowerCase(Locale.UK).equals("solid")) {
            objLayer.forEach(obj -> {
                final Pair<Point2D, Dimension2D> pos = mapPositionToWorld(this.map, obj.getX(), obj.getY(),
                        obj.getWidth(), obj.getHeight());

                model.bodyBuilder().position(pos.getKey())
                        .size(pos.getValue())
                        .moveable(false)
                        .friction(0)
                        .build();
            });
        }
        if (layer.getName().trim().toLowerCase(Locale.UK).equals("objects") || layer.getName().trim().toLowerCase(Locale.UK).equals("background")) {
            layer.forEach(mapObj -> { 
                final Point2D position = FXUtils.invertY(new Point2D(mapObj.getX() / 70, mapObj.getY() / 70));
                final String type = mapObj.getType();
                final BodyBuilder bodyBuilder = model.bodyBuilder();
                switch (type) {
                case "background":
//                    background = SwingFXUtils.toFXImage(mapObj.getImage(0), null);
                    break;
                case "player":
                    player = new Player(bodyBuilder, position);
                    model.addEntity(player);
                    entities.add(
                            new PlayerController(view.getPlayerInput(), player, view.entityFactory().createPlayer()));
                    break;
                case "keyR":
                    final Key keyR = new Key(bodyBuilder, position, KeyLockType.RED);
                    model.addEntity(keyR);
                    entities.add(new LifelessEntityController(keyR, view.entityFactory().createKey(KeyLockType.RED)));
                    break;
                case "redLock":
                    final Lock lockR = new Lock(bodyBuilder, position, KeyLockType.RED);
                    model.addEntity(lockR);
                    entities.add(new LifelessEntityController(lockR, view.entityFactory().createLock(KeyLockType.RED)));
                    break;
                case "lever":
                    final Lever lever = new Lever(bodyBuilder, position, "door", false);
                    model.addEntity(lever);
                    entities.add(new TriggerEntityController(lever, view.entityFactory().createLever()));
                    break;
                case "door":
                    final Door door = new Door(bodyBuilder, position, "door", false);
                    model.addEntity(door);
                    entities.add(new TriggerEntityController(door, view.entityFactory().createDoor()));
                    door.register(new DoorEventListener(this.mainController));
                    break;
                case "gCoin":
                    final Coin gCoin = new Coin(bodyBuilder, position, 100);
                    model.addEntity(gCoin);
                    entities.add(new LifelessEntityController(gCoin, view.entityFactory().createCoin(CoinType.GOLD)));
                    break;
                case "sCoin":
                    final Coin sCoin = new Coin(bodyBuilder, position, 50);
                    model.addEntity(sCoin);
                    entities.add(new LifelessEntityController(sCoin, view.entityFactory().createCoin(CoinType.SILVER)));
                    break;
                case "bCoin":
                    final Coin bCoin = new Coin(bodyBuilder, position, 25);
                    model.addEntity(bCoin);
                    entities.add(new LifelessEntityController(bCoin, view.entityFactory().createCoin(CoinType.BRONZE)));
                    break;
                case "flying":
                    final FlyingEnemy flying = new FlyingEnemy(bodyBuilder, position, InfiniteSequence
                            .repeat(() -> new CircleIterator(position, 5, 5)));
                    model.addEntity(flying);
                    entities.add(new LivingEntityController(flying, view.entityFactory().createBee()));
                    break;
                case "torch":
                    break;
                case "spikes":
                    break;
                case "spring":
                    break;
                case "platform":
                    break;
                case "slime":
                default:
                    final SlimeEnemy slime = new SlimeEnemy(bodyBuilder, position);
                    model.addEntity(slime);
                    entities.add(new LivingEntityController(slime, view.entityFactory().createSlime()));
                    break;
                }
            });
        }
    }

    private void loadTiles(final TileLayer layer) {
        view.showTerrain(layer, Point2D.ZERO,
                new Dimension2D(ModelSettings.TILE_SIZE, ModelSettings.TILE_SIZE));
    }
    
    private Pair<Point2D, Dimension2D> mapPositionToWorld(final Map map,
            final double x, final double y,
            final double width, final double height) {
        final Dimension2D dim = new Dimension2D(width / map.getTileWidth(),
                height / map.getTileHeight());
        final Point2D pos = new Point2D(x / map.getTileWidth() + dim.getWidth() / 2,
                -(y / map.getTileHeight() + dim.getHeight() / 2));

        return new Pair<>(pos, dim);
    }
    
    @Override
    public Player getPlayer() {
        return this.player;
    }
    
    @Override
    public GameWorld getGameWorld() {
        return this.model;
    }
    
    @Override
    public GameWorldView getGameWorldView() {
        return this.view;
    }

    @Override
    public Set<EntityController> getEntities() {
        return this.entities;
    }
}
