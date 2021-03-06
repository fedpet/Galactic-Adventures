package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.MenuWithOptionsObserver;
import it.unibo.oop17.ga_game.model.Difficulty;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The menu view.
 */
public final class MenuViewImpl extends Parent implements MenuScreen {

    private static final int OFFSET = 364;
    private static final int BTN_SIZE = 384;
    private static final String TEXT_OFF = "     ";

    private final Group menuView = new Group();
    private MenuWithOptionsObserver observer;
    private Volume musicVol;
    private Volume sfxVol;
    private Language language;
    private Difficulty difficulty;
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

    private Map<WordText, String> currLang;

    /**
     * Constructor of MenuView.
     * @param stage
     *          The stage.
     * @param musicVol
     *          The music volume.
     * @param sfxVol
     *          The sound effect volume.
     * @param language
     *          The language.
     * @param difficulty
     *          The difficulty.
     * @param audioplayer
     *          The audio player.
     * @param currLang
     *          The current Language.
     */
    public MenuViewImpl(final Stage stage, final Volume musicVol, final Volume sfxVol, final Language language,
            final Difficulty difficulty, final AudioPlayer audioplayer, final Map<WordText, String> currLang) {

        super();
        this.musicVol = musicVol;
        this.sfxVol = sfxVol;
        this.language = language;
        this.difficulty = difficulty;
        this.currLang = currLang;

        final VBox menu0 = new VBox(8);
        final VBox menu1 = new VBox(8);

        btnNewGame = new MenuButton(currLang.get(WordText.NEW_GAME), audioplayer);
        btnNewGame.setOnMouseClicked(event -> {
            observer.newGame();
        });

        btnContinue = new MenuButton(currLang.get(WordText.CONTINUE), audioplayer);
        btnContinue.setOnMouseClicked(event -> {
            observer.continueGame();
        });

        btnOptions = new MenuButton(currLang.get(WordText.OPTIONS), audioplayer);
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

        btnExit = new MenuButton(currLang.get(WordText.EXIT), audioplayer);
        btnExit.setOnMouseClicked(event -> {
            observer.quit();
        });

        btnBack = new MenuButton(currLang.get(WordText.BACK), audioplayer);
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

        btnMusic = new MenuButton(currLang.get(WordText.VOLUME_M) + TEXT_OFF + currLang.get(musicVol.asText()), audioplayer);
        btnMusic.setOnMouseClicked(event -> {
            nextMusicVol();
            observer.setMusicVolume(this.musicVol);
        });

        btnSFX = new MenuButton(currLang.get(WordText.VOLUME_S) + TEXT_OFF + currLang.get(sfxVol.asText()), audioplayer);
        btnSFX.setOnMouseClicked(event -> {
            nextSFXVol();
            observer.setSFXVolume(this.sfxVol);
        });

        btnLanguage = new MenuButton(currLang.get(WordText.LANGUAGE) + TEXT_OFF + currLang.get(language.asText()), audioplayer);
        btnLanguage.setOnMouseClicked(event -> {
            nextLanguage();
            observer.setLanguage(this.language);
        });

        btnDiff = new MenuButton(currLang.get(WordText.DIFFICULTY) + TEXT_OFF + currLang.get(difficulty.asText()), audioplayer);
        btnDiff.setOnMouseClicked(event -> {
            nextDifficulty();
            observer.setDifficulty(this.difficulty);
        });

        btnDefaults = new MenuButton(currLang.get(WordText.DEFAULT_OPT), audioplayer);
        btnDefaults.setOnMouseClicked(event -> observer.setDefaults());

        menu0.getChildren().addAll(btnContinue, btnNewGame, btnOptions, btnExit);
        menu1.getChildren().addAll(btnBack, btnMusic, btnSFX, btnDiff, btnLanguage, btnDefaults);

        final Rectangle bg0 = new Rectangle();
        bg0.setOpacity(0);

        menu0.setLayoutX((stage.getWidth() - BTN_SIZE) / 2);
        menu0.setLayoutY(stage.getHeight() - stage.getHeight() / 2);

        menu1.setLayoutX((stage.getWidth() - BTN_SIZE) / 2);
        menu1.setLayoutY(stage.getHeight() - stage.getHeight() / 2);

        final Image im = new Image(getClass().getResource("/logo.png").toString());
        final ImageView imv = new ImageView(im);
        imv.setLayoutX((stage.getWidth() - im.getWidth()) / 2);
        imv.setLayoutY((stage.getHeight() - im.getWidth()) / 3);

        menuView.getChildren().addAll(imv, bg0, menu0);
    }

    @Override
    public void setObserver(final MenuWithOptionsObserver observer) {
        this.observer = observer;
    }

    @Override
    public void updateView(final Volume musicVol, final Volume sfxVol, final Language language, final Difficulty difficulty) {
        btnNewGame.update(currLang.get(WordText.NEW_GAME));
        btnContinue.update(currLang.get(WordText.CONTINUE));
        btnOptions.update(currLang.get(WordText.OPTIONS));
        btnExit.update(currLang.get(WordText.EXIT));
        btnBack.update(currLang.get(WordText.BACK));
        btnMusic.update(currLang.get(WordText.VOLUME_M) + TEXT_OFF + currLang.get(musicVol.asText()));
        btnSFX.update(currLang.get(WordText.VOLUME_S) + TEXT_OFF + currLang.get(sfxVol.asText()));
        btnLanguage.update(currLang.get(WordText.LANGUAGE) + TEXT_OFF + currLang.get(language.asText()));
        btnDiff.update(currLang.get(WordText.DIFFICULTY) + TEXT_OFF + currLang.get(difficulty.asText()));
        btnDefaults.update(currLang.get(WordText.DEFAULT_OPT));
    }

    @Override
    public void updateLanguage(final Map<WordText, String> currLang) {
        this.currLang = currLang;
    }

    @Override
    public void setContinueEnabled(final boolean isVisible) {
        btnContinue.setVisible(isVisible);
    }

    @Override
    public Node getNode() {
        return menuView;
    }

    private void nextMusicVol() {
        this.musicVol = Volume.values()[(this.musicVol.ordinal() + 1) % Volume.values().length];
    }

    private void nextSFXVol() {
        this.sfxVol = Volume.values()[(this.sfxVol.ordinal() + 1) % Volume.values().length];
    }

    private void nextLanguage() {
        this.language = Language.values()[(this.language.ordinal() + 1) % Language.values().length];
    }

    private void nextDifficulty() {
        this.difficulty = Difficulty.values()[(this.difficulty.ordinal() + 1) % Difficulty.values().length];
    }

    @Override
    public void quit() {
        Platform.exit();
    }
}
