package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.Inventory;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.entities.components.Movement;
import it.unibo.oop17.ga_game.model.entities.events.InventoryChangedEvent;
import it.unibo.oop17.ga_game.model.entities.events.LifeEvent;
import it.unibo.oop17.ga_game.view.entities.PlayerView;
import javafx.geometry.Point2D; 

/**
 * Translates view input to model input and updates the view.
 */
public final class PlayerController extends LivingEntityController {
    private final PlayerView view;

    /**
     * @param input
     *            The @PlayerInput
     * @param player
     *            The @Entity model
     * @param playerView
     *            The view
     */
    public PlayerController(final PlayerInput input, final Entity player, final PlayerView playerView) {
        super(player, playerView);
        view = playerView;
        input.onInput(this::move);
        updateViewLife();
        updateViewInventory();
    }

    /**
     * Subscribes to a @InventoryChangedEvent.
     * 
     * @param event
     *            The InventoryChangedEvent.
     */
    @Subscribe
    public void inventoryChanged(final InventoryChangedEvent event) {
        updateViewInventory();
    }

    @Override
    public void lifeChange(final LifeEvent life) {
        super.lifeChange(life);
        updateViewLife();
    }

    private void move(final Point2D direction) {
        getEntity().get(Movement.class).ifPresent(movement -> movement.move(direction));
    }

    private void updateViewInventory() {
        getEntity().get(Inventory.class).ifPresent(inventory -> {
            view.setKeys(inventory.getKeysBunch());
            view.setMoney(inventory.getMoney());
        });
    }

    private void updateViewLife() {
        getEntity().get(Life.class).ifPresent(life -> {
            view.setMaxHealth(life.getMaxHealthPoints());
            view.setCurrentHealth(life.getHealthPoints());
        });
    }
}
