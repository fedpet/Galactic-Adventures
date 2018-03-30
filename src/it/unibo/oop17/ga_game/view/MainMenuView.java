package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.MainMenuObserver;
import it.unibo.oop17.ga_game.model.Difficulty;

/**
 * Main Menu view interface.
 */
public interface MainMenuView {
    
    /**
     * @param observer the controller to attach
     */
    void setObserver(MainMenuObserver observer);

    /**
     * @param res the result of the last draw
     */
    void updateView(final Volume musicVol, final Volume sfxVol, final Language language, final Difficulty difficulty);
    
    /**
     * @param currLang the current language.
     */
    void updateLanguage(final Map<Text, String> currLang);
    
    /**
     * @param musicVol the current music volume.
     */
    void updateMusicVol(final Volume musicVol);

    /**
     * If at least the first level has been completed, the continue game button must appear.
     * 
     * @param isVisible if progress exits.
     */
    void setContinueEnabled(final boolean isVisible);

}
