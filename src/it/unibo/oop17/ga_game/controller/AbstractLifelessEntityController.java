package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.view.ViewUtils;
import it.unibo.oop17.ga_game.view.entities.LifelessEntityView;

public abstract class AbstractLifelessEntityController<LV extends LifelessEntityView> extends AbstractEntityController<LV> {

    public AbstractLifelessEntityController(final Entity entity, final LV entityView) {
            super(entity, entityView);
        }

    @Override
    public void update() {
        getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
    }

    @Override
    @Subscribe
    public void onEntityDestruction(final DestructionEvent destruction) {
        getEntityView().remove();
    }

}
