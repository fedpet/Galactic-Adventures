package it.unibo.oop17.ga_game.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public final class MainViewImpl implements MainView {
    private static final double GAME_SCALE = 1;
    private final Group root = new Group();
    private final Scene scene = new Scene(root);
    private Screen currentScreen = new EmptyScreen();

    public MainViewImpl(final Stage stage) {
        setScreen(currentScreen);
        stage.setScene(scene);
        stage.show();
    }
    
//    @Override
//    public MainMenuView showMenu() {
//    }

    @Override
    public GameWorldView showGame() {
        return setScreen(new GameWorldViewImpl(new PlayerKeyboardInput(scene), GAME_SCALE));
    }


    private <V extends Screen> V setScreen(final V newScreen) {
        root.getChildren().remove(currentScreen.getNode());
        currentScreen = newScreen;
        root.getChildren().add(currentScreen.getNode());
        return newScreen;
    }

    @Override
    public void showError(final Text message) {
        // TODO: translate message
        new Alert(AlertType.ERROR, message.toString()).showAndWait();
    }
}
