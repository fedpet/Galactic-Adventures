package it.unibo.oop17.ga_game.model.entities.components;

/**
 * GOOD hates EVIL and viceversa.
 * No hate for other personalities.
 */
public enum EntityPersonality implements Personality {
    /**
     * In our game there are GOODs and EVILs.
     * Some entities does not have personality.
     */
    GOOD, EVIL, NONE;

    @Override
    public boolean hates(final Personality personality) {
        return match(this, personality, GOOD, EVIL);
    }

    private static boolean match(final Personality firstPer, final Personality secondPer, final EntityPersonality firstType,
            final EntityPersonality secondType) {
        return firstPer.equals(firstType) && secondPer.equals(secondType) || firstPer.equals(secondType) && secondPer.equals(firstType);
    }
}
