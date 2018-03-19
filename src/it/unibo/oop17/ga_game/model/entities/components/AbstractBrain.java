package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Base class for @Brain.
 */
public abstract class AbstractBrain extends AbstractEntityComponent implements Brain {
    @Override
    public void update(final double dt) {
    }

    /**
     * Default method to decide if we hate another @Entity.
     * 
     * @param other
     *            The other Entity
     * @return true if we hate him.
     */
    protected boolean hate(final Entity other) {
        if (other.get(Brain.class).isPresent()) {
            return getPersonality().hates(other.get(Brain.class).get().getPersonality());
        }
        return getPersonality().hates(EntityPersonality.NONE);
    }
}
