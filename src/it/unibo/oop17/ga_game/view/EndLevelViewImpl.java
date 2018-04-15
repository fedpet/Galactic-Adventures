package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.Stage;

public class EndLevelViewImpl implements CommonView<EndLevelObserver> {
    
    private final Group view = new Group();
    private EndLevelObserver observer;
    
    public EndLevelViewImpl(final Map<WordText, String> currLang, final Stage stage) {
        
        final MenuButton btnContinue = new MenuButton(currLang.get(WordText.CONTINUE));
        btnContinue.setOnMouseClicked(event -> {
            observer.toNextMap();
        });
        
        final MenuButton btnMenu = new MenuButton(currLang.get(WordText.MENU));
        btnMenu.setOnMouseClicked(event -> {
            observer.toMainMenu();
        });
        
        btnContinue.setLayoutX(stage.getWidth() / 10 * 2);
        btnContinue.setLayoutY(stage.getHeight() / 4 * 3);
        
        btnMenu.setLayoutX(stage.getWidth() / 10 * 5);
        btnMenu.setLayoutY(stage.getHeight() / 4 * 3);
        
        this.view.getChildren().addAll(btnContinue, btnMenu);
        
    }

    @Override
    public final void setObserver(final EndLevelObserver observer) {
        this.observer = observer;
    }

    @Override
    public Node getNode() {
        return this.view;
    }

}
