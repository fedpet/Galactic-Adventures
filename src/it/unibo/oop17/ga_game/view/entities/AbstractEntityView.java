package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.view.ViewUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

/**
 * Base class for {@link EntityView}.
 */
public abstract class AbstractEntityView implements EntityView {

    private final ImageView view = new ImageView();
    private final Dimension2D dimension;
    private final Group parentView;

    /**
     * @param group
     *            The {@link Group} instance in which the entity view is added.
     * @param dimension
     *            The entity view dimension.
     */
    public AbstractEntityView(final Group group, final Dimension2D dimension) {
        parentView = group;
        this.dimension = dimension;

        group.getChildren().add(view);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setPosition(final Point2D worldPointToFx) {
        view.setTranslateX(worldPointToFx.getX() - view.getBoundsInLocal().getWidth() / 2);
        view.setTranslateY(worldPointToFx.getY() - view.getBoundsInLocal().getHeight() / 2);
    }

    @Override
    public final Point2D getPosition() {
        return new Point2D(view.getTranslateX(), view.getTranslateY());
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setDimension(final Dimension2D dimension) {
        view.setFitWidth(ViewUtils.metersToPixels(dimension.getWidth()));
        view.setFitHeight(ViewUtils.metersToPixels(dimension.getHeight()));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void remove() {
        parentView.getChildren().remove(view);
    }

    /**
     * 
     * @return the related {@link Dimension2D} instance.
     */
    protected Dimension2D getDimension() {
        return dimension;
    }

    /**
     * 
     * @return the related {@link ImageView} instance.
     */
    protected ImageView getView() {
        return view;
    }

    /**
     * 
     * @return the belonging {@link Group} instance.
     */
    protected Group getParentView() {
        return parentView;
    }
}
