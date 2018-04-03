package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.MenuObserver;
import it.unibo.oop17.ga_game.model.Difficulty;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MenuViewImpl extends Parent implements MenuView {
    
    private final static Music MAINMENU_M = Music.TRACK1;
    private final static int OFFSET = 364;
    private final Group menuView = new Group();;
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
    private final MediaPlayer mediaPlayer;
    
    private Map<Text, String> currLang;
    
    public MenuViewImpl(final Volume musicVol, final Volume sfxVol, final Language language, final Difficulty difficulty, final Map<Text, String> currLang) {
        
        this.currLang = currLang;
        this.mediaPlayer = new MediaPlayer(new Media(MAINMENU_M.getMusic()));
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        this.mediaPlayer.play();
        
        final VBox menu0 = new VBox(8);
        final VBox menu1 = new VBox(8);
        
        menu0.setTranslateX(96);
        menu0.setTranslateY(192);

        menu1.setTranslateX(96);
        menu1.setTranslateY(192);

        menu1.setTranslateX(OFFSET);
        
        this.btnNewGame = new MenuButton(currLang.get(Text.NEW_GAME), sfxVol);
        this.btnNewGame.setOnMouseClicked(event -> {
            this.mediaPlayer.stop();
        });
        
        this.btnContinue = new MenuButton(currLang.get(Text.CONTINUE), sfxVol);
        this.btnContinue.setOnMouseClicked(event -> {
            this.mediaPlayer.stop();
        });
        
        this.btnOptions = new MenuButton(currLang.get(Text.OPTIONS), sfxVol);
        this.btnOptions.setOnMouseClicked(event -> {
            this.menuView.getChildren().add(menu1);

            final TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt.setToX(menu0.getTranslateX() - OFFSET);

            final TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
            tt1.setToX(menu0.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                this.menuView.getChildren().remove(menu0);
            });
        });
        
        this.btnExit = new MenuButton(currLang.get(Text.EXIT), sfxVol);
        this.btnExit.setOnMouseClicked(event -> {
            this.observer.quit();
        });

        this.btnBack = new MenuButton(currLang.get(Text.BACK), sfxVol);
        this.btnBack.setOnMouseClicked(event -> {
            this.menuView.getChildren().add(menu0);

            final TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt.setToX(menu1.getTranslateX() + OFFSET);

            final TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu1.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                this.menuView.getChildren().remove(menu1);
            });
        });
        
        this.btnMusic = new MenuButton(currLang.get(Text.VOLUME_M) + currLang.get(musicVol.asText()), sfxVol);
        this.btnMusic.setOnMouseClicked(event -> this.observer.nextMusicVolume());
        
        this.btnSFX = new MenuButton(currLang.get(Text.VOLUME_S) + currLang.get(sfxVol.asText()), sfxVol);
        this.btnSFX.setOnMouseClicked(event -> this.observer.nextSFXVolume());
        
        this.btnLanguage = new MenuButton(currLang.get(Text.LANGUAGE) + currLang.get(language.asText()), sfxVol);
        this.btnLanguage.setOnMouseClicked(event -> this.observer.nextLanguage());
        
        this.btnDiff = new MenuButton(currLang.get(Text.DIFFICULTY) + currLang.get(difficulty.asText()), sfxVol);
        this.btnDiff.setOnMouseClicked(event -> this.observer.nextDifficulty());
        
        this.btnDefaults = new MenuButton(currLang.get(Text.DEFAULT_OPT), sfxVol);
        this.btnDefaults.setOnMouseClicked(event -> this.observer.setDefaults());

        menu0.getChildren().addAll(btnContinue, btnNewGame, btnOptions, btnExit);
        menu1.getChildren().addAll(btnBack, btnMusic, btnSFX, btnDiff, btnLanguage, btnDefaults);

        final Rectangle bg = new Rectangle();
        bg.setOpacity(0);

        this.menuView.getChildren().addAll(bg, menu0);
    }
    
    public final void setObserver(MenuObserver observer) {
        this.observer = observer;
    }
    
    public final void updateView(final Volume musicVol, final Volume sfxVol, final Language language, final Difficulty difficulty) {
        this.btnNewGame.update(currLang.get(Text.NEW_GAME), sfxVol);
        this.btnContinue.update(currLang.get(Text.CONTINUE), sfxVol);
        this.btnOptions.update(currLang.get(Text.OPTIONS), sfxVol);
        this.btnExit.update(currLang.get(Text.EXIT), sfxVol);
        this.btnBack.update(currLang.get(Text.BACK), sfxVol);
        this.btnMusic.update(currLang.get(Text.VOLUME_M) + currLang.get(musicVol.asText()), sfxVol);
        this.btnSFX.update(currLang.get(Text.VOLUME_S) + currLang.get(sfxVol.asText()), sfxVol);
        this.btnLanguage.update(currLang.get(Text.LANGUAGE) + currLang.get(language.asText()), sfxVol);
        this.btnDiff.update(currLang.get(Text.DIFFICULTY) + currLang.get(difficulty.asText()), sfxVol);
        this.btnDefaults.update(currLang.get(Text.DEFAULT_OPT), sfxVol);
    }
    
    public void updateLanguage(final Map<Text, String> currLang) {
        this.currLang = currLang;
    }
    
    public void updateMusicVol(final Volume musicVol) {
        this.mediaPlayer.setVolume(musicVol.getVolume());
    }
    
    public void setContinueEnabled(final boolean isVisible) {
        this.btnContinue.setVisible(isVisible);
    }
    
    public Node getNode() {
        return this.menuView;
    }
        
}