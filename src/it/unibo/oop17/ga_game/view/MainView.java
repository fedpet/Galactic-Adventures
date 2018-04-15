package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.controller.EndGameObserver;
import it.unibo.oop17.ga_game.controller.EndLevelObserver;
import it.unibo.oop17.ga_game.controller.GameOverObserver;
import it.unibo.oop17.ga_game.controller.MainController;
import javafx.scene.text.Text;

public interface MainView {
    
    GameWorldView showGame(MainController controller);

    MenuView showMenu(MainController controller);
    
    HudView showHud(MainController controller);

    void showError(Text message);
    
    CommonView<EndLevelObserver> showEndLevel(MainController controller);
    
    CommonView<GameOverObserver> showGameOver(MainController controller);
    
    CommonView<EndGameObserver> showEndGame(MainController controller);

    void updateMusicVol();

}
