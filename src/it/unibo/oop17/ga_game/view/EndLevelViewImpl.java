package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import javafx.scene.Group;
import javafx.scene.Node;

public class EndLevelViewImpl implements CommonView<EndLevelObserver> {
    
    private final Group view = new Group();
    private EndLevelObserver observer;
    
    public EndLevelViewImpl(final Volume sfxVol, final Map<Text, String> currLang) {
        
        final MenuButton btnContinue = new MenuButton(currLang.get(Text.CONTINUE), sfxVol);
        btnContinue.setOnMouseClicked(event -> {
            observer.toNextMap();
        });
        
        final MenuButton btnMenu = new MenuButton(currLang.get(Text.MENU), sfxVol);
        btnMenu.setOnMouseClicked(event -> {
            observer.toMainMenu();
        });
        
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
