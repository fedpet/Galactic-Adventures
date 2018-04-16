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
import it.unibo.oop17.ga_game.utils.FXUtils;
import it.unibo.oop17.ga_game.utils.InfiniteSequence;
import it.unibo.oop17.ga_game.view.GameWorldView;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.util.Pair;

/**
 * Controls the level loading.
 */
public final class LoadLevelImpl implements LoadLevel {

    private static final int G_COIN_VALUE = 100;
    private static final int S_COIN_VALUE = 50;
    private static final int B_COIN_VALUE = 25;
    private static final int FLYING_VALUE = 5;

    private Entity player;
    private final Map map;
    private final GameWorld model;
    private final GameWorldView view;
    private final Set<EntityController> entities;
    private final MainController mainController;

    /**
     * Constructor for LoadLevel.
     * @param map
     *          Map to load.
     * @param model
     *          World model.
     * @param view
     *          World view.
     * @param mainController
     *          Main controller.
     */
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

                model.addTerrain(pos.getKey(), pos.getValue());
            });
        }
        if (layer.getName().trim().toLowerCase(Locale.UK).equals("objects")) {
            layer.forEach(mapObj -> {
                final Point2D position = FXUtils.invertY(new Point2D(mapObj.getX() / 70, mapObj.getY() / 70));
                final String type = mapObj.getType();
                Entity entity;
                switch (type) {
                case "player":
                    player = model.spawnEntity(body -> new Player(body, position));
                    entities.add(
                            new PlayerController(view.getPlayerInput(), player, view.entityFactory().createPlayer()));
                    player.register(new DeathEventListener(mainController));
                    break;
                case "keyR":
                    entity = model.spawnEntity(body -> new Key(body, position, KeyLockType.RED));
                    entities.add(new LifelessEntityController(entity, view.entityFactory().createKey(KeyLockType.RED)));
                    break;
                case "keyB":
                    entity = model.spawnEntity(body -> new Key(body, position, KeyLockType.BLUE));
                    entities.add(new LifelessEntityController(entity, view.entityFactory().createKey(KeyLockType.BLUE)));
                    break;
                case "keyG":
                    entity = model.spawnEntity(body -> new Key(body, position, KeyLockType.GREEN));
                    entities.add(new LifelessEntityController(entity, view.entityFactory().createKey(KeyLockType.GREEN)));
                    break;
                case "keyY":
                    entity = model.spawnEntity(body -> new Key(body, position, KeyLockType.YELLOW));
                    entities.add(new LifelessEntityController(entity, view.entityFactory().createKey(KeyLockType.YELLOW)));
                    break;
                case "redLock":
                    entity = model.spawnEntity(body -> new Lock(body, position, KeyLockType.RED));
                    entities.add(
                            new LifelessEntityController(entity, view.entityFactory().createLock(KeyLockType.RED)));
                    break;
                case "blueLock":
                    entity = model.spawnEntity(body -> new Lock(body, position, KeyLockType.BLUE));
                    entities.add(
                            new LifelessEntityController(entity, view.entityFactory().createLock(KeyLockType.BLUE)));
                    break;
                case "greenLock":
                    entity = model.spawnEntity(body -> new Lock(body, position, KeyLockType.GREEN));
                    entities.add(
                            new LifelessEntityController(entity, view.entityFactory().createLock(KeyLockType.GREEN)));
                    break;
                case "yellowLock":
                    entity = model.spawnEntity(body -> new Lock(body, position, KeyLockType.YELLOW));
                    entities.add(
                            new LifelessEntityController(entity, view.entityFactory().createLock(KeyLockType.YELLOW)));
                    break;
                case "lever":
                    entity = model.spawnEntity(body -> new Lever(body, position, "door", false));
                    entities.add(new TriggerEntityController(entity, view.entityFactory().createLever()));
                    break;
                case "door":
                    entity = model.spawnEntity(body -> new Door(body, position, "door", false));
                    entities.add(new TriggerEntityController(entity, view.entityFactory().createDoor()));
                    entity.register(new DoorEventListener(this.mainController));
                    break;
                case "gCoin":
                    entity = model.spawnEntity(body -> new Coin(body, position, G_COIN_VALUE));
                    entities.add(new LifelessEntityController(entity, view.entityFactory().createCoin(CoinType.GOLD)));
                    break;
                case "sCoin":
                    entity = model.spawnEntity(body -> new Coin(body, position, S_COIN_VALUE));
                    entities.add(
                            new LifelessEntityController(entity, view.entityFactory().createCoin(CoinType.SILVER)));
                    break;
                case "bCoin":
                    entity = model.spawnEntity(body -> new Coin(body, position, B_COIN_VALUE));
                    entities.add(
                            new LifelessEntityController(entity, view.entityFactory().createCoin(CoinType.BRONZE)));
                    break;
                case "flying":
                    entity = model.spawnEntity(body -> new FlyingEnemy(body, position, InfiniteSequence
                            .repeat(() -> new CircleIterator(position, FLYING_VALUE, FLYING_VALUE))));
                    entities.add(new LivingEntityController(entity, view.entityFactory().createBee()));
                    break;
                case "torch":
                    //TODO
                    break;
                case "spikes":
                    entity = model.spawnEntity(body -> new Spikes(body, position));
                    entities.add(new LifelessEntityController(entity, view.entityFactory().createSpikes()));
                    break;
                case "spring":
                    entity = model.spawnEntity(body -> new JumpingPlatform(body, position));
                    entities.add(new LifelessEntityController(entity, view.entityFactory().createJumpingPlatform()));
                    break;
                case "platform":
                    entity = model.spawnEntity(body -> new MovingPlatform(body, position, new Dimension2D(3, 1), InfiniteSequence
                            .repeat(() -> new CircleIterator(position, FLYING_VALUE, FLYING_VALUE))));
                    entities.add(new LifelessEntityController(entity, view.entityFactory().createMovingPlatform()));
                    break;
                case "slime":
                default:
                    entity = model.spawnEntity(body -> new SlimeEnemy(body, position));
                    entities.add(new LivingEntityController(entity, view.entityFactory().createSlime()));
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
    public Entity getPlayer() {
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
