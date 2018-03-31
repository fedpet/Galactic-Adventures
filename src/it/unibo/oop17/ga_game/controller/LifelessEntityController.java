package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.view.entities.LifelessEntityView;

/**
 * Translates view input to model input and updates the view for generic entities with @LifelessEntityView.
 */
public class LifelessEntityController extends AbstractLifelessEntityController<LifelessEntityView> {

    /**
     * @param entity
     *            The @Entity to control.
     * @param entityView
     *            The @LifelessEntityView to update.
     */
    public LifelessEntityController(final Entity entity, final LifelessEntityView entityView) {
        super(entity, entityView);
    }

}
