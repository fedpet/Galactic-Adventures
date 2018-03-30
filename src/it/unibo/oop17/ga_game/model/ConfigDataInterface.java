package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.Volume;

public interface ConfigDataInterface {

    Volume getMusicVol();
    
    Volume getSFXVol();
    
    Difficulty getDifficulty();
    
    Language getLanguage();
    
}
