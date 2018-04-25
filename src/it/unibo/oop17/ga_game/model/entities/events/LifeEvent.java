package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.Life;

/**
 * Models an entity event regarding its life.
 */
public class LifeEvent extends AbstractEntityEvent {
    private final Life life;
    private final int change;

    /**
     * 
     * @param source
     *            The {@link Entity} source of this event.
     * @param life
     *            The related {@link Life} object.
     * @param changeAmount
     *            The change in its health points.
     */
    public LifeEvent(final Entity source, final Life life, final int changeAmount) {
        super(source);
        this.life = life;
        this.change = changeAmount;
    }

    /**
     * 
     * @return true if it's dead.
     */
    public boolean isDead() {
        return life.isDead();
    }

    /**
     * 
     * @return The current amount of health points.
     */
    public int getHealthPoints() {
        return life.getHealthPoints();
    }

    /**
     * 
     * @return The maximum amount of health points.
     */
    public int getMaxHealthPoints() {
        return life.getMaxHealthPoints();
    }

    /**
     * 
     * @return The difference between previous health points amount and current one.
     *         A negative value means the @Life has been hurt.
     *         A positive one means it has been healed.
     */
    public int getChange() {
        return change;
    }
}
