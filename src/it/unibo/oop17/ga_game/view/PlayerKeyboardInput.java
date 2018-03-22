package it.unibo.oop17.ga_game.view;

import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.W;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import it.unibo.oop17.ga_game.controller.PlayerInput;
import it.unibo.oop17.ga_game.controller.PlayerInput.Listener;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

/**
 * Manages key presses.
 */
public final class PlayerKeyboardInput implements PlayerInput {
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private Optional<Listener> listener = Optional.empty();

    /**
     * 
     * @param scene
     *            The @Scene from which to capture key presses.
     */
    public PlayerKeyboardInput(final Scene scene) {
        scene.setOnKeyPressed(e -> {
            pressedKeys.add(e.getCode());
            notifyDirectionChange();
        });
        scene.setOnKeyReleased(e -> {
            pressedKeys.remove(e.getCode());
            notifyDirectionChange();
        });
    }

    @Override
    public void onInput(final Listener listener) {
        this.listener = Optional.of(listener);
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

    private void notify(final Consumer<Listener> action) {
        listener.ifPresent(action::accept);
    }
}
