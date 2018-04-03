package it.unibo.oop17.ga_game.view;

public interface HudView extends Screen {

    void addHud();

    void update(final int life, final int coins);

}
