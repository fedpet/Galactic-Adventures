package it.unibo.oop17.ga_game.model.entities;

import java.util.Optional;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.components.Brain;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.components.EntityComponent;
import it.unibo.oop17.ga_game.model.entities.components.InterfacesBag;
import it.unibo.oop17.ga_game.model.entities.components.InterfacesBagImpl;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;
import it.unibo.oop17.ga_game.model.entities.events.LifeEvent;

public abstract class AbstractEntity implements EventfullEntity {
    private final EventBus eventBus = new EventBus();
    private final EntityBody body;
    private final InterfacesBag<EntityComponent> components = new InterfacesBagImpl<>(EntityComponent.class);

    public AbstractEntity(final EntityBody body, final Brain brain, final MovementComponent movement, final Life life) {
        this.body = body;
        register(new MyEntityEventListener());
        body.attach(this);
        add(life);
        add(brain);
        add(movement);
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

    private void die() {
        components.remove(MovementComponent.class);
        components.remove(Brain.class);
    }

    private final class MyEntityEventListener implements EntityEventListener {
        @Subscribe
        public void onLifeChange(final LifeEvent event) {
            if (event.isDead()) {
                die();
            }
        }
    }
}