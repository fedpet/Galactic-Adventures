package it.unibo.oop17.ga_game.controller;

import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.W;

import it.unibo.oop17.ga_game.model.entities.Player;
import javafx.geometry.Point2D;

public class PlayerController {

    public PlayerController(final KeyboardInputController keyboard, final Player player) {
        keyboard.onEvent(e -> updateMovingDirection(keyboard, player));
    }

    private void updateMovingDirection(final KeyboardInputController keyboard, final Player player) {
        Point2D movement = Point2D.ZERO;
        if (keyboard.isPressed(D)) {
            movement = movement.add(1, 0);
        }
        if (keyboard.isPressed(A)) {
            movement = movement.add(-1, 0);
        }
        if (keyboard.isPressed(W)) {
            movement = movement.add(0, 1);
        }
        player.getMovement().move(movement);
    }
}