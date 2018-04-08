package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.MainView;
import it.unibo.oop17.ga_game.view.MainViewImpl;
import javafx.stage.Stage;

public class MainControllerImpl implements MainController {
    
    private final MainView view;
    private final ConfigData data;
    private final GameData save;
    private final Stage stage;
    
    MainControllerImpl(final Stage stage) {
        
        this.stage = stage;
        this.data = CheckData.loadConfig();
        this.save = CheckSave.loadSave();
        this.view = new MainViewImpl(stage);
        this.toMenu();
        
    }

    public final void toMenu() {
        new MenuController(view.showMenu(this), this);
    }
    
    public final void toGame() {
        new GameControllerImpl(view.showGame(), view.showHud(), this);
    }

    public final void toEndLevel() {
        new EndLevelController(view.showEndLevel(this), this);
    }
    
    public final void toGameOver() {
        new GameOverController(view.showGameOver(this), this);
    }
    
    public final void toEndGame() {
        new EndGameController(view.showEndGame(this), this);
    }
    
    public Stage getStage() {
        return this.stage;
    }
    
    public ConfigData getConfigData() {
        return this.data;
    }
    
    public GameData getGameData() {
        return this.save;
    }
    
}