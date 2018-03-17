package it.unibo.oop17.ga_game.model.entities;

import java.util.Optional;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.components.Brain;
import it.unibo.oop17.ga_game.model.entities.components.DeadMovement;
import it.unibo.oop17.ga_game.model.entities.components.EmptyBrain;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.components.Inventory;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;
import it.unibo.oop17.ga_game.model.entities.events.LifeEvent;

public abstract class AbstractEntity implements EventfullEntity {
    private final EventBus eventBus = new EventBus();
    private final EntityBody body;
    private final Life life;
    private MovementComponent movement;
    private Brain brain;
    private final Optional<Inventory> inventory;

    public AbstractEntity(final EntityBody body, final Brain brain, final MovementComponent movement, final Life life,
            final Optional<Inventory> inventory) {
        this.body = body;
        this.brain = brain;
        this.movement = movement;
        this.life = life;
        this.inventory = inventory;
        register(new MyEntityEventListener());
        life.attach(this);
        body.attach(this);
        brain.attach(this);
        movement.attach(this);
    }

    public AbstractEntity(final EntityBody body, final Brain brain, final MovementComponent movement, final Life life) {
        this(body, brain, movement, life, Optional.empty());
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

    @Override
    public Optional<Inventory> getInventory() {
        return inventory;
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

    private void die() {
        System.out.println(toString() + " dieing!");
        movement.detach();
        movement = new DeadMovement();
        movement.attach(this);
        brain.detach();
        brain = new EmptyBrain();
        brain.attach(this);
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