package com.flappybirdg07;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.HashSet;
import java.util.Set;

public class Keyboard {

    private static Keyboard instance;

    private Set<KeyType> keysPressed;

    private Keyboard() {
        keysPressed = new HashSet<>();
    }

    public static Keyboard getInstance() {
        if (instance == null) {
            instance = new Keyboard();
        }
        return instance;
    }

    public synchronized void handleInput(KeyStroke keyStroke) {
        KeyType keyType = keyStroke.getKeyType();

        if (keyType != null) {
            keysPressed.add(keyType);
        }
    }

    public boolean isKeyPressed(char keyType) {
        return keysPressed.contains(keyType);
    }

    public boolean clearKeyPressed(KeyType keyType) {
        keysPressed.remove(keyType);
        return false;
    }
}
