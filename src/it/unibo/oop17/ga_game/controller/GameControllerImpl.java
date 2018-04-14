package it.unibo.oop17.ga_game.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UnknownFormatConversionException;

import org.mapeditor.core.Map;
import org.mapeditor.io.TMXMapReader;

import com.google.common.io.Files;

import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.entities.Player;
import it.unibo.oop17.ga_game.model.entities.components.Inventory;
import it.unibo.oop17.ga_game.model.entities.components.Life;
import it.unibo.oop17.ga_game.utils.ZipUtils;
import it.unibo.oop17.ga_game.view.GameWorldView;
import it.unibo.oop17.ga_game.view.HudView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;

public class GameControllerImpl implements GameController {
    
    private static final double FRAMERATE = 1.0 / 60;
    
    private Player player;
    private GameWorld model;
    private GameWorldView view;
    private Set<EntityController> entities;
    private final HudView hudView;
    private final MainController mainController;
    private final GameData save;
    private final AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(final long now) {
            update();
        }
    };

    public GameControllerImpl(final GameWorldView view, final HudView hudView, final MainController mainController) {
        
        this.view = view;
        this.hudView = hudView;
        this.mainController = mainController;
        model = new GameWorld();
        entities = new LinkedHashSet<>();
        save = mainController.getGameData();
        this.whichLevel();
        
    }
    
    @Override
    public void stop() {
        animationTimer.stop();
    }
    
    private void whichLevel() {
        
        Map map;
        final File tempDir = Files.createTempDir();
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream("levels.zip"))) {
            ZipUtils.extract(is, tempDir);
            map = loadMap(new File(tempDir, "LEVEL_" + this.save.getLevelProgress() + ".tmx"));
        } catch (final IOException ex) {
            // TODO: add error message
            ex.printStackTrace();
            Platform.exit();
            map = null; // unreachable
        }
        
        this.run(map);
    }
    
    private Map loadMap(final File path) throws IOException {
        try {
            return new TMXMapReader().readMap(path.getAbsolutePath());
            // readMap throws a generic Exception :(
        } catch (final IOException e) {
            // this print is to prevent "A catch statement that catches an exception only to rethrow it should be
            // avoided...
            System.out.println("map read error");
            throw e;
        } catch (final Exception e) {
            // we assume map reading can only fail because of an IOException or a bad file format
            throw new UnknownFormatConversionException(e.getMessage());
        }
    }

    private void run(final Map map) {
        
        final LoadLevel loader = new LoadLevelImpl(map, this.model, this.view, this.mainController);
        this.model = loader.getGameWorld();
        this.view = loader.getGameWorldView();
        this.entities = loader.getEntities();
        this.player = loader.getPlayer();
        hudView.addHud();
        
        animationTimer.start();
        
    }

    private void update() {
        entities.forEach(EntityController::update);
        model.update(FRAMERATE);
        if (player != null && player.get(Life.class).isPresent()) {
            hudView.update(player.get(Life.class).get().getHealthPoints(),
                    player.get(Inventory.class).get().getMoney());
        }
    }
    
}
