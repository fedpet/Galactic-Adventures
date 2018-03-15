package it.unibo.oop17.ga_game.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class ResourceManager {
    
    private ResourceManager() {
    }
    
    public static void save(final Serializable data, final String path) throws FileNotFoundException, IOException {
        try (final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(data);
        }
    }

    public static ConfigData load(final String path) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (ConfigData)ois.readObject();
        }
    }
}