package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.MovementEvent;
import it.unibo.oop17.ga_game.view.ViewUtils;
import it.unibo.oop17.ga_game.view.entities.LivingEntityView;

public class LivingEntityController implements EntityController {

    private static final double DEATH_TIME = 60;
    private final Entity entity;
    private final LivingEntityView entityView;
    private int deathTimeCount;

    public LivingEntityController(final Entity entity, final LivingEntityView entityView) {
        this.entity = entity;
        this.entityView = entityView;
        entity.register(this);
        entityView.setDimension(entity.getBody().getDimension());
    }

    @Override
    public void update() {
        if (!entity.get(Life.class).isPresent() || entity.get(Life.class).get().isAlive()) {
            entityView.setPosition(ViewUtils.worldPointToFX(entity.getBody().getPosition()));
        } else {
            entityView.setPosition(
                    ViewUtils.worldPointToFX(entityView.updatePointFromDeath(entity.getBody().getPosition())));
            updateDeathMoment();
        }
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
        if (destruction.getSource().get(Life.class).isPresent()
                && destruction.getSource().get(Life.class).get().isDead()) {
            entityView.deathAnimation();
        } else {
            entityView.remove();
        }
    }

    protected final Entity getEntity() {
        return entity;
    }

    private void updateDeathMoment() {
        deathTimeCount++;
        if (deathTimeCount == DEATH_TIME) {
            entityView.remove();
        }
    }


}
