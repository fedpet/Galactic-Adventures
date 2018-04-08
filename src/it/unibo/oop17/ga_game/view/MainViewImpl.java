package it.unibo.oop17.ga_game.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import it.unibo.oop17.ga_game.controller.EndGameObserver;
import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import it.unibo.oop17.ga_game.controller.GameOverObserver;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public final class MainViewImpl implements MainView {
    
    private final Stage stage;
    private final Group root = new Group();
    private final Scene scene = new Scene(root);
    private final Set<Screen> currentScreens = new HashSet<>(Arrays.asList(new EmptyScreen()));
    private ImageView im;

    public MainViewImpl(final Stage stage) {
        
        this.stage = stage;
        currentScreens.forEach(s -> setScreen(s));
        this.stage.setScene(scene);
        this.stage.show();
        
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
    public void showMenu(final MenuView view) {
        final ImageView im = new ImageView(new Image(new RandomBackground().getBackgroundPath()));
        im.fitWidthProperty().bind(this.stage.widthProperty()); 
        im.fitHeightProperty().bind(this.stage.heightProperty());
        root.getChildren().addAll(im, view.getNode());
        currentScreens.add(view);
    }

    @Override
    public HudView showHud() {
        final HudView hudView = new HudViewImpl();
        root.getChildren().add(hudView.getNode());
        currentScreens.add(hudView);
        return hudView;
    }

    @Override
    public GameWorldView showGame() {
        return setScreen(new GameWorldViewImpl(new PlayerKeyboardInput(scene), getScaleFactor()));
    }

    @Override
    public void showError(final Text message) {
        // TODO: translate message
        new Alert(AlertType.ERROR, message.toString()).showAndWait();
    }

    @Override
    public void showEndLevel(final CommonView<EndLevelObserver> view) {
        this.im = new ImageView(new Image(new RandomBackground().getBackgroundPath()));
        im.fitWidthProperty().bind(this.stage.widthProperty()); 
        im.fitHeightProperty().bind(this.stage.heightProperty());
        root.getChildren().addAll(im, view.getNode());
        currentScreens.add(view);
    }

    @Override
    public void showGameOver(final CommonView<GameOverObserver> view) {
        this.im = new ImageView(new Image("/gameover.png"));
        im.fitWidthProperty().bind(this.stage.widthProperty()); 
        im.fitHeightProperty().bind(this.stage.heightProperty());
        root.getChildren().addAll(im, view.getNode());
        currentScreens.add(view);
    }

    @Override
    public void showEndGame(final CommonView<EndGameObserver> view) {
        this.im = new ImageView(new Image("/congrats.png"));
        im.fitWidthProperty().bind(this.stage.widthProperty()); 
        im.fitHeightProperty().bind(this.stage.heightProperty());
        root.getChildren().addAll(im, view.getNode());
        currentScreens.add(view);
    }
}
