package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.entities.EventfullEntity;

public abstract class AbstractEntityComponent implements EntityComponent {
    private Optional<EventfullEntity> owner = Optional.empty();

    @Override
    public final Optional<EventfullEntity> getOwner() {
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
            throw new IllegalStateException();
        });
        this.owner = Optional.of(owner);
    }
}
