package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.view.ViewUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public abstract class AbstractEntityView implements EntityView {

    private final ImageView view = new ImageView();
    private final Dimension2D dimension;
    private final Group parentView;

    public AbstractEntityView(final Group group, final Dimension2D dimension) {
        parentView = group;
        this.dimension = dimension;


        group.getChildren().add(view);
    }

    @Override
    public void setPosition(final Point2D point) {
        view.setTranslateX(point.getX() - view.getBoundsInLocal().getWidth() / 2);
        view.setTranslateY(point.getY() - view.getBoundsInLocal().getHeight() / 2);
    }

    @Override
    public Point2D getPosition() {
        return new Point2D(view.getTranslateX(), view.getTranslateY());
    }

    @Override
    public void setDimension(final Dimension2D dimension) {
        view.setFitWidth(ViewUtils.metersToPixels(dimension.getWidth()));
        view.setFitHeight(ViewUtils.metersToPixels(dimension.getHeight()));
    }

    @Override
    public void remove() {
        parentView.getChildren().remove(view);
    }

    protected Dimension2D getDimension() {
        return dimension;
    }

    protected ImageView getView() {
        return view;
    }
}
