package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.controller.EndGameObserver;
import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import it.unibo.oop17.ga_game.controller.GameOverObserver;
import it.unibo.oop17.ga_game.controller.MainController;
import it.unibo.oop17.ga_game.model.GameData;

public interface MainView {
    
    GameWorldView showGame(GameData save);

    MenuView showMenu(MainController controller);
    
    HudView showHud();

    void showError(Text message);
    
    CommonView<EndLevelObserver> showEndLevel(MainController controller);
    
    CommonView<GameOverObserver> showGameOver(MainController controller);
    
    CommonView<EndGameObserver> showEndGame(MainController controller);

}
