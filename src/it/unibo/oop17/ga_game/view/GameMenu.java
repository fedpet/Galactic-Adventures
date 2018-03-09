package it.unibo.oop17.ga_game.view;


import it.unibo.oop17.ga_game.controller.Main;
import it.unibo.oop17.ga_game.model.ResetSave;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameMenu extends Parent {
    
    public GameMenu() {
        final VBox menu0 = new VBox(8);
        final VBox menu1 = new VBox(8);

        menu0.setTranslateX(96);
        menu0.setTranslateY(192);

        menu1.setTranslateX(96);
        menu1.setTranslateY(192);

        final int offset = 384;

        menu1.setTranslateX(offset);
        
        final MenuButton btnNuovaPartita = new MenuButton("   NUOVA PARTITA");
        btnNuovaPartita.setOnMouseClicked(event -> {
            // RICORDATI DI MODIFICARLO
            new ResetSave().reset();
            final String[] args = {};
            Main.main(args);
        });

        final MenuButton btnContinua = new MenuButton("   CONTINUA");
        btnContinua.setOnMouseClicked(event -> {
            // RICORDATI DI MODIFICARLO
            final String[] args = {};
            Main.main(args);
        });

        final MenuButton btnOpzioni = new MenuButton("   OPZIONI");
        btnOpzioni.setOnMouseClicked(event -> {
            getChildren().add(menu1);

            final TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt.setToX(menu0.getTranslateX() - offset);

            final TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
            tt1.setToX(menu0.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu0);
            });
        });

        final MenuButton btnEsci = new MenuButton("   ESCI");
        btnEsci.setOnMouseClicked(event -> {
            System.exit(0);
        });

        final MenuButton btnIndietro = new MenuButton("   INDIETRO");
        btnIndietro.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            final TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt.setToX(menu1.getTranslateX() + offset);

            final TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu1.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu1);
            });
        });

        final MenuButton btnVolume = new MenuButton("   VOLUME");
        final MenuButton btnLingua = new MenuButton("   LINGUA");
        final MenuButton btnDiff = new MenuButton("   DIFFICOLTA'");

        menu0.getChildren().addAll(btnContinua, btnNuovaPartita, btnOpzioni, btnEsci);
        menu1.getChildren().addAll(btnIndietro, btnVolume, btnLingua, btnDiff);

        final Rectangle bg = new Rectangle(1024, 512);
        bg.setOpacity(0);

        getChildren().addAll(bg, menu0);
    }
}