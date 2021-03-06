package it.unibo.oop17.ga_game.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import it.unibo.oop17.ga_game.controller.EndGameObserver;
import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import it.unibo.oop17.ga_game.controller.GameOverObserver;
import it.unibo.oop17.ga_game.controller.MainObserver;
import it.unibo.oop17.ga_game.model.Difficulty;
import it.unibo.oop17.ga_game.model.EntityStatistic;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * The main view.
 */
public final class MainViewImpl implements MainView {

    private static final int WIDTH_C = 1280;
    private static final int HEIGHT_C = 720;
    private static final Music MAINMENU_M = Music.TRACK1;
    private static final Music ENDLEVEL_M = Music.TRACK6;
    private final Stage stage;
    private final Group root = new Group();
    private final Scene scene = new Scene(root);
    private final Set<FXView> currentScreens = new HashSet<>(Arrays.asList(new EmptyScreen()));
    private Optional<MainObserver> controller = Optional.empty();
    private Optional<Integer> levelsNum = Optional.empty();
    private AudioPlayer audioplayer;
    private final List<Music> musics = Arrays.asList(
            Music.TRACK3,
            Music.TRACK5,
            Music.TRACK4,
            Music.TRACK2
            );
    private final List<Background> backgrounds = Arrays.asList(
            Background.BG_GRASSLAND,
            Background.BG_GRASSLAND,
            Background.BG_DESERT,
            Background.BG_CASTLE
            );
    private final PlayerKeyboardInput playerInput = new PlayerKeyboardInput(scene);

    /**
     *  Constructor for class MainView.
     *  @param stage
     *          Stage.
     *  @param sfxVol
     *          Sound effect volume.
     *  @param musicVol
     *          Music volume.
     */
    public MainViewImpl(final Stage stage, final Volume sfxVol, final Volume musicVol) {
        this.stage = stage;
        final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.getIcons().add(new Image("/icon.png"));
        stage.setTitle("Galactic Adventures!");
        stage.setResizable(false);
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        audioplayer = new AudioPlayerImpl(sfxVol, musicVol);
        stage.setScene(scene);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                controller.ifPresent(observer -> controller.get().quit());
            }
        });
        stage.show();
    }

    private double getScaleFactor() {
        return Math.max(stage.getWidth() / WIDTH_C, stage.getHeight() / HEIGHT_C);
    }

    private <V extends FXView> V setScreen(final V newScreen, final ImageView im) {
        im.fitWidthProperty().bind(stage.widthProperty());
        im.fitHeightProperty().bind(stage.heightProperty());
        currentScreens.forEach(s -> root.getChildren().remove(s.getNode()));
        currentScreens.clear();
        root.getChildren().add(im);
        currentScreens.add(newScreen);
        currentScreens.forEach(s -> root.getChildren().add(s.getNode()));
        playerInput.clearListener();
        return newScreen;
    }

    @Override
    public MenuScreen showMenu(final Volume musicVol, final Volume sfxVol, final Language language, final Difficulty difficulty) {
        audioplayer.stopMusic();
        audioplayer.playMusic(MAINMENU_M.getPath());
        final List<Background> list = Collections.unmodifiableList(Arrays.asList(Background.values()));
        return setScreen(new MenuViewImpl(stage, musicVol, sfxVol, language, difficulty, audioplayer,
                new LoadLanguage().getCurrLang(language)),
                new ImageView(new Image(list.get(new Random().nextInt(list.size())).getPath())));
    }

    @Override
    public GameWorldView showGame(final Volume musicVol, final Volume sfxVol, final int progress) {
        audioplayer.stopMusic();
        audioplayer = new AudioPlayerImpl(sfxVol, musicVol);
        if (progress <= levelsNum.get().intValue()) {
            audioplayer.playMusic(musics.get(progress).getPath());
        }
        return setScreen(new GameWorldViewImpl(playerInput, getScaleFactor(), audioplayer),
                new ImageView(new Image(backgrounds.get(progress).getPath())));
    }

    @Override
    public CommonView<EndLevelObserver> showEndLevel(final Language language, final int progress,
            final EntityStatistic tracker, final int score) {
        audioplayer.stopMusic();
        audioplayer.playMusic(ENDLEVEL_M.getPath());
        return setScreen(new EndLevelViewImpl(stage, new LoadLanguage().getCurrLang(language), audioplayer, tracker, score),
                new ImageView(new Image(backgrounds.get(progress).getPath())));
    }

    @Override
    public CommonView<GameOverObserver> showGameOver(final Language language) {
        audioplayer.stopMusic();
        return setScreen(new GameOverViewImpl(stage, new LoadLanguage().getCurrLang(language), audioplayer),
                new ImageView(new Image("/gameover.png")));
    }

    @Override
    public CommonView<EndGameObserver> showEndGame(final Language language, final int score) {
        audioplayer.stopMusic();
        audioplayer.playMusic(ENDLEVEL_M.getPath());
        return setScreen(new EndGameViewImpl(stage, new LoadLanguage().getCurrLang(language), audioplayer, score),
                new ImageView(new Image("/congrats.png")));
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    @Override
    public void setObserver(final MainObserver observer) {
        controller = Optional.of(observer);
    }

    @Override
    public void setLevelsNum(final int levelsNum) {
        this.levelsNum = Optional.of(levelsNum);
    }
}
