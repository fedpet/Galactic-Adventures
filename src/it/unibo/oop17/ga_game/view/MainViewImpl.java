package it.unibo.oop17.ga_game.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import it.unibo.oop17.ga_game.controller.AudioController;
import it.unibo.oop17.ga_game.controller.AudioObserver;
import it.unibo.oop17.ga_game.controller.EndGameObserver;
import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import it.unibo.oop17.ga_game.controller.GameOverObserver;
import it.unibo.oop17.ga_game.controller.LoadSaveManager;
import it.unibo.oop17.ga_game.controller.MainController;
import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.model.Level;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public final class MainViewImpl implements MainView {
    
    private final static Music MAINMENU_M = Music.TRACK1;
    private final static Music ENDLEVEL_M = Music.TRACK6;
    
    private final Group root = new Group();
    private final Scene scene = new Scene(root);
    private final Set<Screen> currentScreens = new HashSet<>(Arrays.asList(new EmptyScreen()));
    private final AudioObserver audioC;
    private ConfigData data;

    public MainViewImpl(final Stage stage, final ConfigData data) {
        
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
        currentScreens.forEach(s -> root.getChildren().remove(s.getNode()));
        currentScreens.clear();
        root.getChildren().add(im);
        currentScreens.add(newScreen);
        currentScreens.forEach(s -> root.getChildren().add(s.getNode()));
        return newScreen;
    }
    
//    private void setBackground(final ImageView im) {
//        root.getChildren().add(im);
//    }
    
    @Override
    public MenuView showMenu(final MainController controller) {
        audioC.playMusic(MAINMENU_M.getPath());
        return setScreen(new MenuViewImpl(controller.getConfigData().getMusicVol(),
                controller.getConfigData().getSFXVol(),
                controller.getConfigData().getLanguage(),
                controller.getConfigData().getDifficulty(),
                new LoadLanguage().getCurrLang(controller.getConfigData().getLanguage())),
                new ImageView(new Image(new RandomBackground().getBackgroundPath())));
    }

    @Override
    public HudView showHud() {
        final HudView hudView = new HudViewImpl();
        root.getChildren().add(hudView.getNode());
        currentScreens.add(hudView);
        return hudView;
    }

    @Override
    public GameWorldView showGame(final GameData save) {
        if (save.getLevelProgress() < Level.values().length) {
            audioC.playMusic(Level.values()[save.getLevelProgress()].getMusic());
        }
        return setScreen(new GameWorldViewImpl(new PlayerKeyboardInput(scene), getScaleFactor()),
                new ImageView(new Image(new RandomBackground().getBackgroundPath())));
    }

    @Override
    public void showError(final Text message) {
        new Alert(AlertType.ERROR, message.toString()).showAndWait();
    }

    @Override
    public CommonView<EndLevelObserver> showEndLevel(final MainController controller) {
        audioC.playMusic(ENDLEVEL_M.getPath());
        return setScreen(new EndLevelViewImpl(new LoadLanguage().getCurrLang(controller.getConfigData().getLanguage()),
                controller.getStage()), new ImageView(new Image(new RandomBackground().getBackgroundPath())));
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
