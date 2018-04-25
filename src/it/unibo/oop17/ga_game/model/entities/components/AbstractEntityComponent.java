package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.EntityEventPublisher;
import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventSubscriber;
import it.unibo.oop17.ga_game.model.entities.events.LifeEvent;

/**
 * Base class for {@link EntityComponent}.
 * 
 * Components detaches on owner's death by default.
 */
public abstract class AbstractEntityComponent implements EntityComponent, EntityEventSubscriber {
    private Optional<EntityEventPublisher> owner = Optional.empty();

    @Override
    public final Optional<? extends Entity> getOwner() {
        return owner;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void attach(final EntityEventPublisher owner) throws IllegalStateException {
        this.owner.ifPresent(e -> {
            throw new IllegalStateException("Component already attached to an entity");
        });
        this.owner = Optional.of(owner);
        owner.register(this);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void detach() throws IllegalStateException {
        owner.ifPresent(entity -> {
            entity.unregister(this);
            owner = Optional.empty();
            entity.remove(this);
        });
    }

    @Override
    public void update(final double dt) {
        //
    }

    /**
     * Should the component detach on death?
     * Defaults to true.
     * 
     * @return true if the component should detach on death.
     */
    protected boolean detachesOnDeath() {
        return true;
    }

    /**
     * Called on life changes (if the owner has a {@link Life} component).
     * 
     * @param event
     *            The related {@link LifeEvent} instance
     */
    @Subscribe
    protected void onLifeChange(final LifeEvent event) {
        if (detachesOnDeath() && event.isDead()) {
            detach();
        }
    }

    /**
     * Convenience method to avoid the optional.
     * 
     * @return The related {@link EventfullEntity} object.
     * 
     * @throws IllegalStateException
     *             is the component is not attached to an Entity
     */
    protected final EntityEventPublisher getEntity() {
        return owner.orElseThrow(IllegalStateException::new);
    }

    /**
     * Generates an {@link EntityEvent} object.
     * 
     * @param event
     *            The event
     */
    protected final void post(final EntityEvent event) {
        getEntity().post(event);
    }
}
