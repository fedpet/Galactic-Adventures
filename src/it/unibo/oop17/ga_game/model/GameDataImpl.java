package it.unibo.oop17.ga_game.model;

/**
 * Game data as object.
 */
public final class GameDataImpl implements GameData {

    private static final long serialVersionUID = 3425851307309697377L;
    private static final int SCORE_I = 0;
    private static final int PROGRESS_I = 0;

    private int score;
    private int levelProgress;

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int getLevelProgress() {
        return this.levelProgress;
    }

    /**
     * Sets the score.
     * @param score
     *          Score to set.
     */
    public void setScore(final int score) {
        this.score = score;
    }

    /**
     * Sets the progress.
     * @param progress
     *          Progress to set.
     */
    public void setLevelProgress(final int progress) {
        this.levelProgress = progress;
    }

    /**
     * Resets progress to zero.
     */
    public void resetProgress() {
        this.setScore(SCORE_I);
        this.setLevelProgress(PROGRESS_I);
    }

}

