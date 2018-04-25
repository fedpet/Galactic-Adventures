package it.unibo.oop17.ga_game.model;

/**
 * Calculates an entity score based on an {@link EntityStatistic} object.
 * It also accounts for @Difficulty.
 */
public final class DifficultyBasedScoreCalculator implements ScoreCalculator {
    private static final double FACTOR_EASY = 0.8;
    private static final double FACTOR_MEDIUM = 1;
    private static final double FACTOR_HARD = 1.2;
    private static final int SCORE_PER_KILL = 100;
    private static final int SCORE_PER_KEY = 20;
    private final double factor;

    /**
     * @param difficulty
     *            The difficulty to base the score on.
     */
    public DifficultyBasedScoreCalculator(final Difficulty difficulty) {
        factor = difficultyFactor(difficulty);
    }

    @Override
    public int getScore(final EntityStatistic stats) {
        final int score = stats.getMoneyCollected()
                + stats.getEnemiesKilled() * SCORE_PER_KILL
                + stats.getKeysCollected().size() * SCORE_PER_KEY;
        return (int) Math.ceil(score * factor);
    }

    private double difficultyFactor(final Difficulty difficulty) {
        switch (difficulty) {
        case EASY:
            return FACTOR_EASY;
        case MEDIUM:
            return FACTOR_MEDIUM;
        case HARD:
            return FACTOR_HARD;
        default:
            throw new IllegalStateException("Unknown difficulty " + difficulty);
        }
    }
}
