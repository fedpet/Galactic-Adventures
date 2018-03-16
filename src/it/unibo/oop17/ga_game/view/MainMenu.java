package it.unibo.oop17.ga_game.view;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;

public class MainMenu {

    private final GameMenu gameMenu;

    public MainMenu(final Stage primaryStage) throws IOException, ClassNotFoundException {
        
        final Pane core = new Pane();
        core.setPrefSize(1024, 512);

        final InputStream is = Files.newInputStream(Paths.get(new RandomBackground().getBackgroundPath()));
        final Image img = new Image(is);
        is.close();

        final ImageView imgView = new ImageView(img);

        gameMenu = new GameMenu(primaryStage);
        gameMenu.setVisible(true);

        core.getChildren().addAll(imgView, gameMenu);

        final Scene scene = new Scene(core);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (!gameMenu.isVisible()) {
                    final FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    gameMenu.setVisible(true);
                    ft.play();
                }
                else {
                    final FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt -> gameMenu.setVisible(false));
                    ft.play();
                }
            }
        });
        
        primaryStage.getIcons().add(new Image("file:res/icon.png"));
        primaryStage.setTitle("Galactic Adventures!");
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
}