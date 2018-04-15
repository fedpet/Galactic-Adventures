package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;
import it.unibo.oop17.ga_game.model.entities.events.LifeEvent;

public class DeathEventListener implements EntityEventListener {
    
private final MainController mainController;
    
    public DeathEventListener(final MainController mainController) {
        this.mainController = mainController;
    }
    
    @Subscribe
    public void gameOver(final LifeEvent e) {
        if (e.isDead()) {
            this.mainController.toGameOver();
        }
    }

}