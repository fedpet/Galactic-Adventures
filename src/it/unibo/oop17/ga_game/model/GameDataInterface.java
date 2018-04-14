package it.unibo.oop17.ga_game.model;

public interface GameDataInterface {
    
    /**
     * @return total coins.
     */
    int getCoins();
    
    /**
     * @return total score.
     */
    int getScore();
    
    /**
     * @return last level completed.
     */
    int getLevelProgress();

}
