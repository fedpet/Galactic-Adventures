package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;
import java.util.function.BiPredicate;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * Deals damage to entities in contact with the owner.
 */
public final class MeleeWeapon extends AbstractEntityComponent implements Weapon {
    private final BiPredicate<Dimension2D, Point2D> positionChecker;
    private final int damage;
    private final double selfKnockback, otherKnockback;

    /**
     * @param damage
     *            The damage in health points.
     * @param selfKnockback
     *            The knockback force applied to the user.
     * @param otherKnockback
     *            The knockback force applied to the entity hit.
     * @param positionChecker
     *            A strategy to evaluate if a relative position can be hit.
     *            It will receive the dimension of the owner and the relative direction of the hit.
     *            If it returns false the target won't be hit.
     *            It can be useful to restrict the weapon usage to certain sides only.
     */
    public MeleeWeapon(final int damage, final double selfKnockback, final double otherKnockback,
            final BiPredicate<Dimension2D, Point2D> positionChecker) {
        super();
        this.damage = damage;
        this.selfKnockback = selfKnockback;
        this.otherKnockback = otherKnockback;
        this.positionChecker = positionChecker;
    }

    @Override
    public void use(final Point2D direction) {
        if (canUseToward(direction)) {
            findTarget(direction).ifPresent(e -> hit(e, direction));
        }
    }

    private boolean canUseToward(final Point2D direction) {
        return positionChecker.test(getEntity().getBody().getDimension(), direction);
    }

    private Optional<? extends Entity> findTarget(final Point2D direction) {
        return getEntity().getBody().getContacts()
                .filter(body -> body.getPoint().equals(direction))
                .filter(contact -> contact.getOtherBody().getOwner().isPresent())
                .map(contact -> contact.getOtherBody().getOwner().get())
                .filter(entity -> entity.get(Life.class).isPresent())
                .findAny();
    }

    private void hit(final Entity target, final Point2D direction) {
        target.get(Life.class).get().hurt(damage);
        knockback(target, direction);
        getEntity().getBody().applyImpulse(new Point2D(0, selfKnockback));
    }

    private void knockback(final Entity entity, final Point2D direction) {
        final Side knockbackSide = PositionCompare.relativeSide(getEntity().getBody().getDimension(), direction);
        final Point2D knockbackDirection = PositionCompare.sideToDirection(knockbackSide).multiply(-1);

        double verticalForce = otherKnockback * knockbackDirection.getY() * 2;
        if (knockbackSide.isVertical()) {
            final double sign = direction.subtract(entity.getBody().getPosition()).getY();
            verticalForce = Math.copySign(otherKnockback, sign);
        }

        entity.getBody()
                .applyImpulse(
                        new Point2D(otherKnockback * knockbackDirection.getX(),
                                verticalForce));
    }
}
