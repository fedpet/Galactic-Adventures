package it.unibo.oop17.ga_game.view;

public interface MainView {
    
    GameWorldView showGame();

    void showMenu(MenuView view);
    
    HudView showHud();

    void showError(Text message);
    
    void showEndLevel();
    
    void showGameOver();
    
    void showEndGame();

}
