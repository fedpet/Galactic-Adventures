package it.unibo.oop17.ga_game.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import it.unibo.oop17.ga_game.controller.CheckData;
import it.unibo.oop17.ga_game.controller.CheckSave;
import it.unibo.oop17.ga_game.controller.MenuController;
import it.unibo.oop17.ga_game.controller.MenuObserver;
import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
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
    private final ConfigData data;
    private final GameData save;

    public MainViewImpl(final Stage stage) {
        
        this.stage = stage;
        this.data = CheckData.loadConfig();
        this.save = CheckSave.loadSave();
        currentScreens.forEach(s -> setScreen(s));
        this.stage.setScene(scene);
        this.stage.show();
    }
    
    @Override
    public void showMenu() {
        final MenuObserver menuController = new MenuController(this.data, this.save);
        final ImageView im = new ImageView(new Image(new RandomBackground().getBackgroundPath()));
        im.fitWidthProperty().bind(this.stage.widthProperty()); 
        im.fitHeightProperty().bind(this.stage.heightProperty());
        root.getChildren().addAll(im, menuController.getView().getNode());
        currentScreens.add(menuController.getView());
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


    private <V extends Screen> V setScreen(final V newScreen) {
        currentScreens.forEach(s -> root.getChildren().remove(s.getNode()));
        currentScreens.clear();
        currentScreens.add(newScreen);
        currentScreens.forEach(s -> root.getChildren().add(s.getNode()));
        return newScreen;
    }

    @Override
    public void showError(final Text message) {
        // TODO: translate message
        new Alert(AlertType.ERROR, message.toString()).showAndWait();
    }

    private double getScaleFactor() {
        final Rectangle2D primaryScreenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
        return Math.min(primaryScreenBounds.getWidth() / 1920, primaryScreenBounds.getHeight() / 1080);
    }
}
