package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.MenuObserver;
import it.unibo.oop17.ga_game.model.Difficulty;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MenuViewImpl extends Parent implements MenuView {
    
    
    private final static int OFFSET = 364;
    private final Group menuView = new Group();
    private MenuObserver observer;
    
    private final MenuButton btnNewGame;
    private final MenuButton btnContinue;
    private final MenuButton btnOptions;
    private final MenuButton btnExit;
    private final MenuButton btnBack;
    private final MenuButton btnMusic;
    private final MenuButton btnSFX;
    private final MenuButton btnLanguage;
    private final MenuButton btnDiff;
    private final MenuButton btnDefaults;
    
    private Map<Text, String> currLang;
    
    public MenuViewImpl(final Volume musicVol, final Volume sfxVol, final Language language, final Difficulty difficulty, final Map<Text, String> currLang) {
        
        super();
        
        this.currLang = currLang;
        
        final VBox menu0 = new VBox(8);
        final VBox menu1 = new VBox(8);
        
        menu0.setTranslateX(96);
        menu0.setTranslateY(192);

        menu1.setTranslateX(96);
        menu1.setTranslateY(192);

        menu1.setTranslateX(OFFSET);
        
        btnNewGame = new MenuButton(currLang.get(Text.NEW_GAME));
        btnNewGame.setOnMouseClicked(event -> {
            observer.newGame();
        });
        
        btnContinue = new MenuButton(currLang.get(Text.CONTINUE));
        btnContinue.setOnMouseClicked(event -> {
            observer.continueGame();
        });
        
        btnOptions = new MenuButton(currLang.get(Text.OPTIONS));
        btnOptions.setOnMouseClicked(event -> {
            menuView.getChildren().add(menu1);

            final TranslateTransition tt0 = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt0.setToX(menu0.getTranslateX() - OFFSET);

            final TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
            tt1.setToX(menu0.getTranslateX());

            tt0.play();
            tt1.play();

            tt0.setOnFinished(evt -> {
                menuView.getChildren().remove(menu0);
            });
        });
        
        btnExit = new MenuButton(currLang.get(Text.EXIT));
        btnExit.setOnMouseClicked(event -> {
            observer.quit();
        });

        btnBack = new MenuButton(currLang.get(Text.BACK));
        btnBack.setOnMouseClicked(event -> {
            menuView.getChildren().add(menu0);

            final TranslateTransition tt0 = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt0.setToX(menu1.getTranslateX() + OFFSET);

            final TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu1.getTranslateX());

            tt0.play();
            tt1.play();

            tt0.setOnFinished(evt -> {
                menuView.getChildren().remove(menu1);
            });
        });
        
        btnMusic = new MenuButton(currLang.get(Text.VOLUME_M) + currLang.get(musicVol.asText()));
        btnMusic.setOnMouseClicked(event -> this.observer.nextMusicVolume());
        
        btnSFX = new MenuButton(currLang.get(Text.VOLUME_S) + currLang.get(sfxVol.asText()));
        btnSFX.setOnMouseClicked(event -> this.observer.nextSFXVolume());
        
        btnLanguage = new MenuButton(currLang.get(Text.LANGUAGE) + currLang.get(language.asText()));
        btnLanguage.setOnMouseClicked(event -> this.observer.nextLanguage());
        
        btnDiff = new MenuButton(currLang.get(Text.DIFFICULTY) + currLang.get(difficulty.asText()));
        btnDiff.setOnMouseClicked(event -> this.observer.nextDifficulty());
        
        btnDefaults = new MenuButton(currLang.get(Text.DEFAULT_OPT));
        btnDefaults.setOnMouseClicked(event -> this.observer.setDefaults());

        menu0.getChildren().addAll(btnContinue, btnNewGame, btnOptions, btnExit);
        menu1.getChildren().addAll(btnBack, btnMusic, btnSFX, btnDiff, btnLanguage, btnDefaults);

        final Rectangle bg0 = new Rectangle();
        bg0.setOpacity(0);

        this.menuView.getChildren().addAll(bg0, menu0);
    }
    
    public final void setObserver(final MenuObserver observer) {
        this.observer = observer;
    }
    
    public final void updateView(final Volume musicVol, final Volume sfxVol, final Language language, final Difficulty difficulty) {
        btnNewGame.update(currLang.get(Text.NEW_GAME));
        btnContinue.update(currLang.get(Text.CONTINUE));
        btnOptions.update(currLang.get(Text.OPTIONS));
        btnExit.update(currLang.get(Text.EXIT));
        btnBack.update(currLang.get(Text.BACK));
        btnMusic.update(currLang.get(Text.VOLUME_M) + currLang.get(musicVol.asText()));
        btnSFX.update(currLang.get(Text.VOLUME_S) + currLang.get(sfxVol.asText()));
        btnLanguage.update(currLang.get(Text.LANGUAGE) + currLang.get(language.asText()));
        btnDiff.update(currLang.get(Text.DIFFICULTY) + currLang.get(difficulty.asText()));
        btnDefaults.update(currLang.get(Text.DEFAULT_OPT));
    }
    
    public void updateLanguage(final Map<Text, String> currLang) {
        this.currLang = currLang;
    }
    
    public void setContinueEnabled(final boolean isVisible) {
        btnContinue.setVisible(isVisible);
    }
    
    public Node getNode() {
        return menuView;
    }
        
}