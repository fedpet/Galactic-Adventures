package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.view.entities.EntityView;

public abstract class AbstractEntityController<V extends EntityView> implements EntityController {

    private final Entity entity;
    private final V entityView;

    public AbstractEntityController(final Entity entity, final V entityView) {
        this.entity = entity;
        this.entityView = entityView;
        entity.register(this);
        entityView.setDimension(entity.getBody().getDimension());
    }

    @Override
    public abstract void update();

    public abstract void onEntityDestruction(final DestructionEvent destruction);

    protected final Entity getEntity() {
        return entity;
    }

    protected final V getEntityView() {
        return entityView;
    }

}
