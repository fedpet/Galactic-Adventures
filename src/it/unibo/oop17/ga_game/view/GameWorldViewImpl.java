package it.unibo.oop17.ga_game.view;

import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;

import it.unibo.oop17.ga_game.controller.PlayerInput;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;

public class GameWorldViewImpl implements GameWorldView, Screen {
    private final Group worldView = new Group();
    private final PlayerInput playerInput;

    public GameWorldViewImpl(final PlayerInput input, final double scaleFactor) {
        playerInput = input;
        worldView.getTransforms().addAll(new Scale(scaleFactor, scaleFactor));
    }

    @Override
    public EntityViewFactory entityFactory() {
        return new EntityViewFactoryImpl(worldView);
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
        return worldView;
    }

    @Override
    public PlayerInput getPlayerInput() {
        return playerInput;
    }
}
