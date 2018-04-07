package it.unibo.oop17.ga_game.view;

import javafx.scene.Node;

public interface CommonView<T> extends Screen {

    /**
     * @param observer the controller to attach
     */
    void setObserver(T observer);
   
    /**
     * @return node.
     */
    Node getNode();
    
}
