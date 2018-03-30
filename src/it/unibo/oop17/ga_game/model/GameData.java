package it.unibo.oop17.ga_game.model;

public class GameData implements GameDataInterface, java.io.Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3425851307309697377L;
    
    private int coins;
    private int score;
    private int lives;
    private int levelProgress;
    
    public GameData(final int coins, final int score, final int lives, final int levelProgress) {
        
        this.coins = coins;
        this.score = score;
        this.lives = lives;
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
        this.setCoins(0);
        this.setScore(0);
        this.setLives(3);
        this.setLevelProgress(0);
    }
}
