package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.Brain;
import it.unibo.oop17.ga_game.model.entities.components.EntityPersonality;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.MovementEvent;
import it.unibo.oop17.ga_game.view.ViewUtils;
import it.unibo.oop17.ga_game.view.entities.EntityView;
import javafx.geometry.Point2D;
import javafx.geometry.VerticalDirection;

public abstract class AbstractEntityController implements EntityController {
    private static final double DEATH_FALLING_SPEED = 0.05;
    private final Entity entity;
    private final EntityView entityView;
    private Point2D pointFromDeath;

    public AbstractEntityController(final Entity entity, final EntityView entityView) {
        this.entity = entity;
        this.entityView = entityView;
        entity.register(this);
        entityView.setDimension(entity.getBody().getDimension());
    }

    @Override
    public void update() {
        if (entity.get(Life.class).isPresent() && entity.get(Life.class).get().isAlive()) {
            entityView.setPosition(ViewUtils.worldPointToFX(entity.getBody().getPosition()));
        } else if (entity.get(Brain.class).isPresent()
                && entity.get(Brain.class).get().getPersonality() != EntityPersonality.NONE) {
            entityView.setPosition(ViewUtils.worldPointToFX(updatePointFromDeath()));
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
        if (destruction.getSource().get(Brain.class).isPresent()
                && destruction.getSource().get(Brain.class).get().getPersonality() == EntityPersonality.NONE) {
            entityView.remove();
        } else {
            entityView.flip(VerticalDirection.DOWN);
            entityView.changeMovement(MovementComponent.State.IDLE);
        }
    }

    private Point2D updatePointFromDeath() {
        if (pointFromDeath == null) {
            pointFromDeath = entity.getBody().getPosition();
        }
        pointFromDeath = pointFromDeath.subtract(new Point2D(0, DEATH_FALLING_SPEED));
        return pointFromDeath;
    }

}
