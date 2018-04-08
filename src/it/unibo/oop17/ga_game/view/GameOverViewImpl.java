package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.GameOverObserver;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.Stage;

public class GameOverViewImpl implements CommonView<GameOverObserver> {
    
    private final Group view = new Group();
    private GameOverObserver observer;
    
    public GameOverViewImpl(final Volume sfxVol, final Map<Text, String> currLang, final Stage stage) {
        
        final MenuButton btnRetry = new MenuButton(currLang.get(Text.RETRY), sfxVol);
        btnRetry.setOnMouseClicked(event -> {
            observer.retry();
        });
        
        final MenuButton btnMenu = new MenuButton(currLang.get(Text.MENU), sfxVol);
        btnMenu.setOnMouseClicked(event -> {
            observer.toMainMenu();
        });
        
        final MenuButton btnQuit = new MenuButton(currLang.get(Text.EXIT), sfxVol);
        btnQuit.setOnMouseClicked(event -> {
            observer.quit();
        });
        
        btnRetry.setLayoutX(stage.getWidth() / 10);
        btnRetry.setLayoutY(stage.getHeight() / 4 * 3);
        
        btnMenu.setLayoutX(stage.getWidth() / 10 * 4);
        btnMenu.setLayoutY(stage.getHeight() / 4 * 3);
        
        btnQuit.setLayoutX(stage.getWidth() / 10 * 7);
        btnQuit.setLayoutY(stage.getHeight() / 4 * 3);
        
        this.view.getChildren().addAll(btnRetry, btnMenu, btnQuit);
        
    }

    @Override
    public final void setObserver(final GameOverObserver observer) {
        this.observer = observer;
    }

    @Override
    public Node getNode() {
        return this.view;
    }

}
