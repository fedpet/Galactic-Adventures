package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.view.EntityView;
import it.unibo.oop17.ga_game.view.ViewUtils;

public abstract class AbstractEntityController implements EntityController {
    private final Entity entity;
    private final EntityView entityView;

    public AbstractEntityController(final Entity entity, final EntityView entityView) {
        this.entity = entity;
        this.entityView = entityView;
        entity.register(entityView);
        entityView.setDimension(entity.getBody().getDimension());
    }

    @Override
    public void update() {
        entityView.setPosition(ViewUtils.worldPointToFX(entity.getBody().getPosition()));
    }
}
