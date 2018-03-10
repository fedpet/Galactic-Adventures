package it.unibo.oop17.ga_game.model;

/**
 * Brain di test dimostrativo da cambiare.
 */
public final class LoggingBrain extends AbstractEntityComponent implements Brain {

    @Override
    public void beginContact(final EntityBody other) {
        // System.out.println(toString() + ": Begin contact with " + other.toString());
        System.out.println(getEntity().getBody().getPosition() + ": Begin contact with " + other.getPosition());
    }

    @Override
    public void endContact(final EntityBody other) {
        // System.out.println(toString() + ": End contact with " + other.toString());
        System.out.println(getEntity().getBody().getPosition() + ": End contact with " + other.getPosition());
    }

    @Override
    public void update(final double dt) {
        // TODO Auto-generated method stub

    }

}
