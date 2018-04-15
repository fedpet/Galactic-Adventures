package it.unibo.oop17.ga_game.tests.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop17.ga_game.model.entities.components.Inventory;
import it.unibo.oop17.ga_game.model.entities.components.InventoryImpl;
import it.unibo.oop17.ga_game.model.entities.components.PickupableBrain;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Tests @PickupableBrain.
 */
public class PickupableBrainTest extends BaseEntityTest {

    private static final Dimension2D ENTITY_SIZE = new Dimension2D(1, 1);
    private static final int DEFAULT_MONEY_AMOUNT = 5;
    private TestEntity entity;
    private Inventory inventory;
    private TestEntity pickupable;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        entity = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        inventory = new InventoryImpl();
        pickupable = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        pickupable.add(new PickupableBrain(inventory -> inventory.addMoney(DEFAULT_MONEY_AMOUNT)));
    }

    /**
     * When an Entity with an Inventory becomes in contact with an Entity with a PickupableBrain,
     * this last has to update the inventory only once.
     */
    @Test
    public final void testPickup() {

        advanceSimulation(1);
        assertFalse(
                "The Entity with a PickupableBrain shouldn't be destroyed in contact with an Entity without an Inventory",
                pickupable.getEvents().stream().filter(e -> e instanceof DestructionEvent).count() > 0);

        entity.add(inventory);
        advanceSimulation(1);
        assertTrue(
                "The Entity with a PickupableBrain should be destroyed in contact with an Entity with an Inventory",
                pickupable.getEvents().stream().filter(e -> e instanceof DestructionEvent).count() > 0);
        assertEquals(inventory.getMoney(), DEFAULT_MONEY_AMOUNT);
        advanceSimulation(1);
        assertEquals(inventory.getMoney(), DEFAULT_MONEY_AMOUNT);

    }

}
