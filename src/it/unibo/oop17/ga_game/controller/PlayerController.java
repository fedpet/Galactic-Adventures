package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.Command;
import it.unibo.oop17.ga_game.model.Player;
import javafx.scene.Scene;

public class PlayerController {
    private boolean movingLeft;
    private boolean movingRight;

    public PlayerController(final Scene scene, final Player player) {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case W:
                player.getBrain().execute(Command.JUMP);
                break;
            case A:
                movingLeft = true;
                break;
            case D:
                movingRight = true;
                break;
            default:
                break;
            }
            updateMovingDirection(player);
        });
        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
            case A:
                movingLeft = false;
                break;
            case D:
                movingRight = false;
                break;
            default:
                break;
            }
            updateMovingDirection(player);
        });
    }

    private void updateMovingDirection(final Player player) {
        if (movingLeft && movingRight || !movingLeft && !movingRight) {
            player.getBrain().execute(Command.STOP_MOVING);
        } else if (movingRight) {
            player.getBrain().execute(Command.MOVE_RIGHT);
        } else if (movingLeft) {
            player.getBrain().execute(Command.MOVE_LEFT);
        }
    }
}
