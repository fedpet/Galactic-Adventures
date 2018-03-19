package it.unibo.oop17.ga_game.model.entities.components;

import java.util.function.BiPredicate;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Deals damage to entities in contact with the owner.
 */
public final class MeleeWeapon extends AbstractEntityComponent implements Weapon {
    private final BiPredicate<Dimension2D, Point2D> positionChecker;
    private final int damage;
    private final double knockback;

    /**
     * @param damage
     *            The damage in health points.
     * @param knockback
     *            The knockback force.
     * @param positionChecker
     *            A strategy to evaluate if a relative position can be hit.
     *            It will receive the dimension of the owner and the relative direction of the hit.
     *            If it returns false the target won't be hit.
     *            It can be useful to restrict the weapon usage to certain sides only.
     */
    public MeleeWeapon(final int damage, final double knockback,
            final BiPredicate<Dimension2D, Point2D> positionChecker) {
        this.damage = damage;
        this.knockback = knockback;
        this.positionChecker = positionChecker;
    }

    @Override
    public void use(final Point2D direction) {
        if (canUse(direction)) {
            getEntity().getBody().getContacts()
                    .filter(body -> body.getPoint().equals(direction))
                    .filter(contact -> contact.getOtherBody().getOwner().isPresent())
                    .map(contact -> contact.getOtherBody().getOwner().get())
                    .filter(entity -> entity.get(Life.class).isPresent())
                    .map(entity -> entity.get(Life.class).get())
                    .findAny()
                    .ifPresent(targetedLife -> {
                        targetedLife.hurt(damage);
                        getEntity().getBody().applyImpulse(new Point2D(0, knockback));
                    });
        }
    }

    private boolean canUse(final Point2D direction) {
        return positionChecker.test(getEntity().getBody().getDimension(), direction);
    }
}
