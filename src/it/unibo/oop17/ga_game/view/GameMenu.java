package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.controller.Main;
import it.unibo.oop17.ga_game.model.DifficultyManager;
import it.unibo.oop17.ga_game.model.LanguageLoader;
import it.unibo.oop17.ga_game.model.LanguageManager;
import it.unibo.oop17.ga_game.model.MusicVolumeManager;
import it.unibo.oop17.ga_game.model.ResetSave;
import it.unibo.oop17.ga_game.model.SFXVolumeManager;
import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.Text;
import it.unibo.oop17.ga_game.model.ResourceManager;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameMenu extends Parent {
    
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
    private final LanguageLoader lang;
    private final MusicVolumeManager mvm;
    private final SFXVolumeManager svm;
    private final LanguageManager lm;
    private final DifficultyManager dm;
    private final MediaPlayer mediaPlayer;
    private ConfigData save;
    
    public GameMenu() {
        
        final VBox menu0 = new VBox(8);
        final VBox menu1 = new VBox(8);
        
        this.save = ResourceManager.load("configdata.dat");
        
        this.mediaPlayer = new MediaPlayer(new Media(Music.TRACK1.getMusic()));
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        this.mediaPlayer.setVolume(save.musicVol.getVolume());
        this.mediaPlayer.play();
        
        this.lang = new LanguageLoader();
        lang.loadLanguage(save.language);

        menu0.setTranslateX(96);
        menu0.setTranslateY(192);

        menu1.setTranslateX(96);
        menu1.setTranslateY(192);

        final int offset = 384;

        menu1.setTranslateX(offset);
        
        this.btnNewGame = new MenuButton(lang.getText(Text.NEW_GAME));
        btnNewGame.setOnMouseClicked(event -> {
            // LANCIA IL TEST
            ResetSave.resetProgress();
            final String[] args = {};
            Main.main(args);
        });

        this.btnContinue = new MenuButton(lang.getText(Text.CONTINUE));
        btnContinue.setOnMouseClicked(event -> {
            // LANCIA IL TEST
            final String[] args = {};
            Main.main(args);
        });

        this.btnOptions = new MenuButton(lang.getText(Text.OPTIONS));
        btnOptions.setOnMouseClicked(event -> {
            getChildren().add(menu1);

            final TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt.setToX(menu0.getTranslateX() - offset);

            final TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
            tt1.setToX(menu0.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu0);
            });
        });

        this.btnExit = new MenuButton(lang.getText(Text.EXIT));
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        this.btnBack = new MenuButton(lang.getText(Text.BACK));
        btnBack.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            final TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt.setToX(menu1.getTranslateX() + offset);

            final TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu1.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu1);
            });
        });
        
        this.mvm = new MusicVolumeManager();
        this.btnMusic = new MenuButton(lang.getText(Text.VOLUME_M) + lang.getText(mvm.getMusicVolumeText()));
        btnMusic.setOnMouseClicked(event -> {
            mvm.next();
            updateSave();
            updateMusic();
            this.btnMusic.update(lang.getText(Text.VOLUME_M) + lang.getText(mvm.getMusicVolumeText()));
        });
        
        this.svm = new SFXVolumeManager();
        this.btnSFX = new MenuButton(lang.getText(Text.VOLUME_S) + lang.getText(svm.getSFXVolumeText()));
        btnSFX.setOnMouseClicked(event -> {
            svm.next();
            updateSave();
            updateBtn();
        });
        
        this.lm = new LanguageManager();
        this.btnLanguage = new MenuButton(lang.getText(Text.LANGUAGE) + lang.getText(lm.getLanguageText()));
        btnLanguage.setOnMouseClicked(event -> {
            lm.next();
            updateSave();
            lang.loadLanguage(this.save.language);
            updateBtn();
        });
        
        this.dm = new DifficultyManager();
        this.btnDiff = new MenuButton(lang.getText(Text.DIFFICULTY) + lang.getText(dm.getDifficultyText()));
        btnDiff.setOnMouseClicked(event -> {
            dm.next();
            this.btnDiff.update(lang.getText(Text.DIFFICULTY) + lang.getText(dm.getDifficultyText()));
        });
        
        this.btnDefaults = new MenuButton(lang.getText(Text.DEFAULT_OPT));
        btnDefaults.setOnMouseClicked(event -> {
//            ResetSave.defaultOptions();
//            updateSave();
//            updateBtn();
        });

        menu0.getChildren().addAll(btnContinue, btnNewGame, btnOptions, btnExit);
        menu1.getChildren().addAll(btnBack, btnMusic, btnSFX, btnDiff, btnLanguage, btnDefaults);

        final Rectangle bg = new Rectangle(1024, 512);
        bg.setOpacity(0);

        getChildren().addAll(bg, menu0);
    }
    
    private void updateBtn() {
        this.btnContinue.update(lang.getText(Text.NEW_GAME));
        this.btnNewGame.update(lang.getText(Text.CONTINUE));
        this.btnOptions.update(lang.getText(Text.OPTIONS));
        this.btnExit.update(lang.getText(Text.EXIT));
        this.btnBack.update(lang.getText(Text.BACK));
        this.btnMusic.update(lang.getText(Text.VOLUME_M) + lang.getText(mvm.getMusicVolumeText()));
        this.btnSFX.update(lang.getText(Text.VOLUME_S) + lang.getText(svm.getSFXVolumeText()));
        this.btnLanguage.update(lang.getText(Text.LANGUAGE) + lang.getText(lm.getLanguageText()));
        this.btnDiff.update(lang.getText(Text.DIFFICULTY) + lang.getText(dm.getDifficultyText()));
        this.btnDefaults.update(lang.getText(Text.DEFAULT_OPT));
    }
    
    private void updateSave() {
        this.save = ResourceManager.load("configdata.dat");
        this.lang.loadLanguage(save.language);
    }
    
    private void updateMusic() {
        this.mediaPlayer.stop();
        this.mediaPlayer.setVolume(save.musicVol.getVolume());
        this.mediaPlayer.play();
    }
}