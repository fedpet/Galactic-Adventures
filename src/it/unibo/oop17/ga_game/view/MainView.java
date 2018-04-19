package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.controller.EndGameController;
import it.unibo.oop17.ga_game.controller.EndLevelController;
import it.unibo.oop17.ga_game.controller.GameOverController;
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
    MenuView showMenu(Volume musicVol, Volume sfxVol, Language language, Difficulty difficulty);

    /**
     * 
     * @param language
     *          The language for translation.
     * @param progress
     *          The current progress.
     * @param tracker
     *          The player tracker (at level end).
     * @param score
     *          The player score (at level end).
     * @return the end level view to load.
     */
    CommonView<EndLevelController> showEndLevel(Language language, int progress, EntityStatistic tracker, int score);

    /**
     * 
     * @param language
     *          The language for translation.
     * @return the game over view to load.
     */
    CommonView<GameOverController> showGameOver(Language language);

    /**
     * @param language
     *          The language for translation.
     * @param score
     *          The player score (at level end).
     * @return the end game view to load.
     */
    CommonView<EndGameController> showEndGame(Language language, int score);

    /**
     * Shows error.
     * @param message
     *          The message to show.
     */
    void showError(String message);
}
