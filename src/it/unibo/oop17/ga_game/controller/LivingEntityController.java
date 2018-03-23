package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.MovementEvent;
import it.unibo.oop17.ga_game.view.ViewUtils;
import it.unibo.oop17.ga_game.view.entities.LivingEntityView;

public class LivingEntityController extends AbstractEntityController<LivingEntityView> {

    private static final double DEATH_TIME = 60;
    private int deathTimeCount;

    public LivingEntityController(final Entity entity, final LivingEntityView entityView) {
        super(entity, entityView);
    }

    @Override
    public void update() {
        if (getEntity().get(Life.class).isPresent() && getEntity().get(Life.class).get().isAlive()) {
            getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
        } else {
            getEntityView().setPosition(
                    ViewUtils
                            .worldPointToFX(getEntityView().updatePointFromDeath(getEntity().getBody().getPosition())));
            updateDeathMoment();
        }
    }

    @Subscribe
    public void movementChanged(final MovementEvent event) {
        getEntityView().changeState(event.getState());
    }

    @Subscribe
    public void faceDirectionChanged(final FaceDirectionEvent event) {
        getEntityView().changeFaceDirection(event.getDirection());
    }

    @Override
    @Subscribe
    public void onEntityDestruction(final DestructionEvent destruction) {
        destruction.getSource().unregister(this);
        if (destruction.getSource().get(Life.class).isPresent()
                && destruction.getSource().get(Life.class).get().isDead()) {
            getEntityView().deathAnimation();
        } else {
            getEntityView().remove();
        }
    }

    private void updateDeathMoment() {
        deathTimeCount++;
        if (deathTimeCount == DEATH_TIME) {
            getEntityView().remove();
        }
    }


}
