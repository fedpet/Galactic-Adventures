package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.EndGameObserver;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.Stage;

public class EndGameViewImpl implements CommonView<EndGameObserver> {
    
    private final Group view = new Group();
    private EndGameObserver observer;
    
    public EndGameViewImpl(final Map<WordText, String> currLang, final Stage stage) {
        
        final MenuButton btnMenu = new MenuButton(currLang.get(WordText.MENU));
        btnMenu.setOnMouseClicked(event -> {
            observer.toMainMenu();
        });
        
        final MenuButton btnQuit = new MenuButton(currLang.get(WordText.EXIT));
        btnQuit.setOnMouseClicked(event -> {
            observer.quit();
        });
        
        btnMenu.setLayoutX(stage.getWidth() / 10 * 2);
        btnMenu.setLayoutY(stage.getHeight() / 4 * 3);
        
        btnQuit.setLayoutX(stage.getWidth() / 10 * 5);
        btnQuit.setLayoutY(stage.getHeight() / 4 * 3);
        
        this.view.getChildren().addAll(btnMenu, btnQuit);
        
    }

    @Override
    public final void setObserver(final EndGameObserver observer) {
        this.observer = observer;
    }

    @Override
    public Node getNode() {
        return this.view;
    }

}
