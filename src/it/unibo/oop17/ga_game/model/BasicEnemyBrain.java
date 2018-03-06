package it.unibo.oop17.ga_game.model;

public class BasicEnemyBrain extends AbstractEntityComponent implements Brain {

    @Override
    public void beginContact(EntityBody other) {
        /*
         * la mia idea era che appena l'owner tocca un muro o altro frontalmente
         * cambia direzione, con qualcosa del genere:
         * if (owner.getMovingDirection().equals(HorizontalDirection.RIGHT)) {
         * owner.move(HorizontalDirection.LEFT);
         * } else {
         * owner.move(HorizontalDirection.RIGHT);
         * }
         */

    }

    @Override
    public void endContact(EntityBody other) {
        // niente
    }
    

}
