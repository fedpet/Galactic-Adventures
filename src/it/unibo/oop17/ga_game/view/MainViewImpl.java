package it.unibo.oop17.ga_game.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import it.unibo.oop17.ga_game.controller.EndGameController;
import it.unibo.oop17.ga_game.controller.EndLevelController;
import it.unibo.oop17.ga_game.controller.GameOverController;
import it.unibo.oop17.ga_game.model.Difficulty;
import it.unibo.oop17.ga_game.model.EntityStatistic;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The main view.
 */
public final class MainViewImpl implements MainView {

    private static final int WIDTH_D = 1280;
    private static final int HEIGHT_D = 720;
    private static final int WIDTH_C = 1920;
    private static final int HEIGHT_C = 1080;
    private static final Music MAINMENU_M = Music.TRACK1;
    private static final Music ENDLEVEL_M = Music.TRACK6;
    private static final int LEVELS_NUM = 7;

    private final Stage stage;
    private final Group root = new Group();
    private final Scene scene = new Scene(root);
    private final Set<FXView> currentScreens = new HashSet<>(Arrays.asList(new EmptyScreen()));
    private AudioPlayer audioplayer;
    private final List<Music> musics = Arrays.asList(
            Music.TRACK3,
            Music.TRACK2,
            Music.TRACK4,
            Music.TRACK5,
            Music.TRACK3,
            Music.TRACK2,
            Music.TRACK4,
            Music.TRACK5);
    private final List<Background> backgrounds = Arrays.asList(
            Background.BG_GRASSLAND,
            Background.BG_CASTLE,
            Background.BG_DESERT,
            Background.BG_GRASSLAND,
            Background.BG_DESERT,
            Background.BG_DESERT,
            Background.BG_SHROOM,
            Background.BG_CASTLE);

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
        stage.getIcons().add(new Image("/icon.png"));
        stage.setTitle("Galactic Adventures!");
        stage.setWidth(WIDTH_D);
        stage.setHeight(HEIGHT_D);
        stage.setResizable(false);
        this.stage = stage;
        audioplayer = new AudioPlayerImpl(sfxVol, musicVol);
        stage.setScene(scene);
        stage.show();
    }

    private double getScaleFactor() {
        final Rectangle2D primaryScreenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
        return Math.min(primaryScreenBounds.getWidth() / WIDTH_C, primaryScreenBounds.getHeight() / HEIGHT_C);
    }

    private <V extends FXView> V setScreen(final V newScreen, final ImageView im) {
        im.fitWidthProperty().bind(stage.widthProperty());
        im.fitHeightProperty().bind(stage.heightProperty());
        currentScreens.forEach(s -> root.getChildren().remove(s.getNode()));
        currentScreens.clear();
        root.getChildren().add(im);
        currentScreens.add(newScreen);
        currentScreens.forEach(s -> root.getChildren().add(s.getNode()));
        return newScreen;
    }

    @Override
    public MenuView showMenu(final Volume musicVol, final Volume sfxVol, final Language language, final Difficulty difficulty) {
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
        if (progress < LEVELS_NUM) {
            audioplayer.playMusic(musics.get(progress).getPath());
        }
        return setScreen(new GameWorldViewImpl(new PlayerKeyboardInput(scene), getScaleFactor(), audioplayer),
                new ImageView(new Image(backgrounds.get(progress).getPath())));
    }

    @Override
    public void showError(final String message) {
        new Alert(AlertType.ERROR, message).showAndWait();
    }

    @Override
    public CommonView<EndLevelController> showEndLevel(final Language language, final int progress,
            final EntityStatistic tracker, final int score) {
        audioplayer.stopMusic();
        audioplayer.playMusic(ENDLEVEL_M.getPath());
        return setScreen(new EndLevelViewImpl(stage, new LoadLanguage().getCurrLang(language), audioplayer, tracker, score),
                new ImageView(new Image(backgrounds.get(progress).getPath())));
    }

    @Override
    public CommonView<GameOverController> showGameOver(final Language language) {
        audioplayer.stopMusic();
        return setScreen(new GameOverViewImpl(stage, new LoadLanguage().getCurrLang(language), audioplayer),
                new ImageView(new Image("/gameover.png")));
    }

    @Override
    public CommonView<EndGameController> showEndGame(final Language language, final int score) {
        audioplayer.stopMusic();
        audioplayer.playMusic(ENDLEVEL_M.getPath());
        return setScreen(new EndGameViewImpl(stage, new LoadLanguage().getCurrLang(language), audioplayer, score),
                new ImageView(new Image("/congrats.png")));
    }
}
