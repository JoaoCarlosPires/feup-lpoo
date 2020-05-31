package com.jcjr.bomberman.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.jcjr.bomberman.Controller.FileReader.*;
import com.jcjr.bomberman.View.GUI;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static com.jcjr.bomberman.Controller.GameStates.*;


public class MenuEngine extends Score {

    private GeneralGameController gcc;

    private GameStates currentState;
    private GUI canvas;
    private List<String> gameOptions = Arrays.asList("PLAY", "INSTRUCTIONS", "HIGHSCORE", "EXIT");
    private int menuoption = 0;


    private List<String> gameInstructions;
    private List<String> highScores;
    private HighScoreReader frh;
    private InstructionsReader fri;

    private String name = "";

    public MenuEngine(GeneralGameController gcc ,GUI canvas) {
        setState(STARTMENU);
        this.canvas = canvas;
        this.fri = new FileReaderInstructions();
        this.frh = new FileReaderHighscore();
        this.gcc = gcc;
    }


    public void draw() throws IOException {
        if(currentState.equals(STARTMENU))
            resetMenuEngine();
        if(currentState.equals(SHOWMENU))
            drawMainMenu();
        else if(currentState.equals(SHOWINSTRUCTIONS))
            drawInstructions();
        else if(currentState.equals(HIGHSCORE))
            drawHighscore();
        else if(currentState.equals(GAMEENDMENU))
            readyForHighscore();
        if(currentState.equals(GAMEEND_RUNNING))
            drawHighscoreGetName();
    }

    public void processInput(KeyStroke key) throws IOException {
        KeyType myKey = key.getKeyType();

        if(currentState.equals(GAMEEND_RUNNING)) {
            if(myKey == KeyType.Enter || myKey == KeyType.ArrowRight)
                saveHighscore();
            else if( myKey == KeyType.Character)
                name += key.getCharacter();
            else if( myKey == KeyType.Backspace && (name.length() > 0))
                name = name.substring(0, name.length() - 1);
        }

        else {
            boolean select = (myKey == KeyType.Enter || myKey == KeyType.ArrowRight);
            boolean select2 = (myKey == KeyType.Backspace || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'b'));
            if (myKey == KeyType.ArrowDown)
                arrowdown();
            else if (myKey == KeyType.ArrowUp)
                arrowup();
            else if (select)
                this.selectAction(menuoption);
            else if (select2)
                setState(STARTMENU);
        }
    }


    private void readyForHighscore() {
        this.gcc.setGameState(GameStates.SHOWINGMENU);
        setState(GAMEEND_RUNNING);
    }

    private void resetMenuEngine() {
        this.gcc.setGameState(GameStates.GAMEEND_RUNNING);
        setState(GameStates.SHOWMENU);
        menuoption = 0;
    }



    private void saveHighscore() throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDateTime now = LocalDateTime.now();
        this.frh.saveHighScore(name, String.valueOf(getScore()), dtf.format(now));
        setState(SHOWMENU);
        name = "";

    }


    /* ------------------------------------------------------------
                               Menu Logic
    -------------------------------------------------------------- */

    private void selectAction(int menuoption) throws IOException {
        if( menuoption == gameOptions.size()-1)
            this.canvas.close();
        else if( menuoption == 0) {
            this.gcc.setGameState(GameStates.C_PLAY);
        }
        else if (menuoption == 1) {
            gameInstructions = fri.readInfo();
            setState(SHOWINSTRUCTIONS);
        }
        else if (menuoption == 2) {
            highScores = frh.readInfo();
            setState(GameStates.HIGHSCORE);
        }
    }

    private void arrowdown() {
        menuoption = ((menuoption >= gameOptions.size()-1) ? 0 : menuoption+1);
    }

    private void arrowup() {
        menuoption = ((menuoption <= 0) ? gameOptions.size()-1 : menuoption-1);
    }


    /* ------------------------------------------------------------
                                Draw Logic
    -------------------------------------------------------------- */

    private void drawMainMenu()  throws IOException {
        canvas.drawText(canvas.getWidth()/9, 3, "Main Menu");
        canvas.drawTitle(canvas.getWidth()/9);
        canvas.drawOptions(canvas.getWidth()/9, 13,  gameOptions, menuoption);
        canvas.drawText(canvas.getWidth()/9, 30, "Press <Enter> or <RIGHT> to select an option.");
        canvas.drawText(canvas.getWidth()/9, 30, "Press <UP> or <DOWN> to navigate in the Main Menu.");
        canvas.drawCredits(canvas.getWidth()/9, 35);
    }

    private void drawInstructions() throws IOException{
        canvas.drawText(canvas.getWidth()/9, 3, "Instructions");
        canvas.drawTitle(canvas.getWidth()/9);
        canvas.drawText((canvas.getWidth()/9)+3, 13, gameInstructions);
        canvas.drawText(canvas.getWidth()/9, 30, "Press <B> or <Backspace> to return to Main Menu");
        canvas.drawCredits(canvas.getWidth()/9, 35);
    }

    private void drawHighscore() throws IOException{
        canvas.drawText(canvas.getWidth()/9, 3, "Highscores");
        canvas.drawTitle(canvas.getWidth()/9);
        canvas.drawHighscore((canvas.getWidth()/9)+3, 13, frh.readInfo());
        canvas.drawText(canvas.getWidth()/9, 30, "Press <B> or <Backspace> to return to Main Menu");
        canvas.drawCredits(canvas.getWidth()/9, 35);
    }

    /**
     * Desenha o highscore name. que deve ser obtido pelo Canvas / GUI.
     * @throws IOException -
     * @throws java.lang.IllegalArgumentException caso user tente inserir algo que não é texto
     */
    private void drawHighscoreGetName() throws IOException, java.lang.IllegalArgumentException {
        canvas.drawTitle(canvas.getWidth()/9);
        canvas.drawText(10,17,"Highscore: ");
        canvas.drawColoredText(11,18, String.valueOf(getScore()), "#33ABF9");
        canvas.drawText(10,20,"Insert your name:");
        try {
            canvas.drawColoredText(11, 21, name, "#33ABF9");
        }catch (java.lang.IllegalArgumentException e)
        {
            System.out.println("catched: " + e.getMessage());
        }
    }

    public void setState(GameStates state) {
        this.currentState = state;
    }

    public GameStates getCurrentState() { return this.currentState; }
}
