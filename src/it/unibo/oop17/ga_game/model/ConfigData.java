package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.Volume;

/**
 * Interface of configuration data.
 */
public interface ConfigData extends java.io.Serializable {

    /**
     * @return music volume value.
     */
    Volume getMusicVol();

    /**
     * @return SFX volume value.
     */
    Volume getSFXVol();

    /**
     * @return difficulty value.
     */
    Difficulty getDifficulty();

    /**
     * @return language value.
     */
    Language getLanguage();

}
