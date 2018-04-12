package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;
import java.util.function.Predicate;

import it.unibo.oop17.ga_game.model.Timer;
import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * Deals damage to entities in contact with the owner.
 */
public final class MeleeWeapon extends AbstractEntityComponent implements Weapon {
    private static final double COOLDOWN_TIME = 0.5; // cooldown in seconds to avoid multiple hits in too short time.
    private final Predicate<Side> canUseToward;
    private final int damage;
    private final double selfKnockback, otherKnockback;
    private final Timer cooldown = Timer.seconds(COOLDOWN_TIME);

    /**
     * @param damage
     *            The damage in health points.
     * @param selfKnockback
     *            The knockback force applied to the user.
     * @param otherKnockback
     *            The knockback force applied to the entity hit.
     * @param canUseToward
     *            A strategy to evaluate if a @Side relative to the owner's body can be hit.
     */
    public MeleeWeapon(final int damage, final double selfKnockback, final double otherKnockback,
            final Predicate<Side> canUseToward) {
        super();
        this.damage = damage;
        this.selfKnockback = selfKnockback;
        this.otherKnockback = otherKnockback;
        this.canUseToward = canUseToward;
        cooldown.update(COOLDOWN_TIME); // so it's elapsed.
    }

    @Override
    public void update(final double dt) {
        super.update(dt);
        cooldown.update(dt);
    }

    @Override
    public void use(final Point2D direction) {
        if (cooldown.isElapsed()) {
            findTarget().ifPresent(this::hit);
        }
    }

    private Optional<? extends Entity> findTarget() {
        return getEntity().getBody().getContacts()
                .filter(other -> canUseToward.test(PositionCompare.relativeSide(getEntity().getBody(), other)))
                .filter(body -> body.getOwner().isPresent())
                .map(body -> body.getOwner().get())
                .filter(entity -> entity.get(Life.class).isPresent())
                .findAny();
    }

    private void hit(final Entity target) {
        target.get(Life.class).get().hurt(damage);
        knockback(target);
        getEntity().getBody().applyImpulse(new Point2D(0, selfKnockback));
        cooldown.restart();
    }

    private void knockback(final Entity target) {
        final Side knockbackSide = PositionCompare.relativeSide(getEntity().getBody(), target.getBody());
        final Point2D knockbackDirection = PositionCompare.sideToDirection(knockbackSide);

        double verticalForce = otherKnockback * knockbackDirection.getY();
        if (knockbackSide.isVertical()) {
            final double sign = target.getBody().getPosition().subtract(getEntity().getBody().getPosition()).getY();
            verticalForce = Math.copySign(otherKnockback, sign) / 2;
        }

        target.getBody().setLinearVelocity(
                new Point2D(otherKnockback * knockbackDirection.getX(), verticalForce));
    }
}
