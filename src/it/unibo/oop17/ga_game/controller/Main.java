package it.unibo.oop17.ga_game.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UnknownFormatConversionException;

import org.mapeditor.core.Map;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

import com.google.common.io.Files;

import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.ModelSettings;
import it.unibo.oop17.ga_game.utils.SimpleCollisionGrid;
import it.unibo.oop17.ga_game.utils.ZipUtils;
import it.unibo.oop17.ga_game.view.MainView;
import it.unibo.oop17.ga_game.view.MainViewImpl;
import it.unibo.oop17.ga_game.view.Text;
import it.unibo.oop17.ga_game.view.ViewUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Entry point.
 */
public class Main extends Application {
    private static final double FRAMERATE = 1.0 / 60;
    private static final double SCALE = 1; // TODO: gestire diversa scale
    private final GameWorld gameWorld = new GameWorld();
    private final Group root = new Group();
    private final Group worldView = new Group();

    /**
     * Entry point.
     * 
     * @param args
     *            Command-line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        
        final MainView view = new MainViewImpl(stage);

        Map level;
        final File tempDir = Files.createTempDir();
        try (InputStream is = getClass().getResourceAsStream("/levels.zip")) {
            ZipUtils.extract(is, tempDir);
            level = loadMap(new File(tempDir, "level1.tmx"));
        } catch (final IOException ex) {
            // TODO: add error message
            view.showError(Text.CONTINUE);
            ex.printStackTrace();
            Platform.exit();
            return;
        }

        new GameController(new GameWorld(), view.showGame()).run(level);
    }

    private static Map loadMap(final File path) throws IOException {
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

    private Map loadLevel(final File file) throws IOException {
        Map map;
        try {
            map = new TMXMapReader().readMap(file.getAbsolutePath());
            // readMap throws a generic Exception :(
        } catch (final IOException e) {
            // this is to prevent "A catch statement that catches an exception only to rethrow it should be avoided...
            System.out.println("map read error");
            throw e;
        } catch (final Exception e) {
            // we assume map reading can only fail because of an IOException or a bad file format
            throw new UnknownFormatConversionException(e.getMessage());
        }


        map.forEach(layer -> {
            if (layer instanceof TileLayer) {
                final TileLayer tileLayer = (TileLayer) layer;

                for (int x = 0; x < map.getWidth(); x++) {
                    for (int y = 0; y < map.getHeight(); y++) {
                        final Tile tile = tileLayer.getTileAt(x, y);
                        if (doesTileExists(tile)) {
                            final Image tileImage = SwingFXUtils.toFXImage(tile.getImage(), null);
                            final ImageView tileView = new ImageView(tileImage);
                            tileView.setFitWidth(ViewUtils.metersToPixels(ModelSettings.TILE_SIZE));
                            tileView.setFitHeight(ViewUtils.metersToPixels(ModelSettings.TILE_SIZE));
                            tileView.setTranslateX(x * ViewUtils.metersToPixels(ModelSettings.TILE_SIZE));
                            tileView.setTranslateY(y * ViewUtils.metersToPixels(ModelSettings.TILE_SIZE));
                            worldView.getChildren().add(tileView);
                        }
                    }
                }

                gameWorld.bodyFactory()
                        .createTerrainFromGrid(Point2D.ZERO,
                                new Dimension2D(ModelSettings.TILE_SIZE, ModelSettings.TILE_SIZE),
                                new SimpleCollisionGrid(map.getHeight(), map.getWidth(), (row, column) ->  {
                                        return doesTileExists(tileLayer.getTileAt(column, row));
                                })
                         );
            }
        });

        return map;
    }

    private boolean doesTileExists(final Tile tile) {
        return tile != null && tile.getImage() != null;
    }

    private Point2D tilePositionToWorld(final int x, final int y) {
        return new Point2D(x * ModelSettings.TILE_SIZE + ModelSettings.TILE_SIZE / 2,
                -(y * ModelSettings.TILE_SIZE + ModelSettings.TILE_SIZE / 2));
    }

}
