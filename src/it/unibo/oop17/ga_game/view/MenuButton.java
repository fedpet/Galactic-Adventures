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

public class MenuButton extends StackPane {
    
    private Text text;
    private double sfxVol;

    public MenuButton(final String name, final Volume sfxVol) {
        
        super();
        update(name, sfxVol);
        
    }
    
    public final void update(final String name, final Volume sfxVol) {
        
        this.sfxVol = sfxVol.getVolume();
        
        getChildren().clear();
        
        text = new Text(name);
        text.setFont(Font.font(24));
        text.setFill(Color.BLACK);

        final Rectangle bg = new Rectangle(384, 32);
        bg.setOpacity(0.5);
        bg.setFill(Color.WHITE);
        bg.setEffect(new GaussianBlur(3.5));

        setAlignment(Pos.CENTER_LEFT);
        setRotate(-0.5);
        getChildren().addAll(bg, text);

        setOnMouseEntered(event -> {
            bg.setTranslateX(8);
            text.setTranslateX(8);
            bg.setFill(Color.BLACK);
            text.setFill(Color.WHITE);
            SFX.MOUSE_ENTERED.getSFX().play(this.sfxVol);
        });

        setOnMouseExited(event -> {
            bg.setTranslateX(0);
            text.setTranslateX(0);
            bg.setFill(Color.WHITE);
            text.setFill(Color.BLACK);
        });

        final DropShadow drop = new DropShadow(48, Color.BLACK);
        drop.setInput(new Glow());

        setOnMousePressed(event -> {
            setEffect(drop);
            SFX.MOUSE_CLICKED.getSFX().play(this.sfxVol);
        });
        
        setOnMouseReleased(event -> setEffect(null));
    }
}