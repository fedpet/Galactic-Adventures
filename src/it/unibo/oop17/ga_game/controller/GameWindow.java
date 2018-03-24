package it.unibo.oop17.ga_game.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.oop17.ga_game.model.Level;
import it.unibo.oop17.ga_game.view.GameScreen;
import it.unibo.oop17.ga_game.view.GameScreenTest;
import it.unibo.oop17.ga_game.view.IntroScreen;
import it.unibo.oop17.ga_game.view.MenuScreen;
import it.unibo.oop17.ga_game.view.RandomBackground;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameWindow {

    private final Stage primaryStage;
    private final Scene scene;
    private final ConfigData data;
    private final GameData save;
    
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 512;

    public GameWindow(final Stage primaryStage, final ConfigData data, final GameData save) {
        
        this.data = data;
        this.save = save;
        
        this.primaryStage = primaryStage;
        this.primaryStage.setWidth(WIDTH);
        this.primaryStage.setHeight(HEIGHT);
        
        final IntroScreen introScreen  = new IntroScreen();
        
        this.scene = new Scene(introScreen);
        
        this.primaryStage.setScene(scene);
        this.primaryStage.getIcons().add(new Image("file:res/icon.png"));
        this.primaryStage.setTitle("Galactic Adventures!");
        this.primaryStage.setResizable(false); // Finchè il resize non funziona decentemente
        this.primaryStage.show();
        
        buildMenu();
        
    }
    
    public final void buildMenu() {
        
        final MenuScreen menu = new MenuScreen(this.data, this.save);
        final Pane core = new Pane();

        menu.setVisible(true);
        
        try {
            final InputStream is = Files.newInputStream(Paths.get(new RandomBackground().getBackgroundPath()));
            final Image img = new Image(is);
            is.close();
            final ImageView imgView = new ImageView(img);
            core.getChildren().addAll(imgView, menu);
        } catch (IOException e) {
            System.out.println("ERROR: RESOURCES NOT FOUND");
        }
        
        this.primaryStage.getScene().setRoot(core);
        
        menu.getBtnContinue().setOnMouseClicked(event -> {
            this.init(menu);
        });
        
        menu.getBtnNewGame().setOnMouseClicked(event -> {
            save.resetProgress();
            this.init(menu);
        });
        
    }
    
    public final void buildGame() {
        
//        final GameScreenTest game = new GameScreenTest(this.scene);
        final GameScreen game = new GameScreen(this.scene, Level.LEVEL_1, this.data);
        
        final Group core = game.getWorldView();
        
        game.setVisible(true);
        
        core.getChildren().addAll(game);
        
        this.primaryStage.getScene().setRoot(core);
        
        this.scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                this.buildMenu();
            }
        });
        
    }
    
    public final void init(final MenuScreen menu) {
        menu.getMediaPlayer().stop();
        ResourceManager.save(menu.getConfigData(), "configdata.dat");
        ResourceManager.save(menu.getGameData(), "gamedata.dat");
        this.buildGame();
    }
    
}