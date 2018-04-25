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
 * Models an {@link EntityController} object for the player.
 */
public final class PlayerController extends LivingEntityController implements PlayerInputListener {
    private final PlayerView view;

    /**
     * @param player
     *            The {@link Entity} object to control.
     * @param playerView
     *            The {@link PlayerView} object to update.
     */
    public PlayerController(final Entity player, final PlayerView playerView) {
        super(player, playerView);
        view = playerView;
        updateViewLife();
        updateViewInventory();
    }

    /**
     * Subscribes to a {@link InventoryChangedEvent} signal.
     * 
     * @param event
     *            The InventoryChangedEvent instance to listen.
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

    @Override
    public void move(final Point2D direction) {
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
