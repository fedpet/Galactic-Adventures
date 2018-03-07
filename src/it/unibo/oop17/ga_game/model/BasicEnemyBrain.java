package it.unibo.oop17.ga_game.model;

import javafx.geometry.HorizontalDirection;
import javafx.scene.shape.Rectangle;

public class BasicEnemyBrain extends AbstractEntityComponent implements Brain {

    @Override
    public void beginContact(EntityBody other) {

        final double ownerX = getEntity().getBody().getPosition().getX()
                - getEntity().getBody().getDimension().getWidth() / 2;
        final double ownerY = getEntity().getBody().getPosition().getY()
                + getEntity().getBody().getDimension().getHeight() / 2;
        final double ownerW = getEntity().getBody().getDimension().getWidth();
        final double ownerH = getEntity().getBody().getDimension().getHeight();
        final double otherX = other.getPosition().getX() - other.getDimension().getWidth();
        final double otherY = other.getPosition().getY() + other.getDimension().getHeight();
        final double otherW = other.getDimension().getWidth();
        final double otherH = other.getDimension().getHeight();

        // final Rectangle ownerR = new Rectangle(ownerX, ownerY, ownerW, ownerH);
        final Rectangle otherR = new Rectangle(otherX, otherY, otherW, otherH);

        /*
         * in teoria dovrebbe cambiare direzione se il rettangolo di other contiene:
         * un punto leggermente distante dal vertice più in basso a destra dell'owner
         * un punto leggermente distante dal vertice più in basso a sinistra dell'owner
         */
        if ((otherR.contains(ownerX + ownerW + 0.1, ownerY - ownerH + 0.1)
                || otherR.contains(ownerX - 0.1, ownerY - ownerH + 0.1))
                && getEntity() instanceof BasicEnemy) {
                final BasicEnemy x = (BasicEnemy) getEntity();
                if (x.getMovingDirection().equals(HorizontalDirection.LEFT)) {
                System.out.println("right");
                    x.move(HorizontalDirection.RIGHT);
                } else {
                System.out.println("left");
                    x.move(HorizontalDirection.LEFT);
                }
        }

    }

    @Override
    public void endContact(EntityBody other) {

    }

}
