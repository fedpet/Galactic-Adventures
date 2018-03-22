package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.TriggerState;
import it.unibo.oop17.ga_game.model.entities.components.TriggerComponent;
import it.unibo.oop17.ga_game.model.entities.components.TriggerableComponent;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.TriggerEvent;
import it.unibo.oop17.ga_game.view.ViewUtils;
import it.unibo.oop17.ga_game.view.entities.TriggerEntityView;

public class TriggerEntityController implements EntityController {

    private final Entity entity;
    private final TriggerEntityView entityView;

    public TriggerEntityController(final Entity entity, final TriggerEntityView entityView) {
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
                entityView.changeState(TriggerState.ON);
            }
        });
        entity.get(TriggerComponent.class).ifPresent(x -> {
            if (event.getPassword().equals(x.getPassword())) {
                entityView.changeState(TriggerState.ON);
            }
        });
    }

}
