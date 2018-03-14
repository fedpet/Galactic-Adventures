package it.unibo.oop17.ga_game.model.entities;

import com.google.common.eventbus.EventBus;

import it.unibo.oop17.ga_game.model.entities.components.Brain;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;

public abstract class AbstractEntity implements EventfullEntity {
    private final EventBus eventBus = new EventBus();
    private final EntityBody body;
    private final Brain brain;
    private final MovementComponent movement;
    private final Life life;

    public AbstractEntity(final EntityBody body, final Brain brain, final MovementComponent movement, final Life life) {
        this.body = body;
        this.brain = brain;
        this.movement = movement;
        this.life = life;
        life.attach(this);
        body.attach(this);
        brain.attach(this);
        movement.attach(this);
    }

    @Override
    public final EntityBody getBody() {
        return body;
    }

    @Override
    public final Brain getBrain() {
        return brain;
    }

    @Override
    public final MovementComponent getMovement() {
        return movement;
    }

    @Override
    public final Life getLife() {
        return life;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double dt) {
        updateComponents(dt);
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

    /**
     * Calls update(dt) on the components.
     * 
     * @param dt
     *            Time delta since last call.
     */
    protected void updateComponents(final double dt) {
        brain.update(dt);
        movement.update(dt);
    }
}