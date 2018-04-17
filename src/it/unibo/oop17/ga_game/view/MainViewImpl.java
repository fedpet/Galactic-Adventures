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
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The main view.
 */
public final class MainViewImpl implements MainView {

    private static final Music MAINMENU_M = Music.TRACK1;
    private static final Music ENDLEVEL_M = Music.TRACK6;
    private static final int LEVELS_NUM = 7;

    private final Stage stage;
    private final Group root = new Group();
    private final Scene scene = new Scene(root);
    private final Set<Screen> currentScreens = new HashSet<>(Arrays.asList(new EmptyScreen()));
//    private final AudioController audioC;
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
     *          Actual stage.
     */
    public MainViewImpl(final Stage stage) {
        stage.getIcons().add(new Image("/icon.png"));
        stage.setTitle("Galactic Adventures!");
        this.stage = stage;
//        final AudioView audioV = new AudioViewImpl(data.getSFXVol(), data.getMusicVol());
//        audioC = new AudioControllerImpl(audioV);
        stage.setScene(scene);
        stage.show();
    }

    private double getScaleFactor() {
        final Rectangle2D primaryScreenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
        return Math.min(primaryScreenBounds.getWidth() / 1920, primaryScreenBounds.getHeight() / 1080);
    }

    private <V extends Screen> V setScreen(final V newScreen, final ImageView im) {
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
//        audioC.playMusic(MAINMENU_M.getPath());
        final List<Background> list = Collections.unmodifiableList(Arrays.asList(Background.values()));
        return setScreen(new MenuViewImpl(musicVol, sfxVol, language, difficulty,
                new LoadLanguage().getCurrLang(language)),
                new ImageView(new Image(list.get(new Random().nextInt(list.size())).getPath())));
    }

    @Override
    public GameWorldView showGame(final int progress) {
        if (progress < LEVELS_NUM) {
//            audioC.playMusic(Musics.get(controller.getGameData().getLevelProgress()).getPath());
        }
        return setScreen(new GameWorldViewImpl(new PlayerKeyboardInput(scene), getScaleFactor()),
                new ImageView(new Image(backgrounds.get(progress).getPath())));
    }

    @Override
    public void showError(final Text message) {
        new Alert(AlertType.ERROR, message.toString()).showAndWait();
    }

    @Override
    public CommonView<EndLevelController> showEndLevel(final Language language, final int progress) {
//        audioC.playMusic(ENDLEVEL_M.getPath());
        return setScreen(new EndLevelViewImpl(new LoadLanguage().getCurrLang(language)),
                new ImageView(new Image(backgrounds.get(progress).getPath())));
    }

    @Override
    public CommonView<GameOverController> showGameOver(final Language language) {
//        audioC.stopMusic();
        return setScreen(new GameOverViewImpl(new LoadLanguage().getCurrLang(language)),
                new ImageView(new Image("/gameover.png")));
    }

    @Override
    public CommonView<EndGameController> showEndGame(final Language language) {
//        audioC.playMusic(ENDLEVEL_M.getPath());
        return setScreen(new EndGameViewImpl(new LoadLanguage().getCurrLang(language)),
                new ImageView(new Image("/congrats.png")));
    }
}
