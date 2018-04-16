package it.unibo.oop17.ga_game.model;

/**
 * Game data as object.
 */
public final class GameData implements GameDataInterface, java.io.Serializable {

    private static final long serialVersionUID = 3425851307309697377L;
    private static final int COINS_I = 0;
    private static final int SCORE_I = 0;
    private static final int PROGRESS_I = 0;

    private int coins;
    private int score;
    private int levelProgress;

    @Override
    public int getCoins() {
        return this.coins;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int getLevelProgress() {
        return this.levelProgress;
    }

    /**
     * Sets the coins.
     * @param coins
     *          Coins to set.
     */
    public void setCoins(final int coins) {
        this.coins = coins;
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
     * Sets the next level progress.
     */
    public void nextLevelProgress() {
        this.levelProgress++;
    }

    /**
     * Resets progress to zero.
     */
    public void resetProgress() {
        this.setCoins(COINS_I);
        this.setScore(SCORE_I);
        this.setLevelProgress(PROGRESS_I);
    }

}

