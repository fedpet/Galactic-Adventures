package it.unibo.oop17.ga_game.tests.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop17.ga_game.model.entities.components.InventoryImpl;
import it.unibo.oop17.ga_game.model.entities.components.TriggerableComponent;
import it.unibo.oop17.ga_game.model.entities.components.TriggerableDoorComponent;
import it.unibo.oop17.ga_game.model.entities.events.FinishedLevelEvent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Tests @TriggerableComponent.
 */
public class TriggerableTest extends BaseEntityTest {

    private static final Dimension2D ENTITY_SIZE = new Dimension2D(1, 1);
    private static final String PASSWORD = "test";

    private TestEntity triggerableEntity;
    private TriggerableComponent triggerableDoor;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        triggerableEntity = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        triggerableDoor = new TriggerableDoorComponent(PASSWORD, false);
        triggerableEntity.add(triggerableDoor);
    }

    /**
     * When a triggerable component is declared with the triggered parameter false,
     * it should be triggered only after the call of the trigger method.
     */
    @Test
    public final void testTriggerable() {

        assertFalse("The TriggerableDoorComponent object shouldn't be triggered at start",
                triggerableDoor.isTriggered());

        triggerableDoor.trigger();
        advanceSimulation(1);

        assertTrue("The TriggerableDoorComponent object should be triggered after calling the trigger method",
                triggerableDoor.isTriggered());
    }

    /**
     * After an entity with an inventory collides with a triggered door,
     * the triggerable door component should send a FinishedLevelEvent.
     */
    @Test
    public final void testFinishLevel() {
        final TestEntity player = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        player.add(new InventoryImpl());
        triggerableDoor.trigger();
        advanceSimulation(1);
        assertTrue("The DoorTriggerableComponent should call a FinishedLevelEvent",
                triggerableEntity.getEvents().stream().filter(e -> e instanceof FinishedLevelEvent).count() > 0);

    }

}
