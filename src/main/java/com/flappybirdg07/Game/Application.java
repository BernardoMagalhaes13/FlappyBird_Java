package com.flappybirdg07.Game;

import java.io.IOException;

public class Application {
    public static void main(String[] arrgs) {
        try {
            FlappyBird flappyBird = FlappyBird.getInstance();
            flappyBird.run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}