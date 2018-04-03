package it.unibo.oop17.ga_game.model;

public class GameData implements GameDataInterface, java.io.Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3425851307309697377L;
    private final static int COINS_I = 0;
    private final static int SCORE_I = 0;
    private final static int LIVES_I = 3;
    private final static int PROGRESS_I = 0;
    
    private int coins;
    private int score;
    private int lives;
    private int levelProgress;
    
    public int getCoins() {
        return this.coins;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public int getLives() {
        return this.lives;
    }
    
    public int getLevelProgress() {
        return this.levelProgress;
    }
    
    public void setCoins(final int coins) {
        this.coins = coins;
    }
    
    public void setScore(final int score) {
        this.score = score;
    }
    
    public void setLives(final int lives) {
        this.lives = lives;
    }
    
    public void setLevelProgress(final int levelProgress) {
        this.levelProgress = levelProgress;
    }
    
    public void resetProgress() {
        this.setCoins(COINS_I);
        this.setScore(SCORE_I);
        this.setLives(LIVES_I);
        this.setLevelProgress(PROGRESS_I);
    }
}
