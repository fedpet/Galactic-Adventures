package it.unibo.oop17.ga_game.controller;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Entry point.
 */
public class Launcher extends Application {
    
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
        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        
        new MainControllerImpl(stage);
        
    }
    
}
