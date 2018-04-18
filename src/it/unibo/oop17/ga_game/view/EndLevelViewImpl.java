package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.EndLevelController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * The end level view.
 */
public final class EndLevelViewImpl implements EndLevelScreen {

    private static final int BTN_SIZE = 384;
    private final Group view = new Group();
    private EndLevelController observer;

    /**
     * Constructor of EndLevelView.
     * @param stage
     *          The stage.
     * @param currLang
     *          Current language for text.
     * @param audioplayer
     *          The audio player.
     */
    public EndLevelViewImpl(final Stage stage, final Map<WordText, String> currLang, final AudioPlayer audioplayer) {

        final VBox menu = new VBox(8);

        final MenuButton btnContinue = new MenuButton(currLang.get(WordText.CONTINUE), audioplayer);
        btnContinue.setOnMouseClicked(event -> {
            observer.toNextMap();
        });

        final MenuButton btnMenu = new MenuButton(currLang.get(WordText.MENU), audioplayer);
        btnMenu.setOnMouseClicked(event -> {
            observer.toMainMenu();
        });

        menu.getChildren().addAll(btnContinue, btnMenu);

        final Rectangle bg0 = new Rectangle();
        bg0.setOpacity(0);

        menu.setLayoutX((stage.getWidth() - BTN_SIZE) / 2);
        menu.setLayoutY(stage.getHeight() - stage.getHeight() / 3);

        this.view.getChildren().addAll(bg0, menu);

    }

    @Override
    public void setObserver(final EndLevelController observer) {
        this.observer = observer;
    }

    @Override
    public Node getNode() {
        return this.view;
    }

}
