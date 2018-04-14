package it.unibo.oop17.ga_game.model;

/**
 * Capable of calculating score.
 */
public interface ScoreCalculator {
    /**
     * @param stats
     *            Statistics of the entity
     * @return its score as an integer value. The bigger the better.
     */
    int getScore(EntityStatistic stats);
}
