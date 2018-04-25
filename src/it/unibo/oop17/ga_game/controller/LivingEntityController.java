package it.unibo.oop17.ga_game.controller;

import java.util.HashMap;
import java.util.Map;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.entities.components.Movement;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.LifeEvent;
import it.unibo.oop17.ga_game.model.entities.events.MovementEvent;
import it.unibo.oop17.ga_game.view.ViewUtils;
import it.unibo.oop17.ga_game.view.entities.CreatureState;
import it.unibo.oop17.ga_game.view.entities.LivingEntityView;

/**
 * Translates view input to model input and updates the view
 * for entities related to a {@link LivingEntityView} instance.
 */
public class LivingEntityController extends AbstractEntityController<LivingEntityView> {

    private final Map<Movement.State, CreatureState> stateMap = mapToCreatureState();

    /**
     * @param entity
     *            The {@link Entity} object to control.
     * @param entityView
     *            The {@link LivingEntityView} object to update.
     */
    public LivingEntityController(final Entity entity, final LivingEntityView entityView) {
        super(entity, entityView);
    }

    @Override
    public final void update() {
        if (getEntity().get(Life.class).isPresent() && getEntity().get(Life.class).get().isAlive()) {
            getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
        } else {
            getEntityView().deathUpdate();
        }
    }

    /**
     * It changes the entity state at a {@link MovementEvent} signal.
     * 
     * @param event
     *            The {@link MovementEvent} object to listen to.
     */
    @Subscribe
    public void movementChanged(final MovementEvent event) {
        getEntityView().changeState(stateMap.getOrDefault(event.getState(), CreatureState.IDLE));
    }

    /**
     * It changes the face direction at a {@link FaceDirectionEvent} signal.
     * 
     * @param event
     *            The {@link FaceDirectionEvent} object to listen to.
     */
    @Subscribe
    public void faceDirectionChanged(final FaceDirectionEvent event) {
        getEntityView().changeFaceDirection(event.getDirection());
    }

    /**
     * It updates the entity state at a {@link LifeEvent} signal.
     * 
     * @param life
     *            The {@link LifeEvent} to listen to.
     */
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
    public final void onEntityDestruction(final DestructionEvent event) {
        event.getSource().unregister(this);
        if (!(event.getSource().get(Life.class).isPresent()
                && event.getSource().get(Life.class).get().isDead())) {
            getEntityView().remove();
        }
    }

    private Map<Movement.State, CreatureState> mapToCreatureState() {
        final Map<Movement.State, CreatureState> stateMap = new HashMap<>();
        stateMap.put(Movement.State.IDLE, CreatureState.IDLE);
        stateMap.put(Movement.State.WALKING, CreatureState.WALKING);
        stateMap.put(Movement.State.JUMPING, CreatureState.JUMPING);
        stateMap.put(Movement.State.FLYING, CreatureState.FLYING);
        return stateMap;
    }

}
