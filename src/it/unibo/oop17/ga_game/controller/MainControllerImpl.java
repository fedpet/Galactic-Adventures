package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.view.MainView;
import it.unibo.oop17.ga_game.view.MainViewImpl;
import javafx.stage.Stage;

public class MainControllerImpl implements MainController {
    
    private final MainView view;
    private final ConfigData data;
    private final GameData save;
    
    MainControllerImpl(final Stage stage) {
        this.data = CheckData.loadConfig();
        this.save = CheckSave.loadSave();
        this.view = new MainViewImpl(stage);
        toEndLevel();
    }

    public final void toMenu() {
        this.view.showMenu(new MenuController(this.data, this.save).getView());
    }
    
    public final void toGame() {
        new GameControllerImpl(new GameWorld(), view.showGame(), view.showHud(), this);
    }

    public final void toEndLevel() {
        this.view.showEndLevel(new EndLevelController(this.data, this).getView());
    }
    
}
