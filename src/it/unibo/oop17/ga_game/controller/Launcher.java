package it.unibo.oop17.ga_game.controller;

import javafx.application.Application;
import javafx.stage.Stage;

import it.unibo.oop17.ga_game.view.MainMenu;

public class Launcher extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        new MainMenu(primaryStage);
    }

    public static void main(final String[] args) {
        launch(args);
    }
}