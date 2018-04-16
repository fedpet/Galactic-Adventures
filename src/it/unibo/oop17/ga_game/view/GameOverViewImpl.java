package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.GameOverController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * The game over view.
 */
public final class GameOverViewImpl implements CommonView<GameOverController> {

    private final Group view = new Group();
    private GameOverController observer;

    /**
     * Constructor of GameOverView.
     * @param currLang
     *          The current language for text.
     */
    public GameOverViewImpl(final Map<WordText, String> currLang) {

        final VBox menu = new VBox(8);

        menu.setTranslateX(96);
        menu.setTranslateY(192);

        final MenuButton btnRetry = new MenuButton(currLang.get(WordText.RETRY));
        btnRetry.setOnMouseClicked(event -> {
            observer.retry();
        });

        final MenuButton btnMenu = new MenuButton(currLang.get(WordText.MENU));
        btnMenu.setOnMouseClicked(event -> {
            observer.toMainMenu();
        });

        final MenuButton btnQuit = new MenuButton(currLang.get(WordText.EXIT));
        btnQuit.setOnMouseClicked(event -> {
            observer.quit();
        });

        menu.getChildren().addAll(btnRetry, btnMenu, btnQuit);

        final Rectangle bg0 = new Rectangle();
        bg0.setOpacity(0);

        this.view.getChildren().addAll(bg0, menu);

    }

    @Override
    public void setObserver(final GameOverController observer) {
        this.observer = observer;
    }

    @Override
    public Node getNode() {
        return this.view;
    }

}
