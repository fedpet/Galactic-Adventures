package it.unibo.oop17.ga_game.tests.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.entities.components.LinearLife;
import it.unibo.oop17.ga_game.model.entities.events.LifeEvent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Tests @LinearLife.
 */
public final class LinearLifeTest extends BaseEntityTest {
    private static final Dimension2D ENTITY_SIZE = new Dimension2D(1, 1);
    private static final int STARTING_LIFE = 2;
    private TestEntity entity;
    private Life life;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        entity = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        life = new LinearLife(STARTING_LIFE);
        entity.add(life);
    }

    /**
     * It should be alive if just created.
     */
    @Test
    public void testAlive() {
        assertAlive(life);
        assertHeath(life, 2);
    }

    /**
     * Tests life hurting.
     */
    @Test
    public void testHurt() {
        life.hurt(1);
        assertAlive(life);
        assertHeath(life, 1);
        life.hurt(life.getHealthPoints());
        assertDead(life);
        assertHeath(life, 0);
    }

    /**
     * Tests life healing.
     * Cannot heal over maximum health points.
     */
    @Test
    public void testHeal() {
        life.hurt(1);
        life.heal(1);
        assertAlive(life);
        assertHeath(life, 2);
        life.heal(1);
        assertHeath(life, 2);
    }

    /**
     * Cannot resurrect a dead life.
     */
    @Test
    public void testNoResurrection() {
        life.hurt(life.getHealthPoints());
        assertDead(life);
        life.heal(10);
        assertDead(life);
    }

    /**
     * Must produce events if hurt of healed.
     */
    @Test
    public void testLifeEvent() {
        life.hurt(1);
        life.heal(1);
        life.hurt(life.getHealthPoints());

        LifeEvent event = entity.popEvent(LifeEvent.class);
        assertEquals("Expected -1 decrease in health points", -1, event.getChange());
        assertTrue("Event should report death", event.isDead());

        event = entity.popEvent(LifeEvent.class);
        assertEquals("Expected +1 increase in health points", 1, event.getChange());
        assertTrue("Event should report death", event.isDead());

        event = entity.popEvent(LifeEvent.class);
        assertEquals("Expected -2 decrease in health points", 2 * -1, event.getChange());
        assertTrue("Event should report death", event.isDead());
    }

    private void assertHeath(final Life life, final int expected) {
        assertEquals("Expected " + expected + " health points instead of " + life.getHealthPoints(), expected,
                life.getHealthPoints());
        assertTrue("Current health points must be <= maximum amount",
                life.getHealthPoints() <= life.getMaxHealthPoints());
        assertTrue("Health points should'nt go negative", life.getHealthPoints() >= 0);
    }

    private void assertAlive(final Life life) {
        assertTrue("Life should be alive", life.isAlive());
        assertFalse("Cannot be both isAlive and isDead", life.isDead());
    }

    private void assertDead(final Life life) {
        assertFalse("Life should be dead", life.isAlive());
        assertTrue("Cannot be both isAlive and isDead", life.isDead());
    }
}
