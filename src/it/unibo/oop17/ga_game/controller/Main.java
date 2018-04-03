package it.unibo.oop17.ga_game.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UnknownFormatConversionException;

import org.mapeditor.core.Map;
import org.mapeditor.io.TMXMapReader;

import com.google.common.io.Files;

import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.utils.ZipUtils;
import it.unibo.oop17.ga_game.view.MainView;
import it.unibo.oop17.ga_game.view.MainViewImpl;
import it.unibo.oop17.ga_game.view.Text;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Entry point.
 */
public class Main extends Application {

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
        
        view.showMenu();
        
        if (false) {
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
            
            new GameController(new GameWorld(), view.showGame(), view.showHud()).run(level);
        }
        
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

}
