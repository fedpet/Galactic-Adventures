package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.controller.EndGameObserver;
import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import it.unibo.oop17.ga_game.controller.GameOverObserver;

public interface MainView {
    
    GameWorldView showGame();

    void showMenu(MenuView view);
    
    HudView showHud();

    void showError(Text message);
    
    void showEndLevel(CommonView<EndLevelObserver> view);
    
    void showGameOver(CommonView<GameOverObserver> view);
    
    void showEndGame(CommonView<EndGameObserver> view);

}
