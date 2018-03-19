package it.unibo.oop17.ga_game.model.entities;

import java.util.Optional;

import com.google.common.eventbus.EventBus;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.components.EntityComponent;
import it.unibo.oop17.ga_game.model.entities.components.InterfacesBag;
import it.unibo.oop17.ga_game.model.entities.components.InterfacesBagImpl;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;

public abstract class AbstractEntity implements EventfullEntity {
    private final EventBus eventBus = new EventBus();
    private final EntityBody body;
    private final InterfacesBag<EntityComponent> components = new InterfacesBagImpl<>(EntityComponent.class);

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

    @Override
    public void destroy() {
        post(new DestructionEvent(this));
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
    public final <C extends EntityComponent> void remove(final Class<C> component) {
        components.get(component).ifPresent(this::remove);
        // components.remove(component).ifPresent(EntityComponent::detach);
    }

    @Override
    public final void remove(final EntityComponent component) {
        components.remove(component);
        component.detach();
    }

    /**
     * Calls update(dt) on the components.
     * 
     * @param dt
     *            Time delta since last call.
     */
    protected void updateComponents(final double dt) {
        components.forEach(c -> c.update(dt));
    }

    protected final void add(final EntityComponent component) {
        components.put(component);
        component.attach(this);
    }
}