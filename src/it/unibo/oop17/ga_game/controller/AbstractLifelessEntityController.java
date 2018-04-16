package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.view.ViewUtils;
import it.unibo.oop17.ga_game.view.entities.LifelessEntityView;

/**
 * Translates view input to model input and updates the view for entities with @LifelessEntityView.
 *
 * @param <L>
 *            Generic LifelessEntityView type.
 */
public abstract class AbstractLifelessEntityController<L extends LifelessEntityView>
        extends AbstractEntityController<L> {

    /**
     * @param entity
     *            The @Entity to control.
     * @param entityView
     *            The @LifelessEntityView to update.
     */
    public AbstractLifelessEntityController(final Entity entity, final L entityView) {
            super(entity, entityView);
        }

    @Override
    public final void update() {
        getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
    }

    @Override
    @Subscribe
    public final void onEntityDestruction(final DestructionEvent event) {
        getEntityView().remove();
    }

}
