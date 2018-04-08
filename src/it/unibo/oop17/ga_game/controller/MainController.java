package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
import javafx.stage.Stage;

public interface MainController {

    void toMenu();
    
    void toGame();

    void toEndLevel();
    
    void toGameOver();
    
    void toEndGame();
    
    Stage getStage();
    
    ConfigData getConfigData();
    
    GameData getGameData();
}
