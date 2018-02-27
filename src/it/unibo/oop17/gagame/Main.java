package it.unibo.oop17.gagame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    public final void start(final Stage primaryStage) throws Exception {
        final Group root = new Group();
        primaryStage.setScene(new Scene(root));

        root.getChildren().add(new Label("Hello, world!"));

        primaryStage.show();
    }

}
