package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.MenuObserver;
import it.unibo.oop17.ga_game.model.Difficulty;

/**
 * Main Menu view interface.
 */
public interface MenuView extends CommonView<MenuObserver> {
    
    /**
     * @param 
     */
    void updateView(final Volume musicVol, final Volume sfxVol, final Language language, final Difficulty difficulty);
    
    /**
     * @param currLang the current language.
     */
    void updateLanguage(final Map<WordText, String> currLang);

    /**
     * If progress exits, continue button appears.
     * 
     * @param isVisible if progress exits.
     */
    void setContinueEnabled(final boolean isVisible);

}
