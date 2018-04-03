package it.unibo.oop17.ga_game.view;

public interface MainView {
    
    GameWorldView showGame();

    void showMenu();

    HudView showHud();

    void showError(Text message);

}
