package it.unibo.oop17.ga_game.view;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Empty screen interface.
 */
public final class EmptyScreen implements FXView {
    private final Rectangle node = new Rectangle();

    /**
     * Constructor for EmptyScreen.
     */
    public EmptyScreen() {
        node.setFill(Color.BLACK);
    }

    @Override
    public Node getNode() {
        return node;
    }
}
