package it.unibo.oop17.ga_game.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        new GameWindow(primaryStage, CheckData.loadConfig(), CheckSave.loadSave());
    }

    public static void main(final String[] args) {
        launch(args);
    }
}