package it.unibo.oop17.ga_game.view;

import javafx.scene.Node;

/**
 * Interface for controlling a menu.
 * @param <T>
 *              Controller interface Type.
 */
public interface CommonView<T> extends Screen {

    /**
     * @param observer 
     *          The controller to attach
     */
    void setObserver(T observer);

    /**
     * @return node.
     */
    Node getNode();

}
