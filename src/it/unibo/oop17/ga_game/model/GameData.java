package it.unibo.oop17.ga_game.model;

public class GameData implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int coins;
    private int score;
    private int lives;
    private int levelProgress;
    
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
    
    public void resetProgress() {
        this.coins = 0;
        this.score = 0;
        this.lives = 3;
        this.levelProgress = 0;
    }
}
