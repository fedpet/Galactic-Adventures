package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.view.entities.EntityView;

/**
 * Base-class for @EntityController.
 * 
 * @param <V>
 *            Generic EntityView type.
 */
public abstract class AbstractEntityController<V extends EntityView> implements EntityController {

    private final Entity entity;
    private final V entityView;

    /**
     * @param entity
     *            The @Entity to control.
     * @param entityView
     *            The @EntityView to update.
     */
    public AbstractEntityController(final Entity entity, final V entityView) {
        this.entity = entity;
        this.entityView = entityView;
        entity.register(this);
        entityView.setDimension(entity.getBody().getDimension());
    }

    @Override
    public abstract void update();

    /**
     * It manages the entity destruction at a @DestructionEvent signal.
     * 
     * @param event
     *            The @DestructionEvent to listen to.
     */
    public abstract void onEntityDestruction(DestructionEvent event);

    /**
     * @return The associated @Entity.
     */
    protected final Entity getEntity() {
        return entity;
    }

    /**
     * @return The associated @EntityView.
     */
    protected final V getEntityView() {
        return entityView;
    }

}
