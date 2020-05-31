package com.jcjr.bomberman.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.jcjr.bomberman.View.GUI;
import com.jcjr.bomberman.View.GameScreen;

import java.awt.*;
import java.io.IOException;

import static com.jcjr.bomberman.Controller.GameStates.*;

public class GameController implements GeneralGameController {

    protected boolean ShowingMenu = true;
    protected GUI canvas;

    protected GameStates connectionState;

    private GameEngine gameEngine;
    private MenuEngine menuEngine;

    public GameController() {
        try {
            this.canvas = new GameScreen(75,50, "#000000");
            gameEngine = new GameEngine(this, this.canvas);
            menuEngine = new MenuEngine(this, this.canvas);
            connectionState = SHOWMENU;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException {
        while (true) {
            this.draw();
            KeyStroke key = this.canvas.readInput();
            if (key.getKeyType() == KeyType.EOF) {
                break;
            } else if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q'  || key.getCharacter() == 'Q')) {
                canvas.close();
                break;
            } else {
                this.processKey(key);
            }
        }
    }


    private void ViewDecision() {
        switch (connectionState) {
            case SHOWMENU:
                menuEngine.setState(STARTMENU);
                ShowingMenu = true;
                break;
            case SHOWINGMENU:
                ShowingMenu = true;
                break;
            case C_GAMEENDMENU:
                menuEngine.setState(GAMEENDMENU);
                ShowingMenu = true;
                break;

            case C_PLAY:
                ShowingMenu = false;
                gameEngine.setState(PLAYORDER);
                break;
            case PLAYING:
                ShowingMenu = false;
                break;
        }
    }

    private void draw() throws IOException {
        this.canvas.clear();
        ViewDecision();
        if(ShowingMenu)
            menuEngine.draw();
        else {
            gameEngine.draw();
        }
        this.canvas.refresh();
    }

    /**
     * Tells where to process the Key input.
     * @param key that should be given by the GUI
     * @throws IOException
     */
    private void processKey(KeyStroke key) throws IOException {
        if (ShowingMenu)
            menuEngine.processInput(key);
        else
            gameEngine.processInput(key);

    }

    /***
     * Sets the GameState
     * @param gs GameState to set
     */
    public void setGameState( GameStates gs) {
        this.connectionState = gs;
    }

    /***
     * gets the connection GameState
     * @return current connection State
     */
    public GameStates getGameState() { return this.connectionState;}

}
