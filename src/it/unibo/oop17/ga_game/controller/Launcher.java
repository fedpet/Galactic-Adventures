package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.view.MainViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point.
 */
public final class Launcher extends Application {

    /**
     * Entry point.
     * 
     * @param args
     *            Command-line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * {@inheritDoc}
     */
    public void start(final Stage stage) {
        final ConfigData data = LoadSaveManager.checkConfigDataExistenceThenLoad();
        new MainControllerImpl(new MainViewImpl(stage, data.getMusicVol(), data.getMusicVol()));
    }

}
