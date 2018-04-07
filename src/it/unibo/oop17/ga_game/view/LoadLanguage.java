package it.unibo.oop17.ga_game.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.application.Platform;

public class LoadLanguage {
    
    private final Map<Language, Map<Text, String>> languages;
    
    public LoadLanguage() {
        this.languages = new HashMap<Language, Map<Text, String>>();
        for (final Language l : Language.values()) {
            this.languages.put(l, loadLanguage(l));
        }
    }
    
    private Map<Text, String> loadLanguage(final Language l) {
        try {
            return  Files.lines(Paths.get("res", "languages", l.toString() + ".txt"))
                    .map(line -> line.split("=", 2))
                    .collect(Collectors.toMap(key -> Text.valueOf(key[0]), val -> val[1]));
        } catch (IOException e) {
            System.err.println("ERROR: CANNOT LOAD LANGUAGE RESOURCES!");
            Platform.exit();
            return null;
        }
    }
    
    public Map<Text, String> getCurrLang(final Language lang) {
        return this.languages.get(lang);
    }

}
