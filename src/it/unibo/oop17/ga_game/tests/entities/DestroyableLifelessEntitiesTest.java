package it.unibo.oop17.ga_game.tests.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.entities.components.Brain;
import it.unibo.oop17.ga_game.model.entities.components.FeetComponent;
import it.unibo.oop17.ga_game.model.entities.components.Inventory;
import it.unibo.oop17.ga_game.model.entities.components.InventoryImpl;
import it.unibo.oop17.ga_game.model.entities.components.LockBrain;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.model.entities.components.PickupableBrain;
import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

public class DestroyableLifelessEntitiesTest extends BaseEntityTest {

    private static final Dimension2D ENTITY_SIZE = new Dimension2D(1, 1);
    private static final double WALKING_SPEED = 20.0;
    private static final double JUMPING_SPEED = 20.0;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * When an Entity with an Inventory becomes in contact with an entity
     * with PickupableBrain, this last has to be destroyed (with its components);
     * otherwise, nothing should happen.
     */
    @Test
    public final void testGenericPickupableBrain() {

        final TestEntity collectible = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        collectible.add(new PickupableBrain(inventory -> {
        }));
        assertTrue("The collectible Brain should be present", collectible.get(Brain.class).isPresent());

        final TestEntity player = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        player.add(new InventoryImpl());
        advanceSimulation(1);
        assertFalse("The collectible Brain shouldn't be present anymore", collectible.get(Brain.class).isPresent());

        final TestEntity collectible2 = spawnTestEntity(new Point2D(10, 10), ENTITY_SIZE);
        collectible2.add(new PickupableBrain(inventory -> {
        }));
        spawnTestEntity(new Point2D(10, 10), ENTITY_SIZE);
        advanceSimulation(1);
        assertTrue("The collectible Brain should be still present", collectible2.get(Brain.class).isPresent());

    }

    /**
     * When an Entity with an Inventory becomes in contact with a Coin,
     * this last has to update the money amount in the inventory only once
     * (only in the moment when the two entities collide).
     */
    @Test
    public final void testCoin() {

        final TestEntity coin = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        coin.add(new PickupableBrain(inventory -> inventory.addMoney(10)));
        final TestEntity player = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        player.add(new InventoryImpl());
        advanceSimulation(1);
        assertTrue("The Inventory money amount should be 10", player.get(Inventory.class).get().getMoney() == 10);
        advanceSimulation(1);
        assertTrue("The Inventory money amount shouldn't be changed",
                player.get(Inventory.class).get().getMoney() == 10);

    }

    /**
     * When an Entity with an Inventory becomes in contact with a Key,
     * this last has to update the keys bunch set in the inventory only once.
     */
    @Test
    public final void testKey() {

        final TestEntity key = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        key.add(new PickupableBrain(inventory -> inventory.add(KeyLockType.RED)));
        final TestEntity player = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        player.add(new InventoryImpl());
        advanceSimulation(1);
        assertTrue("The Keys bunch set should contain the red Key",
                player.get(Inventory.class).get().getKeysBunch().contains(KeyLockType.RED));

    }

    /**
     * A Lock must be destroyed only if it's in contact with an Entity with an Inventory
     * containing a Key of the same color of the Lock in its KeysBunch.
     */
    @Test(timeout = 10000)
    public final void testLock() {

        final TestEntity redKey = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        redKey.add(new PickupableBrain(inventory -> inventory.add(KeyLockType.RED)));
        final TestEntity redLock = spawnTestEntity(new Point2D(5, 0), ENTITY_SIZE);
        redLock.add(new LockBrain(KeyLockType.RED));
        final TestEntity greenLock = spawnTestEntity(new Point2D(10, 0), ENTITY_SIZE);
        greenLock.add(new LockBrain(KeyLockType.GREEN));

        final TestEntity player = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        player.add(new InventoryImpl());
        player.add(new FeetComponent(WALKING_SPEED, JUMPING_SPEED));
        while (greenLock.getBody().getContacts().count() == 0) {
            player.get(MovementComponent.class).get().move(PositionCompare.sideToDirection(Side.RIGHT));
            advanceSimulation(1);
        }

        assertFalse("As the Inventory possessor should have a red Key, the red Lock should be destroyed",
                redLock.get(Brain.class).isPresent());
        assertTrue("As the Inventory possessor should have just a red Key, the green Lock shouldn't be destroyed",
                greenLock.get(Brain.class).isPresent());

    }

}
