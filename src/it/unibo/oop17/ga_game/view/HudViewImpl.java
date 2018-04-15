package it.unibo.oop17.ga_game.view;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HudViewImpl implements HudView {

    private final Group hudView = new Group();
    private final StackPane stack = new StackPane();
    private final Map<WordText, String> currLang;
    private final Rectangle bg0;

    public HudViewImpl(final Map<WordText, String> map) {
        
        currLang = map;
        bg0 = new Rectangle(512, 32);
        bg0.setOpacity(0.5);
        bg0.setFill(Color.WHITE);
        bg0.setEffect(new GaussianBlur(3.5));
        update(0, 0);
        
    }

    @Override
    public void addHud() {
        hudView.getChildren().addAll(stack);
    }

    @Override
    public final void update(final int life, final int score) {
        stack.getChildren().clear();
        final Text text = new Text(currLang.get(WordText.HEALTH) + ": " +  life + "     " + currLang.get(WordText.SCORE) + ": " + score);
        text.setFont(Font.font("Verdana", 24));
        text.setFill(Color.BLACK);
        stack.getChildren().addAll(bg0, text);
    }

    @Override
    public Node getNode() {
        return hudView;
    }

}