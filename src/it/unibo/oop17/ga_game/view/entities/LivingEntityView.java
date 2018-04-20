package it.unibo.oop17.ga_game.view.entities;

import javafx.geometry.HorizontalDirection;

/**
 * Models a living entity view.
 */
public interface LivingEntityView extends StateChangingEntityView<CreatureState> {

    /**
     * Used to change the entity view direction.
     * 
     * @param direction
     *            The direction to set for the entity view.
     */
    void changeFaceDirection(HorizontalDirection direction);

    /**
     * Starts the death animation for the entity.
     */
    void deathUpdate();

}
