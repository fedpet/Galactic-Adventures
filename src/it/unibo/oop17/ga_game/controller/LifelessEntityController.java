package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.view.entities.LifelessEntityView;

/**
 * Base-class for {@link AbstractLifelessEntityController}.
 */
public class LifelessEntityController extends AbstractLifelessEntityController<LifelessEntityView> {

    /**
     * @param entity
     *            The {@link Entity} object to control.
     * @param entityView
     *            The {@link LifelessEntityView} object to update.
     */
    public LifelessEntityController(final Entity entity, final LifelessEntityView entityView) {
        super(entity, entityView);
    }

}
