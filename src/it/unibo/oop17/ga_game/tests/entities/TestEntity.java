package it.unibo.oop17.ga_game.tests.entities;

import static org.junit.Assert.assertTrue;

import java.util.List;

import it.unibo.oop17.ga_game.model.entities.AbstractEntity;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;

/**
 * An {@link Entity} extension exposing methods to add components on the fly.
 * It also collects its own events to ease testing.
 */
public final class TestEntity extends AbstractEntity {
    private final EventsCollector collector = new EventsCollector();

    /**
     * @param body
     *            The {@link EntityBody} instance.
     */
    public TestEntity(final EntityBody body) {
        super(body);
        register(collector);
    }

    @Override
    public void destroy() {
        super.destroy();
        unregister(collector);
    }

    /**
     * @return List of generated events.
     */
    public List<EntityEvent> getEvents() {
        return collector.getEvents();
    }

    /**
     * Pop the queue first generated event and asserts it's of the given type. Else throws @AssertionError.
     * 
     * @param <E>
     *            type of event
     * @param type
     *            The event class.
     * @return The event found.
     */
    protected <E extends EntityEvent> E popEvent(final Class<E> type) {
        final EntityEvent event = collector.pop().orElseThrow(() -> {
            return new AssertionError("Event not found");
        });
        assertTrue(type.isAssignableFrom(event.getClass()));
        return type.cast(event);
    }
}
