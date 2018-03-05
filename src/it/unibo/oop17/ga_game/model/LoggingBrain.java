package it.unibo.oop17.ga_game.model;

/**
 * Brain di test dimostrativo da cambiare.
 */
public final class LoggingBrain extends AbstractEntityComponent implements Brain {

    @Override
    public void beginContact(final EntityBody other) {
        System.out.println(toString() + ": Begin contact with " + other.toString());
    }

    @Override
    public void endContact(final EntityBody other) {
        System.out.println(toString() + ": End contact with " + other.toString());
    }

}
