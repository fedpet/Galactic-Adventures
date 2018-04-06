package it.unibo.oop17.ga_game.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import org.mapeditor.core.Map;
import org.mapeditor.core.ObjectGroup;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;

import com.google.common.eventbus.Subscribe;

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
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;
import it.unibo.oop17.ga_game.model.entities.events.FinishedLevelEvent;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import it.unibo.oop17.ga_game.utils.FXUtils;
import it.unibo.oop17.ga_game.utils.InfiniteSequence;
import it.unibo.oop17.ga_game.utils.SimpleCollisionGrid;
import it.unibo.oop17.ga_game.view.GameWorldView;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class LoadLevelImpl implements LoadLevel {
    
    private Player player;
    private final GameWorld model;
    private final GameWorldView view;
    private final Set<EntityController> entities;

    public LoadLevelImpl(final Map map, final GameWorld model, final GameWorldView view) {
        
        this.model = model;
        this.view = view;
        this.entities = new LinkedHashSet<>();
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
            final String type = mapObj.getType();
            switch (type) {
            case "player":
                player = new Player(bodyFactory, position);
                model.addEntity(player);
                entities.add(new PlayerController(view.getPlayerInput(), player, view.entityFactory().createPlayer()));
                break;
            case "keyR":
                final Key keyR = new Key(bodyFactory, position, KeyLockType.RED);
                model.addEntity(keyR);
                entities.add(new LifelessEntityController(keyR, view.entityFactory().createKey(KeyLockType.RED)));
                break;
            case "redLock":
                final Lock lockR = new Lock(bodyFactory, position, KeyLockType.RED);
                model.addEntity(lockR);
                entities.add(new LifelessEntityController(lockR, view.entityFactory().createLock(KeyLockType.RED)));
                break;
            case "lever":
                final Lever lever = new Lever(bodyFactory, position, "door", false);
                model.addEntity(lever);
                entities.add(new TriggerEntityController(lever, view.entityFactory().createLever()));
                break;
            case "door":
                final Door door = new Door(bodyFactory, position, "door", false);
                model.addEntity(door);
                entities.add(new TriggerEntityController(door, view.entityFactory().createDoor()));
                
                class MyDoorEventListener implements EntityEventListener {
                    @Subscribe
                    private void endLevel(final FinishedLevelEvent e) {
                        System.out.println("troll");
                        // deve chiamare toEndLevel nel Main
                    }
                };
                
                door.register(new MyDoorEventListener());
                break;
            case "gCoin":
                final Coin gCoin = new Coin(bodyFactory, position, 100);
                model.addEntity(gCoin);
                entities.add(new LifelessEntityController(gCoin, view.entityFactory().createCoin(CoinType.GOLD)));
                break;
            case "sCoin":
                final Coin sCoin = new Coin(bodyFactory, position, 50);
                model.addEntity(sCoin);
                entities.add(new LifelessEntityController(sCoin, view.entityFactory().createCoin(CoinType.SILVER)));
                break;
            case "bCoin":
                final Coin bCoin = new Coin(bodyFactory, position, 25);
                model.addEntity(bCoin);
                entities.add(new LifelessEntityController(bCoin, view.entityFactory().createCoin(CoinType.BRONZE)));
                break;
            case "flying":
                final FlyingEnemy flying = new FlyingEnemy(bodyFactory, position, InfiniteSequence
                        .repeat(() -> new CircleIterator(position, 5, 5)));
                model.addEntity(flying);
                entities.add(new LivingEntityController(flying, view.entityFactory().createBee()));
                break;
            case "slime":
            default:
                final SlimeEnemy slime = new SlimeEnemy(bodyFactory, position);
                model.addEntity(slime);
                entities.add(new LivingEntityController(slime, view.entityFactory().createSlime()));
                break;
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
