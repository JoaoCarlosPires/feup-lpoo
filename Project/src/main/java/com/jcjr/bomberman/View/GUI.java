package com.jcjr.bomberman.View;

import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.List;

public interface GUI {

    void drawTitle(int col) throws IOException;
    void drawOptions(int col, int row, List<String> menuOptions, int arrowPosition);
    void drawText(int col, int row, List<String> stringList);
    void drawText(int col, int row, String str);
    void drawColoredText(int col, int row, String str, String color);
    void drawHighscore(int col, int row, List<String> highscores);
    void drawCredits(int col, int row);

    KeyStroke readInput() throws IOException;

    void refresh() throws IOException;
    void clear() throws IOException;
    void close() throws IOException;

    int getWidth();
    int getHeight();
    String getBackgroundColor();

    void setBackgroundColor(String color);


}
