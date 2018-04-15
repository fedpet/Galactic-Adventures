package it.unibo.oop17.ga_game.tests.entities;

import static org.junit.Assert.assertTrue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;

/**
 * Collects @EntityEvent and exposes convenient methods for testing.
 */
public class EventsCollector implements EntityEventListener {
    // we'd like to use LinkedList directly because we need both Deque and List interfaces but checkstyle complains and
    // we have no time to fix it now..
    private final Deque<EntityEvent> events = new LinkedList<>();

    /**
     * Event listener.
     * 
     * @param event
     *            The @EntityEvent to collect.
     */
    @Subscribe
    protected void onEvent(final EntityEvent event) {
        events.add(event);
    }

    /**
     * @return The list of received @EntityEvent.
     */
    public List<EntityEvent> getEvents() {
        return events.stream().collect(Collectors.toList());
    }

    /**
     * Retrieves and removes the first event received.
     * 
     * @return first EntityEvent in the queue.
     */
    public Optional<EntityEvent> pop() {
        return Optional.ofNullable(events.poll());
    }

    /**
     * Pops the first event and asserts it's of the given type. Else throws @AssertionError.
     * 
     * @param <E>
     *            type of event
     * @param type
     *            The event class.
     * @return The event found.
     */
    protected <E extends EntityEvent> E pop(final Class<E> type) {
        final EntityEvent event = pop().orElseThrow(() -> {
            return new AssertionError("Event not found");
        });
        assertTrue(type.isAssignableFrom(event.getClass()));
        return type.cast(event);
    }
}
