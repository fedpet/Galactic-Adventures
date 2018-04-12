package it.unibo.oop17.ga_game.controller;

import java.util.concurrent.TimeUnit;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.MainView;
import it.unibo.oop17.ga_game.view.MainViewImpl;
import javafx.stage.Stage;

public class MainControllerImpl implements MainController {
    
    private final Stage stage;
    private final MainView view;
    private ConfigData data;
    private GameData save;
    
    MainControllerImpl(final Stage stage) {
        
        this.stage = stage;
        data = CheckData.loadConfig();
        save = CheckSave.loadSave();
        view = new MainViewImpl(stage, data);
        toMenu(); 
        
    }

    public final void toMenu() {
        new MenuController(view.showMenu(this), this);
    }
    
    public final void toGame() {
        data = CheckData.loadConfig();
        save = CheckSave.loadSave();
        new GameControllerImpl(view.showGame(this.save), view.showHud(), this);
    }

    public final void toEndLevel() {
        slowTransiction();
        new EndLevelController(view.showEndLevel(this), this);
    }
    
    public final void toGameOver() {
        slowTransiction();
        new GameOverController(view.showGameOver(this), this);
    }
    
    public final void toEndGame() {
        slowTransiction();
        new EndGameController(view.showEndGame(this), this);
    }
    
    public Stage getStage() {
        return stage;
    }
    
    public ConfigData getConfigData() {
        return data;
    }
    
    public GameData getGameData() {
        return save;
    }

    @Override
    public void updateMusicVol() {
        view.updateMusicVol();
    }
    
    private void slowTransiction() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    
}
