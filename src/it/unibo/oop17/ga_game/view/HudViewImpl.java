package it.unibo.oop17.ga_game.view;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.oop17.ga_game.model.KeyLockType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

/**
 * Controls the heads-up display.
 */
public final class HudViewImpl implements HudView {
    private static final int PADDING = 20;
    private static final Image IMG_HEART_FULL = new Image("/hud/hud_heartFull.png");
    private static final Image IMG_HEART_EMPTY = new Image("/hud/hud_heartEmpty.png");
    private static final Map<KeyLockType, Pair<Image, Image>> IMG_KEY;
    private static final List<Image> IMG_DIGIT;
    static {
        IMG_KEY = Arrays.stream(KeyLockType.values())
            .collect(Collectors.toMap(Function.identity(), key -> {
                    return new Pair<>(new Image("/hud/key_empty_" + key.toString() + ".png"),
                            new Image("/hud/key_full_" + key.toString() + ".png"));
                }));
        IMG_DIGIT = IntStream.range(0, 10)
                .mapToObj(n -> "/hud/hud_" + n + ".png")
                .map(Image::new)
                .collect(Collectors.toList());
    }
    private final List<ImageView> hearts = new LinkedList<>();
    private final Map<KeyLockType, ImageView> keys = new LinkedHashMap<>();
    private final HBox lifeBar = new HBox();
    private final HBox keysBar = new HBox();
    private final HBox moneyBar = new HBox();
    private final HBox root = new HBox(PADDING, lifeBar, keysBar, moneyBar);
    private final List<ImageView> moneyDigit = new LinkedList<>();
    private int currentLife;

    /**
     * Initializes HUD images.
     */
    public HudViewImpl() {
        IMG_KEY.entrySet().forEach(entry -> {
            keys.put(entry.getKey(), new ImageView(entry.getValue().getKey()));
            keysBar.getChildren().add(keys.get(entry.getKey()));
        });
        root.setPadding(new Insets(PADDING));
        moneyBar.setAlignment(Pos.CENTER_LEFT);
        moneyBar.getChildren().add(new ImageView(new Image("/hud/hud_coins.png")));
        moneyBar.getChildren().add(new ImageView(new Image("/hud/hud_x.png")));
    }

    @Override
    public Node getNode() {
        return root;
    }

    @Override
    public void setCurrentHealth(final int life) {
        currentLife = life;
        for (int i = 0; i < life; i++) {
            hearts.get(i).setImage(IMG_HEART_FULL);
        }
        for (int i = life; i < hearts.size(); i++) {
            hearts.get(i).setImage(IMG_HEART_EMPTY);
        }
        if (life > hearts.size()) {
            setMaxHealth(life);
        }
    }

    @Override
    public void setMaxHealth(final int max) {
        hearts.forEach(view -> lifeBar.getChildren().remove(view));
        hearts.clear();
        for (int i = 0; i < max; i++) {
            hearts.add(new ImageView(IMG_HEART_EMPTY));
        }
        hearts.forEach(view -> lifeBar.getChildren().add(view));
        setCurrentHealth(currentLife);
    }

    @Override
    public void setKeys(final Set<KeyLockType> collectedKeys) {
        keys.forEach((type, view) -> view.setImage(IMG_KEY.get(type).getKey()));
        collectedKeys.forEach(type -> keys.get(type).setImage(IMG_KEY.get(type).getValue()));
    }

    @Override
    public void setMoney(final int money) {
        moneyDigit.forEach(view -> moneyBar.getChildren().remove(view));
        moneyDigit.clear();
        for (final String ch : Integer.toString(money).split("")) {
            moneyDigit.add(new ImageView(IMG_DIGIT.get(Integer.parseInt(ch))));
        }
        moneyDigit.forEach(view -> moneyBar.getChildren().add(view));
    }
}
