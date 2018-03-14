package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.controller.Main;
import it.unibo.oop17.ga_game.model.CheckConfig;
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
    private final MediaPlayer mediaPlayer;
    private ConfigData save;
    
    public GameMenu() {
        
        final VBox menu0 = new VBox(8);
        final VBox menu1 = new VBox(8);
        
        this.save = CheckConfig.checkIfConfigExists();
        
        this.mediaPlayer = new MediaPlayer(new Media(Music.TRACK1.getMusic()));
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        this.mediaPlayer.setVolume(save.getMusicVol().getVolume());
        this.mediaPlayer.play();
        
        this.lang = new LanguageLoader();
        lang.loadLanguage(save.getLanguage());

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
        
        this.btnMusic = new MenuButton(lang.getText(Text.VOLUME_M) + lang.getText(MusicVolumeManager.getMusicVolumeText()));
        btnMusic.setOnMouseClicked(event -> {
            MusicVolumeManager.next();
            updateSave();
            updateMusic();
            this.btnMusic.update(lang.getText(Text.VOLUME_M) + lang.getText(MusicVolumeManager.getMusicVolumeText()));
        });
        
        this.btnSFX = new MenuButton(lang.getText(Text.VOLUME_S) + lang.getText(SFXVolumeManager.getSFXVolumeText()));
        btnSFX.setOnMouseClicked(event -> {
            SFXVolumeManager.next();
            updateSave();
            updateBtn();
        });
        
        this.btnLanguage = new MenuButton(lang.getText(Text.LANGUAGE) + lang.getText(LanguageManager.getLanguageText()));
        btnLanguage.setOnMouseClicked(event -> {
            LanguageManager.next();
            updateSave();
            lang.loadLanguage(this.save.getLanguage());
            updateBtn();
        });
        
        this.btnDiff = new MenuButton(lang.getText(Text.DIFFICULTY) + lang.getText(DifficultyManager.getDifficultyText()));
        btnDiff.setOnMouseClicked(event -> {
            DifficultyManager.next();
            this.btnDiff.update(lang.getText(Text.DIFFICULTY) + lang.getText(DifficultyManager.getDifficultyText()));
//            System.out.println(ResourceManager.load("configdata.dat").difficulty);
        });
        
        this.btnDefaults = new MenuButton(lang.getText(Text.DEFAULT_OPT));
        btnDefaults.setOnMouseClicked(event -> {
            ResetSave.defaultOptions();
            updateMusic();
            updateSave();
            updateBtn();
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
        this.btnMusic.update(lang.getText(Text.VOLUME_M) + lang.getText(MusicVolumeManager.getMusicVolumeText()));
        this.btnSFX.update(lang.getText(Text.VOLUME_S) + lang.getText(SFXVolumeManager.getSFXVolumeText()));
        this.btnLanguage.update(lang.getText(Text.LANGUAGE) + lang.getText(LanguageManager.getLanguageText()));
        this.btnDiff.update(lang.getText(Text.DIFFICULTY) + lang.getText(DifficultyManager.getDifficultyText()));
        this.btnDefaults.update(lang.getText(Text.DEFAULT_OPT));
//        System.out.println(ResourceManager.load("configdata.dat").difficulty);
    }
    
    private void updateSave() {
        this.save = ResourceManager.load("configdata.dat");
        this.lang.loadLanguage(this.save.getLanguage());
    }
    
    private void updateMusic() {
        this.mediaPlayer.stop();
        this.mediaPlayer.setVolume(this.save.getMusicVol().getVolume());
        this.mediaPlayer.play();
    }
}