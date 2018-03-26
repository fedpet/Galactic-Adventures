package it.unibo.oop17.ga_game.controller;

import java.util.HashMap;
import java.util.Map;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.LifeEvent;
import it.unibo.oop17.ga_game.model.entities.events.MovementEvent;
import it.unibo.oop17.ga_game.view.ViewUtils;
import it.unibo.oop17.ga_game.view.entities.CreatureState;
import it.unibo.oop17.ga_game.view.entities.LivingEntityView;

public class LivingEntityController extends AbstractEntityController<LivingEntityView> {

    private final Map<MovementComponent.State, CreatureState> stateMap = mapToCreatureState();

    public LivingEntityController(final Entity entity, final LivingEntityView entityView) {
        super(entity, entityView);
    }

    @Override
    public void update() {
        if (getEntity().get(Life.class).isPresent() && getEntity().get(Life.class).get().isAlive()) {
            getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
        } else {
            getEntityView().deathAnimation(getEntity().getBody().getPosition());
        }
    }

    @Subscribe
    public void movementChanged(final MovementEvent event) {
        getEntityView().changeState(stateMap.getOrDefault(event.getState(), CreatureState.IDLE));
    }

    @Subscribe
    public void faceDirectionChanged(final FaceDirectionEvent event) {
        getEntityView().changeFaceDirection(event.getDirection());
    }

    @Subscribe
    public void lifeChange(final LifeEvent life) {
        if (life.isDead()) {
            getEntityView().changeState(CreatureState.DEAD);
        } else if (life.getChange() < 0) {
            // we've been damaged but still alive
            getEntityView().changeState(CreatureState.SUFFERING);
        }
    }

    @Override
    @Subscribe
    public void onEntityDestruction(final DestructionEvent destruction) {
        destruction.getSource().unregister(this);
        if (!(destruction.getSource().get(Life.class).isPresent()
                && destruction.getSource().get(Life.class).get().isDead())) {
            getEntityView().remove();
        }
    }

    private Map<MovementComponent.State, CreatureState> mapToCreatureState() {
        final Map<MovementComponent.State, CreatureState> stateMap = new HashMap<>();
        stateMap.put(MovementComponent.State.IDLE, CreatureState.IDLE);
        stateMap.put(MovementComponent.State.WALKING, CreatureState.WALKING);
        stateMap.put(MovementComponent.State.JUMPING, CreatureState.JUMPING);
        stateMap.put(MovementComponent.State.FLYING, CreatureState.FLYING);
        return stateMap;
    }

}