package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.view.MainView;
import it.unibo.oop17.ga_game.view.MainViewImpl;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Entry point.
 */
public class Main extends Application {
    
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
        
        final MainView view = new MainViewImpl(stage);
        
        new GameController(new GameWorld(), view.showGame(), view.showHud());
        
//        view.showMenu();
        
    }

}
