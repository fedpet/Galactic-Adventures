package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import it.unibo.oop17.ga_game.model.EntityStatistic;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The end level view.
 */
public final class EndLevelViewImpl implements EndLevelScreen {

    private static final int BTN_SIZE = 384;
    private static final int FONT_SIZE = 48;
    private static final int OFFSET = 64;
    private static final int BOX_W = 768;
    private static final int BOX_H = 372;
    private static final double EFFECT_C = 3.5;
    private final Group view = new Group();
    private EndLevelObserver observer;

    /**
     * Constructor of EndLevelView.
     * @param stage
     *          The stage.
     * @param currLang
     *          Current language for text.
     * @param audioplayer
     *          The audio player.
     * @param statistic
     *          The player statistics.
     * @param score
     *          The player score.
     */
    public EndLevelViewImpl(final Stage stage, final Map<WordText, String> currLang, final AudioPlayer audioplayer,
            final EntityStatistic statistic, final int score) {

        final VBox menu = new VBox(8);
        final StackPane pane = new StackPane();

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

        final Text text = new Text(currLang.get(WordText.COINS) + ": " + statistic.getMoneyCollected() + "\n\n"
                + currLang.get(WordText.KILLS) + ": " + statistic.getEnemiesKilled() + "\n"
                + currLang.get(WordText.SCORE) + ": " + score + "\n");
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
    public void setObserver(final EndLevelObserver observer) {
        this.observer = observer;
    }

    @Override
    public Node getNode() {
        return this.view;
    }

}
