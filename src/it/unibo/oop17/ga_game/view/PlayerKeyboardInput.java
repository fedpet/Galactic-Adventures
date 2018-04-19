package it.unibo.oop17.ga_game.view;

import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.W;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import it.unibo.oop17.ga_game.controller.PlayerInputListener;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Manages key presses.
 */
public final class PlayerKeyboardInput {
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private Optional<PlayerInputListener> listener = Optional.empty();

    /**
     * @param scene
     *            The @Scene from which to capture key presses.
     */
    public PlayerKeyboardInput(final Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::onKeyEvent);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, this::onKeyEvent);
    }

    /**
     * Sets a @PlayerInputListener.
     * 
     * @param listener
     *            The listener
     */
    public void setListener(final PlayerInputListener listener) {
        this.listener = Optional.of(listener);
    }

    /**
     * Clear the listener.
     */
    public void clearListener() {
        this.listener = Optional.empty();
    }

    private void onKeyEvent(final KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            pressedKeys.add(event.getCode());
        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            pressedKeys.remove(event.getCode());
        }
        notifyDirectionChange();
    }

    private void notifyDirectionChange() {
        notify(listener -> listener.move(computeDirection()));
    }

    private Point2D computeDirection() {
        Point2D direction = Point2D.ZERO;
        if (pressedKeys.contains(D)) {
            direction = direction.add(1, 0);
        }
        if (pressedKeys.contains(A)) {
            direction = direction.add(-1, 0);
        }
        if (pressedKeys.contains(W)) {
            direction = direction.add(0, 1);
        }
        return direction;
    }

    private void notify(final Consumer<PlayerInputListener> action) {
        listener.ifPresent(action::accept);
    }
}
