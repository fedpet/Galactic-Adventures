package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.DeadState;
import it.unibo.oop17.ga_game.model.entities.components.TriggerComponent;
import it.unibo.oop17.ga_game.model.entities.components.TriggerableComponent;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.TriggerEvent;
import it.unibo.oop17.ga_game.view.ViewUtils;
import it.unibo.oop17.ga_game.view.entities.DeadEntityView;

public class DeadEntityController implements EntityController {

    private final Entity entity;
    private final DeadEntityView entityView;

    public DeadEntityController(final Entity entity, final DeadEntityView entityView) {
        this.entity = entity;
        this.entityView = entityView;
        entity.register(this);
        entityView.setDimension(entity.getBody().getDimension());
    }

    @Override
    public void update() {
        entityView.setPosition(ViewUtils.worldPointToFX(entity.getBody().getPosition()));
    }

    @Subscribe
    public void faceDirectionChanged(final FaceDirectionEvent event) {
        entityView.changeFaceDirection(event.getDirection());
    }

    @Subscribe
    public void onEntityDestruction(final DestructionEvent destruction) {
        entityView.remove();
    }

    @Subscribe
    public void triggered(final TriggerEvent event) {
        entity.get(TriggerableComponent.class).ifPresent(x -> {
            if (event.getPassword().equals(x.getPassword())) {
                entityView.changeState(DeadState.ON);
            }
        });
        entity.get(TriggerComponent.class).ifPresent(x -> {
            if (event.getPassword().equals(x.getPassword())) {
                entityView.changeState(DeadState.ON);
            }
        });
    }

}
