package com.jcjr.bomberman.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class GameScreen extends Canvas {

    public GameScreen(int width, int height, String backgroundColor) throws IOException, FontFormatException {
        super(width, height, backgroundColor);
    }

    public void drawTitle(int col) {
        screen.enableModifiers(SGR.BOLD);
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        screen.putString(col, 5, "______  ________  _________ ______________  ___  ___   _   _ ");
        screen.setForegroundColor(TextColor.Factory.fromString("#FFCC00"));
        screen.putString(col, 6, "| ___ \\|  _  |  \\/  || ___ \\  ___| ___ \\  \\/  | / _ \\ | \\ | |");
        screen.setForegroundColor(TextColor.Factory.fromString("#FF9900"));
        screen.putString(col, 7, "| |_/ /| | | | .  . || |_/ / |__ | |_/ / .  . |/ /_\\ \\|  \\| |");
        screen.setForegroundColor(TextColor.Factory.fromString("#FF6600"));
        screen.putString(col, 8, "| ___ \\| | | | |\\/| || ___ \\  __||    /| |\\/| ||  _  || . ` |");
        screen.setForegroundColor(TextColor.Factory.fromString("#FF3300"));
        screen.putString(col, 9, "| |_/ /\\ \\_/ / |  | || |_/ / |___| |\\ \\| |  | || | | || |\\  |");
        screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        screen.putString(col, 10, "\\____/  \\___/\\_|  |_/\\____/\\____/\\_| \\_\\_|  |_/\\_| |_/\\_| \\_/");
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.disableModifiers(SGR.BOLD);
    }

    /**
     * Used to create a interactable SELECTABLE menu.
     * Note: logic not handled here
     * @param col column where the string will start to be written
     * @param row row where the string will start to be written
     * @param menuOptions list of strings w/ options of main menu
     * @param arrowPosition position of the current selected option
     * */

    public void drawOptions(int col, int row, List<String> menuOptions, int arrowPosition) {
        drawText(col+3, row, menuOptions);
        screen.enableModifiers(SGR.BOLD);
        screen.putString(col, row + arrowPosition, ">");
        screen.disableModifiers(SGR.BOLD);
    }

    public int compare(String s1, String s2) {
        int t1 = Integer.parseInt(s1.substring(0,3));
        int t2 = Integer.parseInt(s2.substring(0,3));
        return t2 - t1;
    }

    public void drawHighscore(int col, int row, List<String> highscores) {
        String head = String.format("%-16s%-16s%-16s", "Name", "Highscore", "Date");
        screen.putString(col, row, head);
        Collections.sort(highscores, this::compare);
        int i = 2;
        for (String high : highscores) {
            if (i == 12) break;
            String[] parts = high.split("-");
            String output = String.format("%-16s%-16s%-16s", parts[1], Integer.valueOf(parts[0]).toString(), parts[2]);
            screen.putString(col, row + i, output);
            i++;
        }
    }



}
