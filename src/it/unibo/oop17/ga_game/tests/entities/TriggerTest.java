package it.unibo.oop17.ga_game.tests.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop17.ga_game.model.entities.components.ContactTrigger;
import it.unibo.oop17.ga_game.model.entities.components.OneTimeTriggerable;
import it.unibo.oop17.ga_game.model.entities.components.TriggerComponent;
import it.unibo.oop17.ga_game.model.entities.components.TriggerableComponent;
import it.unibo.oop17.ga_game.model.entities.events.PasswordTriggeredEvent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Tests @TriggerComponent and @TriggerableComponent.
 */
public class TriggerTest extends BaseEntityTest {

    private static final Dimension2D ENTITY_SIZE = new Dimension2D(1, 1);
    private static final Point2D TRIGGERED_ENTITY_POSITION = new Point2D(5, 0);
    private static final Point2D TRIGGER_ENTITY_POSITION = Point2D.ZERO;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * After an Entity collides with an Entity with a TriggerComponent, this last
     * has to have triggered; the Entity with a TriggerableComponent with the same password
     * has to be triggered right after as well.
     */
    @Test
    public final void testTriggerEntity() {
        final String password = "test";
        final TestEntity triggerEntity = spawnTestEntity(TRIGGER_ENTITY_POSITION, ENTITY_SIZE);
        triggerEntity.add(new ContactTrigger(password, false));
        assertFalse("The TriggerComponent starting triggered value should be false",
                triggerEntity.get(TriggerComponent.class).get().hasTriggered());

        final TestEntity triggerableEntity = spawnTestEntity(TRIGGERED_ENTITY_POSITION, ENTITY_SIZE);
        triggerableEntity.add(new OneTimeTriggerable(password, false));
        assertFalse("The TriggerableComponent starting triggered value should be false",
                triggerableEntity.get(TriggerableComponent.class).get().isTriggered());

        spawnTestEntity(TRIGGER_ENTITY_POSITION, ENTITY_SIZE);
        advanceSimulation(1);
        assertTrue("The contact between the new Entity and the Entity with the TriggerComponent should triggered it",
                triggerEntity.get(TriggerComponent.class).get().hasTriggered());
        spawnTestEntity(TRIGGERED_ENTITY_POSITION, ENTITY_SIZE);
        advanceSimulation(1);
        if (triggerEntity.popEvent(PasswordTriggeredEvent.class).getPassword()
                .equals(triggerableEntity.get(TriggerableComponent.class).get().getPassword())) {
            triggerableEntity.get(TriggerableComponent.class).get().trigger();
        }
        ;
        triggerableEntity.update(1);
        assertTrue("The TriggerableComponent should be triggered",
                triggerableEntity.get(TriggerableComponent.class).get().isTriggered());
    }

}
