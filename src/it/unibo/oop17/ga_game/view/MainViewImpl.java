package it.unibo.oop17.ga_game.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import it.unibo.oop17.ga_game.controller.EndGameObserver;
import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import it.unibo.oop17.ga_game.controller.GameOverObserver;
import it.unibo.oop17.ga_game.controller.MainController;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.model.Level;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public final class MainViewImpl implements MainView {
    
    private final Group root = new Group();
    private final Scene scene = new Scene(root);
    private final Set<Screen> currentScreens = new HashSet<>(Arrays.asList(new EmptyScreen()));
    private ImageView im;
    private MediaPlayer mediaPlayer;

    public MainViewImpl(final Stage stage, final GameData save) {
        
        this.mediaPlayer = new MediaPlayer(new Media(Level.values()[save.getLevelProgress()].getMusic()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        currentScreens.forEach(s -> setScreen(s));
        stage.setScene(scene);
        stage.show();
        
    }
    
    private double getScaleFactor() {
        final Rectangle2D primaryScreenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
        return Math.min(primaryScreenBounds.getWidth() / 1920, primaryScreenBounds.getHeight() / 1080);
    }

    private <V extends Screen> V setScreen(final V newScreen) {
        currentScreens.forEach(s -> root.getChildren().remove(s.getNode()));
        currentScreens.clear();
        currentScreens.add(newScreen);
        currentScreens.forEach(s -> root.getChildren().add(s.getNode()));
        return newScreen;
    }
    
    @Override
    public MenuView showMenu(final MainController controller) {
        this.mediaPlayer.stop();
        this.im = new ImageView(new Image(new RandomBackground().getBackgroundPath()));
        return setScreen(new MenuViewImpl(controller.getConfigData().getMusicVol(),
                controller.getConfigData().getSFXVol(),
                controller.getConfigData().getLanguage(),
                controller.getConfigData().getDifficulty(),
                new LoadLanguage().getCurrLang(controller.getConfigData().getLanguage())));
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
        this.mediaPlayer.stop();
        this.mediaPlayer = new MediaPlayer(new Media(Level.values()[save.getLevelProgress()].getMusic()));
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        return setScreen(new GameWorldViewImpl(new PlayerKeyboardInput(scene), getScaleFactor()));
    }

    @Override
    public void showError(final Text message) {
        // TODO: translate message
        new Alert(AlertType.ERROR, message.toString()).showAndWait();
    }

    @Override
    public CommonView<EndLevelObserver> showEndLevel(final MainController controller) {
        this.mediaPlayer.stop();
        this.mediaPlayer = new MediaPlayer(new Media(Music.TRACK6.getMusic()));
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        this.im = new ImageView(new Image(new RandomBackground().getBackgroundPath()));
        return setScreen(new EndLevelViewImpl(controller.getConfigData().getSFXVol(),
                new LoadLanguage().getCurrLang(controller.getConfigData().getLanguage()),
                controller.getStage()));
    }

    @Override
    public CommonView<GameOverObserver> showGameOver(final MainController controller) {
        this.mediaPlayer.stop();
        this.im = new ImageView(new Image("/gameover.png"));
        return setScreen(new GameOverViewImpl(controller.getConfigData().getSFXVol(),
                new LoadLanguage().getCurrLang(controller.getConfigData().getLanguage()),
                controller.getStage()));
    }

    @Override
    public CommonView<EndGameObserver> showEndGame(final MainController controller) {
        this.mediaPlayer.stop();
        this.im = new ImageView(new Image("/congrats.png"));
        return setScreen(new EndGameViewImpl(controller.getConfigData().getSFXVol(),
                new LoadLanguage().getCurrLang(controller.getConfigData().getLanguage()),
                controller.getStage()));
    }
}
