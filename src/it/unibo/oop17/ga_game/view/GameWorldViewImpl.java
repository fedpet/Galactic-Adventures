package it.unibo.oop17.ga_game.view;

import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;

import it.unibo.oop17.ga_game.controller.PlayerInputListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;

/**
 * GameWorld view.
 */
public final class GameWorldViewImpl implements GameWorldScreen {
    private final Group worldView = new Group();
    private final Group rootView = new Group(worldView);
    private final PlayerKeyboardInput playerInput;
    private final HudScreen hud = new HudViewImpl();
    private final AudioPlayer audioplayer;

    /**
     * Constructor of GameWorldView.
     * @param input
     *          The player input.
     * @param scaleFactor
     *          The scale factor.
     * @param audioplayer
     *          The audio player.
     */
    public GameWorldViewImpl(final PlayerKeyboardInput input, final double scaleFactor, final AudioPlayer audioplayer) {
        this.audioplayer = audioplayer;
        playerInput = input;
        rootView.getChildren().add(hud.getNode());
        worldView.getTransforms().addAll(new Scale(scaleFactor, scaleFactor));
    }

    @Override
    public EntityViewFactory entityFactory() {
        return new EntityViewFactoryImpl(worldView, hud, audioplayer);
    }

    @Override
    public void showTerrain(final TileLayer layer, final Point2D topLeft, final Dimension2D tileSize) {
        for (int x = 0; x < layer.getMap().getWidth(); x++) {
            for (int y = 0; y < layer.getMap().getHeight(); y++) {
                final Tile tile = layer.getTileAt(x, y);
                if (tile != null && tile.getImage() != null) {
                    final Image tileImage = SwingFXUtils.toFXImage(tile.getImage(), null);

                    final ImageView tileView = new ImageView(tileImage);
                    tileView.setFitWidth(ViewUtils.metersToPixels(tileSize.getWidth()));
                    tileView.setFitHeight(ViewUtils.metersToPixels(tileSize.getHeight()));
                    tileView.setTranslateX(x * ViewUtils.metersToPixels(tileSize.getWidth()) + topLeft.getX());
                    tileView.setTranslateY(y * ViewUtils.metersToPixels(tileSize.getHeight()) + topLeft.getY());

                    worldView.getChildren().add(tileView);
                }
            }
        }
    }

    @Override
    public Node getNode() {
        return rootView;
    }

    @Override
    public void setPlayerInputListener(final PlayerInputListener listener) {
        playerInput.setListener(listener);
    }
}
