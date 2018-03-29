package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.Entity;

/**
 * Base class for @Brain.
 * It self-detaches on death
 */
public abstract class AbstractBrain extends AbstractContactAwareComponent implements Brain {
    private final Personality personality;

    /**
     * @param personality
     *            Brain @Personality
     */
    public AbstractBrain(final Personality personality) {
        super();
        this.personality = personality;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Personality getPersonality() {
        return personality;
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
