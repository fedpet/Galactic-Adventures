package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.MovementEvent;
import it.unibo.oop17.ga_game.view.EntityView;
import it.unibo.oop17.ga_game.view.ViewUtils;

public abstract class AbstractEntityController implements EntityController {
    private final Entity entity;
    private final EntityView entityView;

    public AbstractEntityController(final Entity entity, final EntityView entityView) {
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
    public void movementChanged(final MovementEvent event) {
        entityView.changeMovement(event.getState());
    }

    @Subscribe
    public void faceDirectionChanged(final FaceDirectionEvent event) {
        entityView.changeFaceDirection(event.getDirection());
    }

    @Subscribe
    public void onEntityDestruction(final DestructionEvent destruction) {
        destruction.getSource().unregister(this);
        entityView.remove();
    }
}
