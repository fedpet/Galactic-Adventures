package it.unibo.oop17.ga_game.model;

/**
 * Interface of game data.
 */
public interface GameData extends java.io.Serializable {

    /**
     * @return total score.
     */
    int getScore();

    /**
     * @return last level completed.
     */
    int getLevelProgress();

    /**
     * Resets progress to zero.
     */
    void resetProgress();

}
