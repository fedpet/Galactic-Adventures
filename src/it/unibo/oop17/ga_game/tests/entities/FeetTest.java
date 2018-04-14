package it.unibo.oop17.ga_game.tests.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop17.ga_game.model.entities.components.FeetComponent;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
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
    private FeetComponent feet;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        entity = spawnTestEntity(Point2D.ZERO, ENTITY_SIZE);
        feet = new FeetComponent(WALKING_SPEED, JUMPING_SPEED);
        entity.add(feet);
    }

    /**
     * Moving toward a direction must change the face direction.
     */
    @Test
    public final void testFaceDirection() {
        feet.move(direction(Side.RIGHT));
        assertEquals(HorizontalDirection.RIGHT, feet.getFaceDirection());

        feet.move(direction(Side.LEFT));
        assertEquals(HorizontalDirection.LEFT, feet.getFaceDirection());

        feet.move(direction(Side.TOP));
        assertEquals(HorizontalDirection.LEFT, feet.getFaceDirection());

        feet.move(direction(Side.BOTTOM));
        assertEquals(HorizontalDirection.LEFT, feet.getFaceDirection());
    }

    /**
     * Must be able to move horizontally.
     */
    @Test
    public final void testBasicMovement() {
        // add floor to the bottom of the entity
        addTerrain(entity.getBody().getPosition()
                .add(new Point2D(0, -entity.getBody().getDimension().getHeight() - 1)), FLOOR_SIZE);

        assertEquals(MovementComponent.State.FALLING, feet.getState());

        advanceSimulation(1);
        assertEquals(MovementComponent.State.IDLE, feet.getState());
        MovementEvent event = entity.popEvent(MovementEvent.class);
        assertEquals(MovementComponent.State.IDLE, event.getState());

        double startingX = entity.getBody().getPosition().getX();
        feet.move(direction(Side.RIGHT));
        advanceSimulation(1);
        assertTrue(startingX < entity.getBody().getPosition().getX());
        event = entity.popEvent(MovementEvent.class);
        assertEquals(MovementComponent.State.WALKING, event.getState());

        startingX = entity.getBody().getPosition().getX();
        feet.move(direction(Side.LEFT));
        advanceSimulation(1);
        assertTrue(startingX > entity.getBody().getPosition().getX());
    }

    /**
     * Test state changes and relative events during movement.
     */
    @Test
    public final void testJump() {
        // add floor to the bottom of the entity
        final Point2D floorPos = entity.getBody().getPosition()
                .add(new Point2D(0, -entity.getBody().getDimension().getHeight() - 1)); 
        addTerrain(floorPos, FLOOR_SIZE);

        advanceSimulation(1);
        assertEquals(MovementComponent.State.IDLE, feet.getState());

        final double startingY = entity.getBody().getPosition().getY();
        // this must result in a jump
        feet.move(direction(Side.TOP));
        assertEquals(MovementComponent.State.JUMPING, feet.getState());

        final MovementEvent event = entity.popEvent(MovementEvent.class);
        assertEquals(MovementComponent.State.JUMPING, event.getState());

        advanceSimulation(1);
        assertTrue(startingY < entity.getBody().getPosition().getY());
    }

    private Point2D direction(final Side side) {
        return PositionCompare.sideToDirection(side);
    }
}
