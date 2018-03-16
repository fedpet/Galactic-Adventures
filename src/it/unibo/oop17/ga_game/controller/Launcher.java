package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        new MainMenu(primaryStage);
    }

    public static void main(final String[] args) {
        launch(args);
    }
}