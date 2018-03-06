package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.CasualBackground;
import it.unibo.oop17.ga_game.model.GameMenu;
import it.unibo.oop17.ga_game.model.Music;
import it.unibo.oop17.ga_game.model.Volume;

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

    public MainMenu(final Stage primaryStage) throws IOException {

        final Pane core = new Pane();
        core.setPrefSize(1024, 512);
        
        final Music music = new Music();
        
        // DEVE ESSERE SALVATO IN UN FILE DI CONFIGURAZIONE IL VOLUME ATTUALE INSIEME ALLE ALTRE OPZIONI
        final Volume vol = Volume.LOW;
        final double volume = vol.getVolume();
        
        music.getMusic("TRACK1").play(volume);

        final InputStream is = Files.newInputStream(Paths.get(new CasualBackground().getBackgroundPath()));
        final Image img = new Image(is);
        is.close();

        final ImageView imgView = new ImageView(img);
        imgView.setFitWidth(1024);
        imgView.setFitHeight(512);

        gameMenu = new GameMenu();
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