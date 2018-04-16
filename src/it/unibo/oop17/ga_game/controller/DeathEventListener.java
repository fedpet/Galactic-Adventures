package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;
import it.unibo.oop17.ga_game.model.entities.events.LifeEvent;

/**
 * Listens to the death event, then performs and action.
 */
public class DeathEventListener implements EntityEventListener {

private final MainController mainController;

    /**
     * @param mainController
     *          The controller to use.
     */
    public DeathEventListener(final MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Listens to the death event, then opens the game over screen.
     * @param e
     *          The event to listen.
     */
    @Subscribe
    public void gameOver(final LifeEvent e) {
        if (e.isDead()) {
            this.mainController.toGameOver();
        }
    }

}
