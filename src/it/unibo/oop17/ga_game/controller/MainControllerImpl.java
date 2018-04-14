package it.unibo.oop17.ga_game.controller;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.MainView;
import it.unibo.oop17.ga_game.view.MainViewImpl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainControllerImpl implements MainController {
    
    private final static int LEVELS_NUM = 7;
    
    private final Stage stage;
    private final MainView view;
    private ConfigData data;
    private GameData save;
    private Optional<GameController> activeGameController = Optional.empty();
    
    MainControllerImpl(final Stage stage) {
        
        this.stage = stage;
        data = CheckData.loadConfig();
        save = CheckSave.loadSave();
        view = new MainViewImpl(stage, data);
        toMenu(); 
        
    }

    public final void toMenu() {
        stopGameController();
        new MenuController(view.showMenu(this), this);
    }
    
    public final void toGame() {
        data = CheckData.loadConfig();
        save = CheckSave.loadSave();
        if (save.getLevelProgress() > LEVELS_NUM) {
            toEndGame();
        } else {
            activeGameController = Optional.of(new GameControllerImpl(view.showGame(this), view.showHud(), this));
        }
    }

    public final void toEndLevel() {
        stopGameController();
        new EndLevelController(view.showEndLevel(this), this);
    }
    
    public final void toGameOver() {
        stopGameController();
        new GameOverController(view.showGameOver(this), this);
    }
    
    public final void toEndGame() {
        stopGameController();
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
    
    private void stopGameController() {
        activeGameController.ifPresent(game -> game.stop());
        activeGameController = Optional.empty();
    }
    
}
