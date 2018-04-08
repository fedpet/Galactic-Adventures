package it.unibo.oop17.ga_game.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.application.Platform;

public final class LoadSaveManager {
    
    private LoadSaveManager() {
    }
    
    public static void save(final Serializable data, final String path) {
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            Platform.exit();
        }
        
    }

    public static Object load(final String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            Platform.exit();
            return null;
        }
    }
}