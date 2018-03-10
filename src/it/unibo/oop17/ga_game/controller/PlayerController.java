package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.Player;
import javafx.geometry.HorizontalDirection;
import javafx.scene.Scene;

public class PlayerController {
    private boolean movingLeft;
    private boolean movingRight;

    public PlayerController(final Scene scene, final Player player) {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case W:
                player.jump();
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
            player.stopWalking();
        } else if (movingRight) {
            player.move(HorizontalDirection.RIGHT);
        } else if (movingLeft) {
            player.move(HorizontalDirection.LEFT);
        }
    }
}