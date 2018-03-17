package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.LifeEvent;

/**
 * Base class for @Life implementations.
 */
public class AbstractLife extends AbstractEntityComponent implements Life {
    private int current;
    private final int max;

    /**
     * 
     * @param max
     *            Maximum amount of life.
     * @param current
     *            Current amount of life.
     */
    protected AbstractLife(final int max, final int current) {
        this.current = current;
        this.max = max;
        if (max < 1) {
            throw new IllegalArgumentException("Maximum life cannot be below one");
        }
        if (current < 0) {
            throw new IllegalArgumentException("Current life cannot below zero");
        }
    }

    @Override
    public final int getHealthPoints() {
        return current;
    }

    @Override
    public final int getMaxHealthPoints() {
        return max;
    }

    @Override
    public final void hurt(final int damageAmount) {
        if (damageAmount < 0) {
            throw new IllegalArgumentException("Cannot hurt a life for a negative amount");
        }
        if (!isDead()) {
            addLife(-damageAmount);
        }
    }

    @Override
    public final void heal(final int restoreAmount) {
        if (restoreAmount < 0) {
            throw new IllegalArgumentException("Cannot heal a life for a negative amount");
        }
        if (current < max && !isDead()) {
            addLife(restoreAmount);
        }
    }

    /**
     * Adds health points and produces the relative event.
     * 
     * @param amount
     *            The amount.
     */
    protected void addLife(final int amount) {
        current += amount;
        if (current < 0) {
            current = 0;
        }
        if (current > max) {
            current = max;
        }
        if (amount != 0) {
            reportChange(amount);
        }
    }

    /**
     * Produces an event relative to a change in this life.
     * 
     * @param amount
     *            The amount.
     */
    protected void reportChange(final int amount) {
        if (isDead()) {
            post(new DestructionEvent(getEntity()));
        } else {
            post(new LifeEvent(getEntity(), this, amount));
        }
    }
}
