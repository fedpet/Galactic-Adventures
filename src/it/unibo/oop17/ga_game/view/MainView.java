package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.controller.EndGameObserver;
import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import it.unibo.oop17.ga_game.controller.GameOverObserver;
import it.unibo.oop17.ga_game.controller.MainObserver;
import it.unibo.oop17.ga_game.model.Difficulty;
import it.unibo.oop17.ga_game.model.EntityStatistic;

/**
 * Interface of the main view.
 */
public interface MainView {

    /**
     * @param musicVol
     *          The music volume.
     * @param sfxVol
     *          The sound effect volume.
     * @param progress
     *          The current progress.
     * @return the game view to load.
     */
    GameWorldView showGame(Volume musicVol, Volume sfxVol, int progress);

    /**
     * 
     * @param musicVol
     *          The current music volume.
     * @param sfxVol
     *          The current sound effects volume.
     * @param language
     *          The current language.
     * @param difficulty
     *          The current difficulty.
     * @return the menu view to load.
     */
    MenuScreen showMenu(Volume musicVol, Volume sfxVol, Language language, Difficulty difficulty);

    /**
     * 
     * @param language
     *          The language for translation.
     * @param progress
     *          The current progress.
     * @param statistic
     *          The player statistic (at level end).
     * @param score
     *          The player score (at level end).
     * @return the end level view to load.
     */
    CommonView<EndLevelObserver> showEndLevel(Language language, int progress, EntityStatistic statistic, int score);

    /**
     * 
     * @param language
     *          The language for translation.
     * @return the game over view to load.
     */
    CommonView<GameOverObserver> showGameOver(Language language);

    /**
     * @param language
     *          The language for translation.
     * @param score
     *          The player score (at level end).
     * @return the end game view to load.
     */
    CommonView<EndGameObserver> showEndGame(Language language, int score);

    /**
     * Quits.
     */
    void quit();

    /**
     * Sets the observer.
     * @param observer
     *          The controller.
     */
    void setObserver(MainObserver observer);

    /**
     * Sets the number of levels.
     * @param levelsNum
     *          Number of levels.
     */
    void setLevelsNum(int levelsNum);
}
