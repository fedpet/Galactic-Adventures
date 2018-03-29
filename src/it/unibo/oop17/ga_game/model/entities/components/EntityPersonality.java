package it.unibo.oop17.ga_game.model.entities.components;

/**
 * GOOD hates EVIL and viceversa.
 * PSYCHO hates everyone.
 * NONE hates noone.
 */
public enum EntityPersonality implements Personality {
    /**
     * In our game there are GOODs, EVILs and PSYCHOs.
     * Some entities does not have personality.
     */
    GOOD, EVIL, PSYCHO, NONE;

    @Override
    public boolean hates(final Personality personality) {
        return this == PSYCHO || match(this, personality, GOOD, EVIL);
    }

    private static boolean match(final Personality firstPer, final Personality secondPer, final EntityPersonality firstType,
            final EntityPersonality secondType) {
        return firstPer.equals(firstType) && secondPer.equals(secondType) || firstPer.equals(secondType) && secondPer.equals(firstType);
    }
}
