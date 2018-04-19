package it.unibo.oop17.ga_game.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.oop17.ga_game.controller.EndGameController;
import it.unibo.oop17.ga_game.model.KeyLockType;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * The end game view.
 */
public final class EndGameViewImpl implements EndGameScreen {

    private static final int BTN_SIZE = 384;
    private static final int FONT_SIZE = 72;
    private static final int OFFSET = 64;
    private static final int BOX_W = 768;
    private static final int BOX_H = 220;
    private static final double EFFECT_C = 3.5;
    private final Group view = new Group();
    private EndGameController observer;
    private static final List<Image> IMG_DIGIT;
    static {
        IMG_DIGIT = IntStream.range(0, 10)
                .mapToObj(n -> "/hud/hud_" + n + ".png")
                .map(Image::new)
                .collect(Collectors.toList());
    }

    /**
     * Constructor of EndGameView.
     * @param stage
     *          The stage.
     * @param currLang
     *          Current language for text.
     * @param audioplayer
     *          The audio player.
     */
    public EndGameViewImpl(final Stage stage, final Map<WordText, String> currLang, final AudioPlayer audioplayer,
            final int score) {

        final VBox menu = new VBox(8);
        final StackPane pane = new StackPane();

        final MenuButton btnMenu = new MenuButton(currLang.get(WordText.MENU), audioplayer);
        btnMenu.setOnMouseClicked(event -> {
            observer.toMainMenu();
        });

        final MenuButton btnQuit = new MenuButton(currLang.get(WordText.EXIT), audioplayer);
        btnQuit.setOnMouseClicked(event -> {
            observer.quit();
        });

        menu.getChildren().addAll(btnMenu, btnQuit);

        final Rectangle bg0 = new Rectangle();
        bg0.setOpacity(0);

        final Text text = new Text(currLang.get(WordText.FINAL_SCORE) + ":\n" + score);
        text.setFont(Font.font(FONT_SIZE));
        text.setFill(Color.BLACK);

        final Rectangle bg1 = new Rectangle(BOX_W, BOX_H);
        bg1.setOpacity(0.5);
        bg1.setFill(Color.WHITE);
        bg1.setEffect(new GaussianBlur(EFFECT_C));

        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(bg1, text);

        pane.setLayoutX((stage.getWidth() - BOX_W) / 2);
        pane.setLayoutY(OFFSET);

        menu.setLayoutX((stage.getWidth() - BTN_SIZE) / 2);
        menu.setLayoutY(stage.getHeight() - stage.getHeight() / 3);

        view.getChildren().addAll(pane, menu);

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
