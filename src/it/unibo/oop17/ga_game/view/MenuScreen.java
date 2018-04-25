package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.MenuWithOptionsObserver;
import it.unibo.oop17.ga_game.model.Difficulty;

/**
 * Main Menu view interface.
 */
public interface MenuScreen extends CommonView<MenuWithOptionsObserver> {

    /**
     * Updates the menu view.
     * @param musicVol
     *          Current music volume.
     * @param sfxVol
     *          Current sound effects volume.
     * @param language
     *          Current language.
     * @param difficulty
     *          Current difficulty.
     */
    void updateView(Volume musicVol, Volume sfxVol, Language language, Difficulty difficulty);

    /**
     * @param currLang the current language.
     */
    void updateLanguage(Map<WordText, String> currLang);

    /**
     * If progress exits, continue button appears.
     * 
     * @param isVisible if progress exits.
     */
    void setContinueEnabled(boolean isVisible);

}
