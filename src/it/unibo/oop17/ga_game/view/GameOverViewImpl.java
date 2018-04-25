package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.GameOverObserver;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * The game over view.
 */
public final class GameOverViewImpl implements GameOverScreen {

    private static final int BTN_SIZE = 384;
    private final Group view = new Group();
    private GameOverObserver observer;

    /**
     * Constructor of GameOverView.
     * 
     * @param stage
     *            The related stage.
     * @param currLang
     *            The current language for text.
     * @param audioplayer
     *            The related audio player.
     */
    public GameOverViewImpl(final Stage stage, final Map<WordText, String> currLang, final AudioPlayer audioplayer) {

        final VBox menu = new VBox(8);

        final MenuButton btnRetry = new MenuButton(currLang.get(WordText.RETRY), audioplayer);
        btnRetry.setOnMouseClicked(event -> {
            observer.retry();
        });

        final MenuButton btnMenu = new MenuButton(currLang.get(WordText.MENU), audioplayer);
        btnMenu.setOnMouseClicked(event -> {
            observer.toMainMenu();
        });

        final MenuButton btnQuit = new MenuButton(currLang.get(WordText.EXIT), audioplayer);
        btnQuit.setOnMouseClicked(event -> {
            observer.quit();
        });

        menu.getChildren().addAll(btnRetry, btnMenu, btnQuit);

        final Rectangle bg0 = new Rectangle();
        bg0.setOpacity(0);

        menu.setLayoutX((stage.getWidth() - BTN_SIZE) / 2);
        menu.setLayoutY(stage.getHeight() - stage.getHeight() / 3);

        this.view.getChildren().addAll(bg0, menu);

    }

    @Override
    public void setObserver(final GameOverObserver observer) {
        this.observer = observer;
    }

    @Override
    public Node getNode() {
        return this.view;
    }

}
