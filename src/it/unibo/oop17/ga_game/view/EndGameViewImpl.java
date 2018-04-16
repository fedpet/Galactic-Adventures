package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.EndGameController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * The end game view.
 */
public final class EndGameViewImpl implements CommonView<EndGameController> {

    private final Group view = new Group();
    private EndGameController observer;

    /**
     * Constructor of EndGameView.
     * @param currLang
     *          Current language for text.
     */
    public EndGameViewImpl(final Map<WordText, String> currLang) {

        final VBox menu = new VBox(8);

        menu.setTranslateX(96);
        menu.setTranslateY(192);

        final MenuButton btnMenu = new MenuButton(currLang.get(WordText.MENU));
        btnMenu.setOnMouseClicked(event -> {
            observer.toMainMenu();
        });

        final MenuButton btnQuit = new MenuButton(currLang.get(WordText.EXIT));
        btnQuit.setOnMouseClicked(event -> {
            observer.quit();
        });

        menu.getChildren().addAll(btnMenu, btnQuit);

        final Rectangle bg0 = new Rectangle();
        bg0.setOpacity(0);

        this.view.getChildren().addAll(bg0, menu);

    }

    @Override
    public void setObserver(final EndGameController observer) {
        this.observer = observer;
    }

    @Override
    public Node getNode() {
        return this.view;
    }

}
