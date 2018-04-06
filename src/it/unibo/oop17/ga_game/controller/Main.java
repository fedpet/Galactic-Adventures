package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.entities.events.FinishedLevelEvent;
import it.unibo.oop17.ga_game.view.MainView;
import it.unibo.oop17.ga_game.view.MainViewImpl;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Entry point.
 */
public class Main extends Application {
    
    private MainView view;
    private ConfigData data;
    private GameData save;
    
    /**
     * Entry point.
     * 
     * @param args
     *            Command-line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        
        stage.getIcons().add(new Image("file:res/icon.png"));
        stage.setTitle("Galactic Adventures!");
        
        this.data = CheckData.loadConfig();
        this.save = CheckSave.loadSave();
        
        this.view = new MainViewImpl(stage);
        
        this.toGame();
    }
    
    public void toMenu() {
        this.view.showMenu(new MenuController(this.data, this.save).getView());
    }
    
    public void toGame() {
        new GameControllerImpl(new GameWorld(), view.showGame(), view.showHud());
    }

    public void toEndLevel() {
        System.out.println("trollface");
        this.view.showEndLevel();
    }

    
}
