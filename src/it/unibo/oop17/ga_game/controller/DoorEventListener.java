package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.EntityEventSubscriber;
import it.unibo.oop17.ga_game.model.entities.events.FinishedLevelEvent;

/**
 * Listens to the finished level event, then performs and action.
 */
class DoorEventListener implements EntityEventSubscriber {

    private final MainController mainController;

    /**
     * @param mainController
     *          The controller to use.
     */
    DoorEventListener(final MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Listens to the finished level event, then opens the end level screen.
     * @param e
     *          The event to listen.
     */
    @Subscribe
    public void endLevel(final FinishedLevelEvent e) {
        this.mainController.goToEndLevel();
    }

}
