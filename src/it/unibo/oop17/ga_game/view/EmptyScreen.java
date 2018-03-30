package it.unibo.oop17.ga_game.view;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EmptyScreen implements Screen {
    private final Rectangle node = new Rectangle();

    public EmptyScreen() {
        node.setFill(Color.BLACK);
    }

    @Override
    public Node getNode() {
        return node;
    }
}
