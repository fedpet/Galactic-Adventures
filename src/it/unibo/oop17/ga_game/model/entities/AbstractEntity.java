package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.Brain;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;

public abstract class AbstractEntity implements EventfullEntity {
    private final EntityBody body;
    private final Brain brain;
    private final MovementComponent movement;

    public AbstractEntity(final EntityBody body, final Brain brain, final MovementComponent movement) {
        this.body = body;
        this.brain = brain;
        this.movement = movement;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double dt) {
        updateComponents(dt);
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