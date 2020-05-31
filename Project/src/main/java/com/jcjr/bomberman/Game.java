package com.jcjr.bomberman;

import com.jcjr.bomberman.Controller.GeneralGameController;
import com.jcjr.bomberman.Controller.GameController;

import java.awt.*;
import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException {
        Game g = new Game();
        g.start();
    }

    private void start() throws IOException {

        GeneralGameController gameEngine = new GameController();
        gameEngine.run();
    }

}