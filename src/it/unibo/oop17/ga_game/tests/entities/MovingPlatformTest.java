package it.unibo.oop17.ga_game.tests.entities;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import it.unibo.oop17.ga_game.model.EllipticalPathIterator;
import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.MovingPlatform;
import it.unibo.oop17.ga_game.utils.InfiniteSequence;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Tests @MovingPlatform with varying number of passengers.
 */
@RunWith(Parameterized.class)
public class MovingPlatformTest extends BaseEntityTest {
    private static final double TEST_DURATION = 10; // seconds to simulate
    private static final double GAP_BETWEEN_PASSENGERS = 1; // meters
    private static final double MAX_PASSENGER_POS_ERROR = 0.1; // max position error in meters
    private static final Dimension2D PASSENGER_SIZE = new Dimension2D(1, 1);
    private static final Supplier<Point2D> MOVEMENT_ALGO = InfiniteSequence
            .repeat(() -> new EllipticalPathIterator(Point2D.ZERO, 10, 10));
    private final int numberOfPassengers;
    private List<Entity> passengers;
    private Entity platform;

    /**
     * @param passengers
     *            Number of passengers to test
     */
    public MovingPlatformTest(final int passengers) {
        super();
        numberOfPassengers = passengers;
    }

    /**
     * We test the platform with different number of passengers.
     * 
     * @return Arguments for the constructor.
     */
    @Parameters(name = "Test with {0} passengers")
    public static List<Integer> data() {
        return Arrays.asList(1, 2, 3);
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
        final Dimension2D platformSize = new Dimension2D(numberOfPassengers * (PASSENGER_SIZE.getWidth() + GAP_BETWEEN_PASSENGERS), 1);
        platform = spawnEntity(body -> new MovingPlatform(body, Point2D.ZERO, platformSize, MOVEMENT_ALGO));
        passengers = IntStream.range(0, numberOfPassengers)
                .mapToObj(this::spawnPassenger)
                .collect(Collectors.toList());
    }

    /**
     * Tests that passengers don't fall off the moving platform.
     */
    @Test
    public void testPassengersStability() {
        for (int i = 0; i < TEST_DURATION; i++) {
            advanceSimulation(1);
            IntStream.range(0, passengers.size())
                    .forEach(number -> assertCorrectPosition(passengers.get(number).getBody().getPosition(), number));
        }
    }

    private void assertCorrectPosition(final Point2D currentPosition, final int passengerNumber) {
        final Point2D expected = expectedPosition(passengerNumber);
        final Point2D offset = currentPosition.subtract(expected);

        assertEquals("Passenger " + passengerNumber + " expected position = " + expected + " got = " + currentPosition,
                0, offset.getX(), MAX_PASSENGER_POS_ERROR);
        assertEquals("Passenger" + passengerNumber + " expected position = " + expected + " got = " + currentPosition,
                0, offset.getY(), MAX_PASSENGER_POS_ERROR);
    }

    private Point2D expectedPosition(final int passengerNumber) {
        final double y = platform.getBody().getPosition().getY()
                + platform.getBody().getDimension().getHeight() / 2
                + PASSENGER_SIZE.getHeight() / 2;
        double x = platform.getBody().getPosition().getX()
                - platform.getBody().getDimension().getWidth() / 2
                + PASSENGER_SIZE.getWidth() / 2;
        x += passengerNumber * (PASSENGER_SIZE.getWidth() + GAP_BETWEEN_PASSENGERS);
        return new Point2D(x, y);
    }

    private Entity spawnPassenger(final int passengerNumber) {
        return spawnTestEntity(expectedPosition(passengerNumber), PASSENGER_SIZE);
    }
}
