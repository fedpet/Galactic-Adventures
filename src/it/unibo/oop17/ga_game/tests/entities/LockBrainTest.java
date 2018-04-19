package it.unibo.oop17.ga_game.tests.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.entities.components.Inventory;
import it.unibo.oop17.ga_game.model.entities.components.InventoryImpl;
import it.unibo.oop17.ga_game.model.entities.components.LockBrain;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Tests @LockBrain.
 */
public class LockBrainTest extends BaseEntityTest {

    private static final Dimension2D ENTITY_SIZE = new Dimension2D(1, 1);
    private static final double LITTLE_DISTANCE_FROM_OTHER_BODY = 0.1;
    private Inventory inventory;
    private TestEntity redLock;
    private TestEntity greenLock;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        final TestEntity entity = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        inventory = new InventoryImpl();
        entity.add(inventory);

        redLock = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        redLock.add(new LockBrain(KeyLockType.RED));
        greenLock = spawnTestEntity(new Point2D(LITTLE_DISTANCE_FROM_OTHER_BODY, 0), ENTITY_SIZE);
        greenLock.add(new LockBrain(KeyLockType.GREEN));
    }

    /**
     * A lock must be destroyed only if it's in contact with an entity with an inventory
     * containing a key of the same color of the lock in its keys bunch.
     */
    @Test
    public final void testLockDisappear() {

        advanceSimulation(1);
        assertFalse(
                "The entity with a LockBrain object shouldn't be destroyed in contact with an entity without an inventory",
                redLock.getEvents().stream().filter(e -> e instanceof DestructionEvent).count() > 0);
        assertFalse(
                "The entity with a LockBrain object shouldn't be destroyed in contact with an entity without an inventory",
                greenLock.getEvents().stream().filter(e -> e instanceof DestructionEvent).count() > 0);

        inventory.add(KeyLockType.RED);
        advanceSimulation(1);
        assertTrue(
                "The entity with a LockBrain object should be destroyed in contact with an entity with an inventory containing a key of the same type",
                redLock.getEvents().stream().filter(e -> e instanceof DestructionEvent).count() > 0);
        assertFalse(
                "The entity with a LockBrain object should be destroyed in contact with an entity with an inventory not containing a key of the same type",
                greenLock.getEvents().stream().filter(e -> e instanceof DestructionEvent).count() > 0);

        inventory.add(KeyLockType.GREEN);
        advanceSimulation(1);
        assertTrue(
                "The entity with a LockBrain object should be destroyed in contact with an entity with an inventory containing a key of the same type",
                greenLock.getEvents().stream().filter(e -> e instanceof DestructionEvent).count() > 0);

    }

}
