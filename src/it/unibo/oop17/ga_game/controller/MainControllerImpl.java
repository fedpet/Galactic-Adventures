package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.model.GameWorld;
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
        toEndLevel();
        
    }

    public final void toMenu() {
        this.view.showMenu(new MenuController(this).getView());
    }
    
    public final void toGame() {
        new GameControllerImpl(new GameWorld(), view.showGame(), view.showHud(), this);
    }

    public final void toEndLevel() {
        this.view.showEndLevel(new EndLevelController(this).getView());
    }
    
    public final void toGameOver() {
        this.view.showGameOver(new GameOverController(this).getView());
    }
    
    public final void toEndGame() {
        this.view.showEndGame(new EndGameController(this).getView());
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
