package it.unibo.oop17.ga_game.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UnknownFormatConversionException;

import org.mapeditor.core.Map;
import org.mapeditor.io.TMXMapReader;

import com.google.common.io.Files;

import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.utils.ZipUtils;
import it.unibo.oop17.ga_game.view.GameWorldView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;

/**
 *  Controls the game play.
 */
public final class GameControllerImpl implements GameController {

    private static final double FRAMERATE = 1.0 / 60;

    private Entity player;
    private GameWorld model;
    private GameWorldView view;
    private Set<EntityController> entities;
    private final MainController mainController;
    private final GameData save;
    private final AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(final long now) {
            update();
        }
    };

    /**
     * Constructor for GameController.
     * @param save
     *          Game data.
     * @param view
     *          Game world view.
     * @param mainController
     *          Main controller.
     */
    public GameControllerImpl(final GameData save, final GameWorldView view, final MainController mainController) {
        this.save = save;
        this.view = view;
        this.mainController = mainController;
        model = new GameWorld();
        entities = new LinkedHashSet<>();
        whichLevel();
    }

    @Override
    public void stop() {
        animationTimer.stop();
    }

    private void whichLevel() {
        Map map;
        final File tempDir = Files.createTempDir();
        try (InputStream is = getClass().getResourceAsStream("/levels.zip")) {
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
        animationTimer.start();
    }

    private void update() {
        entities.forEach(EntityController::update);
        model.update(FRAMERATE);
    }

}
