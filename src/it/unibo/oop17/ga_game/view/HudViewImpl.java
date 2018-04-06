package it.unibo.oop17.ga_game.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class HudViewImpl implements HudView {

    private final Group hudView = new Group();
    private final StackPane stack = new StackPane();
    private final Label label = new Label("Life: " + 0 + " Coins: " + 0);

    public HudViewImpl() {
        stack.getChildren().addAll(label);
    }

    @Override
    public void addHud() {
        hudView.getChildren().addAll(stack);
    }

    @Override
    public void update(final int life, final int coins) {
        label.setText("Life: " + life + " Score: " + coins);
    }

    @Override
    public Node getNode() {
        return hudView;
    }

}
