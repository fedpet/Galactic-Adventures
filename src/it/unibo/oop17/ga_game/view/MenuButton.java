package it.unibo.oop17.ga_game.view;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Menu button component.
 */
public class MenuButton extends StackPane {

//    private AudioController audioC;
    private Text text;

    /**
     * Menu button constructor.
     * @param name
     *          The menu button text.
     */
    public MenuButton(final String name) {
        super();
        update(name);
    }

    /**
     * Updates the menu button text.
     * @param name
     *          The menu button updated text.
     */
    public final void update(final String name) {
//        final ConfigData data = (ConfigData)LoadSaveManager.load("configdata.dat");
//        final AudioView audioV = new AudioViewImpl(data.getSFXVol(), data.getMusicVol());
//        audioC = new AudioControllerImpl(audioV);

        getChildren().clear();

        text = new Text("     " + name);
        text.setFont(Font.font(24));
        text.setFill(Color.BLACK);

        final Rectangle bg0 = new Rectangle(384, 32);
        bg0.setOpacity(0.5);
        bg0.setFill(Color.WHITE);
        bg0.setEffect(new GaussianBlur(3.5));

        setAlignment(Pos.CENTER_LEFT);
        setRotate(-0.5);
        getChildren().addAll(bg0, text);

        setOnMouseEntered(event -> {
            bg0.setTranslateX(8);
            text.setTranslateX(8);
            bg0.setFill(Color.BLACK);
            text.setFill(Color.WHITE);
//            audioC.playSFX(SFX.MOUSE_ENTERED.getPath());
        });

        setOnMouseExited(event -> {
            bg0.setTranslateX(0);
            text.setTranslateX(0);
            bg0.setFill(Color.WHITE);
            text.setFill(Color.BLACK);
        });

        final DropShadow drop = new DropShadow(48, Color.BLACK);
        drop.setInput(new Glow());

        setOnMousePressed(event -> {
            setEffect(drop);
//            audioC.playSFX(SFX.MOUSE_CLICKED.getPath());
        });

        setOnMouseReleased(event -> setEffect(null));
    }
}
