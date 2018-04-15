package it.unibo.oop17.ga_game.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import it.unibo.oop17.ga_game.controller.AudioController;
import it.unibo.oop17.ga_game.controller.AudioObserver;
import it.unibo.oop17.ga_game.controller.EndGameObserver;
import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import it.unibo.oop17.ga_game.controller.GameOverObserver;
import it.unibo.oop17.ga_game.controller.LoadSaveManager;
import it.unibo.oop17.ga_game.controller.MainController;
import it.unibo.oop17.ga_game.model.ConfigData;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public final class MainViewImpl implements MainView {
    
    private final static Music MAINMENU_M = Music.TRACK1;
    private final static Music ENDLEVEL_M = Music.TRACK6;
    private final static int LEVELS_NUM = 7;
    
    private final Stage stage;
    private final Group root = new Group();
    private final Scene scene = new Scene(root);
    private final Set<Screen> currentScreens = new HashSet<>(Arrays.asList(new EmptyScreen()));
    private final AudioObserver audioC;
    private final List<Music> Musics = Arrays.asList(
            Music.TRACK3,
            Music.TRACK2,
            Music.TRACK4,
            Music.TRACK5,
            Music.TRACK3,
            Music.TRACK2,
            Music.TRACK4,
            Music.TRACK5);
    private final List<Background> Backgrounds = Arrays.asList(
            Background.BG_GRASSLAND,
            Background.BG_CASTLE,
            Background.BG_DESERT,
            Background.BG_GRASSLAND,
            Background.BG_DESERT,
            Background.BG_DESERT,
            Background.BG_SHROOM,
            Background.BG_CASTLE);
    private ConfigData data;

    public MainViewImpl(final Stage stage, final ConfigData data) {
        
        this.stage = stage;
        this.data = data;
        final AudioView audioV = new AudioViewImpl(data.getSFXVol(), data.getMusicVol());
        audioC = new AudioController(audioV);
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
    public MenuView showMenu(final MainController controller) {
        audioC.playMusic(MAINMENU_M.getPath());
        final List<Background> list = Collections.unmodifiableList(Arrays.asList(Background.values()));
        return setScreen(new MenuViewImpl(controller.getConfigData().getMusicVol(),
                controller.getConfigData().getSFXVol(),
                controller.getConfigData().getLanguage(),
                controller.getConfigData().getDifficulty(),
                new LoadLanguage().getCurrLang(controller.getConfigData().getLanguage())),
                new ImageView(new Image(list.get(new Random().nextInt(list.size())).getPath())));
    }

    @Override
    public HudView showHud(final MainController controller) {
        final HudView hudView = new HudViewImpl(new LoadLanguage().getCurrLang(controller.getConfigData().getLanguage()));
        root.getChildren().add(hudView.getNode());
        currentScreens.add(hudView);
        return hudView;
    }

    @Override
    public GameWorldView showGame(final MainController controller) {
        if (controller.getGameData().getLevelProgress()< LEVELS_NUM) {
            audioC.playMusic(Musics.get(controller.getGameData().getLevelProgress()).getPath());
        }
        return setScreen(new GameWorldViewImpl(new PlayerKeyboardInput(scene), getScaleFactor()),
                new ImageView(new Image(Backgrounds.get(controller.getGameData().getLevelProgress()).getPath())));
    }

    @Override
    public void showError(final Text message) {
        new Alert(AlertType.ERROR, message.toString()).showAndWait();
    }

    @Override
    public CommonView<EndLevelObserver> showEndLevel(final MainController controller) {
        audioC.playMusic(ENDLEVEL_M.getPath());
        return setScreen(new EndLevelViewImpl(new LoadLanguage().getCurrLang(controller.getConfigData().getLanguage()),
                controller.getStage()),
                new ImageView(new Image(Backgrounds.get(controller.getGameData().getLevelProgress()).getPath())));
    }

    @Override
    public CommonView<GameOverObserver> showGameOver(final MainController controller) {
        audioC.stopMusic();
        return setScreen(new GameOverViewImpl(new LoadLanguage().getCurrLang(controller.getConfigData().getLanguage()),
                controller.getStage()), new ImageView(new Image("/gameover.png")));
    }

    @Override
    public CommonView<EndGameObserver> showEndGame(final MainController controller) {
        audioC.playMusic(ENDLEVEL_M.getPath());
        return setScreen(new EndGameViewImpl(new LoadLanguage().getCurrLang(controller.getConfigData().getLanguage()),
                controller.getStage()), new ImageView(new Image("/congrats.png")));
    }

    @Override
    public void updateMusicVol() {
        data = (ConfigData)LoadSaveManager.load("configdata.dat");
        audioC.updateMusicVol(data.getMusicVol());
    }
}
