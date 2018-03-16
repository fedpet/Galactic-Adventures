package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.components.Life;

/**
 * Models an @EntityEvent reagrding its @Life.
 */
public class LifeEvent implements EntityEvent {
    private final Life life;
    private final int change;

    /**
     * 
     * @param life
     *            The @Life
     * @param changeAmount
     *            The change in its health points.
     */
    public LifeEvent(final Life life, final int changeAmount) {
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