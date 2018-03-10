package it.unibo.oop17.ga_game.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

/**
 * Manages key presses.
 */
public class KeyboardInputController {
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private Optional<Consumer<KeyCode>> listener = Optional.empty();

    /**
     * 
     * @param scene
     *            The @Scene from which to capture key presses.
     */
    public KeyboardInputController(final Scene scene) {
        scene.setOnKeyPressed(e -> {
            pressedKeys.add(e.getCode());
            listener.ifPresent(c -> c.accept(e.getCode()));
        });
        scene.setOnKeyReleased(e -> {
            pressedKeys.remove(e.getCode());
            listener.ifPresent(c -> c.accept(e.getCode()));
        });
    }

    /**
     * Sets a listener for key events.
     * 
     * @param listener
     *            the listener
     */
    public void onEvent(final Consumer<KeyCode> listener) {
        this.listener = Optional.of(listener);
    }

    /**
     * 
     * @param key
     *            The @KeyCode
     * @return true if it's currently pressed
     */
    public boolean isPressed(final KeyCode key) {
        return pressedKeys.contains(key);
    }
}
