package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.view.entities.LivingEntityView;
import javafx.geometry.Point2D; 

/**
 * Translates view input to model input and updates the view.
 */
public final class PlayerController extends UnplayableLivingEntityController {

    /**
     * @param input
     *            The @PlayerInput
     * @param player
     *            The @Entity model
     * @param view
     *            The view
     */
    public PlayerController(final PlayerInput input, final Entity player, final LivingEntityView view) {
        super(player, view);
        input.onInput(this::move);
    }

    private void move(final Point2D direction) {
        getEntity().get(MovementComponent.class).ifPresent(movement -> movement.move(direction));
    }
}
