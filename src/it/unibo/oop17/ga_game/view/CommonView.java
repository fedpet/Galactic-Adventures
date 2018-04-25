package it.unibo.oop17.ga_game.view;

/**
 * Interface for controlling a menu.
 * @param <T>
 *              Controller interface Type.
 */
public interface CommonView<T> extends FXView {

    /**
     * @param observer 
     *          The controller to attach
     */
    void setObserver(T observer);

}
