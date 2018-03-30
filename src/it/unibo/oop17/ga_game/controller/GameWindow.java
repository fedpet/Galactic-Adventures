package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.IntroScreen;
import it.unibo.oop17.ga_game.view.MainMenuViewImpl;
import it.unibo.oop17.ga_game.view.MainMenuViewInterface;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameWindow {

    private final Stage primaryStage;
    private final Scene scene;
    private final ConfigData data;
    private final GameData save;
    
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 512;

    public GameWindow(final Stage primaryStage) {
        
        final IntroScreen introScreen  = new IntroScreen();
        
        this.scene = new Scene(introScreen);
        this.data = CheckData.loadConfig();
        this.save = CheckSave.loadSave();
        
        this.primaryStage = primaryStage;
        this.primaryStage.setWidth(WIDTH);
        this.primaryStage.setHeight(HEIGHT);
        
        this.primaryStage.getIcons().add(new Image("file:res/icon.png"));
        this.primaryStage.setTitle("Galactic Adventures!");
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
        
        this.buildMenu();
        
    }
    
    public final void buildMenu() {
        
        final MainMenuObserver observer = new MainMenuController(this.data, this.save);
        
        observer.updateLanguage();
        observer.updateView();
        
        final Pane core = new Pane();
        
//        menu.setVisible(true);
//        core.getChildren().addAll(new ImageView(new Image(new RandomBackground().getBackgroundPath())), menu);
//        this.primaryStage.getScene().setRoot(core);
        
    }
    
    public final void buildGame() {
        
//        final GameScreenTest game = new GameScreenTest(this.scene);
//        final GameScreen game = new GameScreen(this.scene, Level.LEVEL_1, this.data);
//        
//        final Group core = game.getWorldView();
//        
//        game.setVisible(true);
//        
//        core.getChildren().addAll(game);
//        
//        this.primaryStage.getScene().setRoot(core);
//        
//        this.scene.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ESCAPE) {
//                this.buildMenu();
//            }
//        });
        
    }
    
    public final void buildIntro() {
        
    }
    
}