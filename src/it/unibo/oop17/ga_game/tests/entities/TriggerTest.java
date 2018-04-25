package it.unibo.oop17.ga_game.tests.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop17.ga_game.model.entities.components.ContactTrigger;
import it.unibo.oop17.ga_game.model.entities.components.Trigger;
import it.unibo.oop17.ga_game.model.entities.events.TriggeredEvent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Tests {@link Trigger}.
 */
public class TriggerTest extends BaseEntityTest {

    private static final Dimension2D ENTITY_SIZE = new Dimension2D(1, 1);
    private static final String PASSWORD = "test";

    private TestEntity triggerEntity;
    private Trigger contactTrigger;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        triggerEntity = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        contactTrigger = new ContactTrigger(PASSWORD, false);
        triggerEntity.add(contactTrigger);
    }

    /**
     * After an entity collides with an entity with a TriggerComponent object, this last
     * has to have triggered.
     */
    @Test
    public final void testTrigger() {

        assertFalse("The ContactTrigger object shouldn't have to trigger at start", contactTrigger.hasTriggered());

        spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        advanceSimulation(1);
        assertTrue("The ContactTrigger object should have triggered in contact with another Entity",
                contactTrigger.hasTriggered());
        assertTrue("The ContactTrigger object should send a TriggeredEvent",
                triggerEntity.getEvents().stream().filter(e -> e instanceof TriggeredEvent).count() > 0);

    }

}
