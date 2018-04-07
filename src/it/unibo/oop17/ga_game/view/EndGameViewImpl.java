package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.EndGameObserver;
import javafx.scene.Group;
import javafx.scene.Node;

public class EndGameViewImpl implements CommonView<EndGameObserver> {
    
    private final Group view = new Group();
    private EndGameObserver observer;
    
    public EndGameViewImpl(final Volume sfxVol, final Map<Text, String> currLang) {
        
        final MenuButton btnMenu = new MenuButton(currLang.get(Text.MENU), sfxVol);
        btnMenu.setOnMouseClicked(event -> {
            observer.toMainMenu();
        });
        
        final MenuButton btnQuit = new MenuButton(currLang.get(Text.EXIT), sfxVol);
        btnQuit.setOnMouseClicked(event -> {
            observer.quit();
        });
        
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
