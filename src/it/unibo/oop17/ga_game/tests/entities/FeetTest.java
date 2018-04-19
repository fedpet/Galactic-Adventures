package it.unibo.oop17.ga_game.tests.entities;

import static it.unibo.oop17.ga_game.model.entities.components.Movement.State.FALLING;
import static it.unibo.oop17.ga_game.model.entities.components.Movement.State.IDLE;
import static it.unibo.oop17.ga_game.model.entities.components.Movement.State.JUMPING;
import static it.unibo.oop17.ga_game.model.entities.components.Movement.State.WALKING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop17.ga_game.model.entities.components.Feet;
import it.unibo.oop17.ga_game.model.entities.components.Movement;
import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.MovementEvent;
import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Dimension2D;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * Tests @FeetComponent.
 */
public class FeetTest extends BaseEntityTest {
    private static final Dimension2D ENTITY_SIZE = new Dimension2D(1, 1);
    private static final Dimension2D FLOOR_SIZE = new Dimension2D(20, 1);
    private static final double WALKING_SPEED = 20.0;
    private static final double JUMPING_SPEED = 20.0;
    private TestEntity entity;
    private Feet feet;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        entity = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        feet = new Feet(WALKING_SPEED, JUMPING_SPEED);
        entity.add(feet);
    }

    /**
     * Moving toward a direction must change the face direction.
     */
    @Test
    public final void testFaceDirection() {
        assertState(FALLING);
        feet.move(direction(Side.RIGHT));
        assertFacing(HorizontalDirection.RIGHT);

        feet.move(direction(Side.LEFT));
        assertFacing(HorizontalDirection.LEFT);
        assertEquals(HorizontalDirection.LEFT, entity.popEvent(FaceDirectionEvent.class).getDirection());

        feet.move(direction(Side.TOP));
        assertEquals(HorizontalDirection.LEFT, feet.getFaceDirection());
        assertFacing(HorizontalDirection.LEFT);

        feet.move(direction(Side.BOTTOM));
        assertFacing(HorizontalDirection.LEFT);
    }

    /**
     * Must be able to move horizontally.
     */
    @Test
    public final void testHorizontalMovement() {
        // add floor to the bottom of the entity
        addFloor();
        assertState(FALLING);
        advanceSimulation(1);
        assertState(IDLE);

        double startingX = entity.getBody().getPosition().getX();
        feet.move(direction(Side.RIGHT));
        advanceSimulation(1);
        assertTrue("The entity must have moved right", startingX < entity.getBody().getPosition().getX());
        assertState(WALKING);

        startingX = entity.getBody().getPosition().getX();
        feet.move(direction(Side.LEFT));
        advanceSimulation(1);
        assertTrue("The entity must have moved left", startingX > entity.getBody().getPosition().getX());
    }

    /**
     * Test state changes and relative events during movement.
     */
    @Test
    public final void testJump() {
        addFloor();
        assertState(FALLING);
        advanceSimulation(1);
        assertState(IDLE);

        final double startingY = entity.getBody().getPosition().getY();
        // this must result in a jump
        feet.move(direction(Side.TOP));
        assertState(JUMPING);
        advanceSimulation(1);
        assertTrue("The entity must have jumped in air (startY < endY)",
                startingY < entity.getBody().getPosition().getY());
    }

    private void assertFacing(final HorizontalDirection expected) {
        assertEquals("The entity must face " + expected + " now", expected,
                feet.getFaceDirection());
    }

    private void assertState(final Movement.State expected) {
        assertEquals("Expected Movement state " + expected, expected, feet.getState());
        assertEquals("Expected Movement state " + expected, expected, entity.popEvent(MovementEvent.class).getState());
    }

    private void addFloor() {
        final Point2D floorPos = entity.getBody().getPosition()
                .add(new Point2D(0, -entity.getBody().getDimension().getHeight() / 2 - FLOOR_SIZE.getHeight() / 2));
        addTerrain(floorPos, FLOOR_SIZE);
    }

    private Point2D direction(final Side side) {
        return PositionCompare.sideToDirection(side);
    }
}
