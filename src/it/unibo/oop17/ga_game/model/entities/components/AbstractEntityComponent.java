package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.EventfullEntity;
import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;

public abstract class AbstractEntityComponent implements EntityComponent {
    private Optional<EventfullEntity> owner = Optional.empty();

    @Override
    public final Optional<? extends Entity> getOwner() {
        return owner;
    }

    /**
     * Convenience method to avoid the optional.
     * 
     * @return The @EventfullEntity
     * 
     * @throws IllegalStateException
     *             is the component is not attached to an Entity
     */
    protected final EventfullEntity getEntity() {
        return owner.orElseThrow(IllegalStateException::new);
    }

    @Override
    public final void attach(final EventfullEntity owner) throws IllegalStateException {
        this.owner.ifPresent(e -> {
            throw new IllegalStateException("Component already attached to an entity");
        });
        this.owner = Optional.of(owner);
    }

    /**
     * Generates an @EntityEvent.
     * 
     * @param event
     *            The event
     */
    protected void post(final EntityEvent event) {
        getEntity().post(event);
    }
}
