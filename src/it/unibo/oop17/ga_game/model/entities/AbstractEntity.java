package it.unibo.oop17.ga_game.model.entities;

import java.util.Optional;

import com.google.common.eventbus.EventBus;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.components.EntityComponent;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;
import it.unibo.oop17.ga_game.utils.InterfacesBag;
import it.unibo.oop17.ga_game.utils.InterfacesBagImpl;

/**
 * Base class for @Entity.
 * It supports events and components.
 */
public abstract class AbstractEntity implements EntityEventPublisher {
    private final EventBus eventBus = new EventBus();
    private final EntityBody body;
    private final InterfacesBag<EntityComponent> components = new InterfacesBagImpl<>(EntityComponent.class);

    /**
     * 
     * @param body
     *            The @EntityBody is the only required component for an @Entity.
     */
    public AbstractEntity(final EntityBody body) {
        this.body = body;
        body.attach(this);
    }

    @Override
    public final EntityBody getBody() {
        return body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double dt) {
        updateComponents(dt);
    }

    /**
     * Generates a @DestructionEvent and then detaches all components.
     */
    @Override
    public void destroy() {
        post(new DestructionEvent(this));
        components.forEach(this::remove);
        remove(body);
    }

    /**
     * An @EntityEventListener declares wanted events with the @Subscribe annotation.
     */
    @Override
    public final void register(final EntityEventListener listener) {
        eventBus.register(listener);
    }

    @Override
    public final void unregister(final EntityEventListener listener) {
        eventBus.unregister(listener);
    }

    @Override
    public final void post(final EntityEvent event) {
        eventBus.post(event);
    }

    @Override
    public final <C extends EntityComponent> Optional<C> get(final Class<C> component) {
        return components.get(component);
    }

    @Override
    public final void add(final EntityComponent component) {
        components.put(component);
        component.attach(this);
    }

    @Override
    public final void remove(final EntityComponent component) {
        components.remove(component);
        component.detach();
    }

    /**
     * Calls {@link EntityComponent#update} on the components.
     * 
     * @param dt
     *            Time delta since last call.
     */
    protected void updateComponents(final double dt) {
        components.forEach(c -> c.update(dt));
    }
}
