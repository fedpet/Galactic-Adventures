package it.unibo.oop17.ga_game.controller;

import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.W;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.view.entities.PlayerView;
import javafx.geometry.Point2D;

public class PlayerController extends UnplayableLivingEntityController {

    public PlayerController(final KeyboardInputController keyboard, final Entity player, final PlayerView view) {
        super(player, view);
        keyboard.onEvent(e -> updateMovingDirection(keyboard, player));
    }

    private void updateMovingDirection(final KeyboardInputController keyboard, final Entity player) {
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
        move(player, movement);
    }

    private void move(final Entity player, final Point2D direction) {
        player.get(MovementComponent.class).ifPresent(move -> move.move(direction));
    }
}